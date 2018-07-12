package com.renho.nginx.agent.support.service.impl;

import com.renho.nginx.agent.exception.AgentException;
import com.renho.nginx.agent.exception.AgentInitException;
import com.renho.nginx.agent.instance.Nginx;
import com.renho.nginx.agent.model.InitContent;
import com.renho.nginx.agent.model.InitResult;
import com.renho.nginx.agent.model.NginxEnv;
import com.renho.nginx.agent.model.SimpleStatusType;
import com.renho.nginx.agent.support.service.INginxAgentSupport;
import com.renho.nginx.agent.support.service.initcomponent.INeedInit;
import com.renho.nginx.agent.util.NginxEnvConfigUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: renho
 * @since: 1.0.0
 */
@Service
public class NginxAgentSupportImpl implements INginxAgentSupport {

    @Autowired
    private List<INeedInit> needInits;

    @Override
    public InitResult init(InitContent initContent) {

        InitResult result = new InitResult();
        result.setStatus(SimpleStatusType.SUCCESS);

        if(!isValidEnv(initContent)) {
            throw new AgentException("invalid env!");
        }

        initDir(initContent.getInitDirs());
        initFile(initContent.getInitFiles());

        if(!checkConf(initContent)) {
            throw new AgentException("nginx conf check fail!");
        }
        return result;
    }

    private boolean isValidEnv(InitContent initContent) {
        return Nginx.INSTANCE.isEnvValid(initContent.getNginxEnv());
    }

    @Override
    public InitContent initContentMyself() {

        InitContent initContent = new InitContent();

        NginxEnv nginxEnv = NginxEnvConfigUtil.getNginxEnvConfig(null);
        initContent.setNginxEnv(nginxEnv);

        for (INeedInit needInit: needInits) {
            needInit.make(initContent);
        }

        return initContent;
    }

    private boolean checkConf(InitContent initContent) {
        return Nginx.INSTANCE.check(initContent.getNginxEnv());
    }

    private void initFile(Map<String,String> initFiles) {

        if(null==initFiles || 0==initFiles.size()) {
            return;
        }

        for(Map.Entry<String, String> fileContent: initFiles.entrySet()) {
            File file = new File(fileContent.getKey());
            try {
                FileUtils.writeStringToFile(file, fileContent.getValue(), "utf-8");
            } catch (IOException e) {
                throw new AgentInitException("init file exception");
            }
        }

    }

    private void initDir(List<String> initDirs) {

        if(null==initDirs || 0==initDirs.size()) {
            return;
        }
        for(String dir: initDirs) {
            File dirFile = new File(dir);
            if(!dirFile.exists()) {
                try {
                    FileUtils.forceMkdir(dirFile);
                } catch (IOException e) {
                    e.getMessage();
                    throw new AgentInitException("init dir exception");
                }
            }
        }
    }

}
