package com.renho.nginx.agent.model.request;

import com.renho.nginx.agent.model.NginxEnv;

import java.util.List;
import java.util.Map;

/**
 * @author: renho
 * @since: 1.0.0
 */
public class InitAgentRequest extends BaseAgentRequest {

    private NginxEnv nginxEnv;
    private String nginxConf;
    private List<String> initDirs;
    private Map<String, String> initFiles;

    public String getNginxConf() {
        return nginxConf;
    }

    public void setNginxConf(String nginxConf) {
        this.nginxConf = nginxConf;
    }

    public NginxEnv getNginxEnv() {
        return nginxEnv;
    }

    public void setNginxEnv(NginxEnv nginxEnv) {
        this.nginxEnv = nginxEnv;
    }

    public Map<String, String> getInitFiles() {
        return initFiles;
    }

    public void setInitFiles(Map<String, String> initFiles) {
        this.initFiles = initFiles;
    }

    public List<String> getInitDirs() {
        return initDirs;
    }

    public void setInitDirs(List<String> initDirs) {
        this.initDirs = initDirs;
    }
}
