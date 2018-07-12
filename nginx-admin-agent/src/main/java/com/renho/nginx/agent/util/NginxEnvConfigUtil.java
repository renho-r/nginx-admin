package com.renho.nginx.agent.util;

import com.renho.nginx.agent.exception.AgentException;
import com.renho.nginx.agent.exception.InitPropertiesException;
import com.renho.nginx.agent.model.NginxEnv;
import com.renho.nginx.agent.model.request.InitAgentRequest;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * @author: renho
 * @since: 1.0.0
 */
public enum NginxEnvConfigUtil {

    /**
     *
     */
    INSTANCE;

    public static NginxEnv getNginxEnvConfig(InitAgentRequest initRequest) throws AgentException {

        if(null!=initRequest && null!=initRequest.getNginxEnv()) {
            return initRequest.getNginxEnv();
        }
        return initNginxEnvConfigFromProperties();
    }

    /**
     * OS type
     * @return
     * @throws AgentException
     */
    private static NginxEnv initNginxEnvConfigFromProperties() throws AgentException {

        NginxEnv nginxEnv = new NginxEnv();

        String nginxHome = PropertiesUtils.getProperty("nginx.home");
        if(StringUtils.isBlank(nginxHome)) {
            throw new InitPropertiesException("Nginx home must not empty!");
        }
        nginxEnv.setNginxHome(nginxHome);

        String nginxBinPath = PropertiesUtils.getProperty("nginx.bin.path");
        if(StringUtils.isBlank(nginxBinPath)) {
            nginxEnv.setNginxBin(nginxHome + File.separator + "sbin" + File.separator + "nginx");
        }

        String nginxConf = PropertiesUtils.getProperty("nginx.conf.path");
        if(StringUtils.isBlank(nginxConf)) {
            nginxEnv.setNginxConfPath(nginxHome + File.separator + "conf");
        }

        String nginxConfFile = PropertiesUtils.getProperty("nginx.conffile.path");
        if(StringUtils.isBlank(nginxConfFile)) {
            nginxEnv.setNginxConfFile(nginxEnv.getNginxConfPath() + File.separator + "nginx.conf");
        }
        return nginxEnv;
    }
}
