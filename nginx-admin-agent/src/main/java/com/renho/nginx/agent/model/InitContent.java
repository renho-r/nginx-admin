package com.renho.nginx.agent.model;

import java.util.List;
import java.util.Map;

/**
 * @author: renho
 * @since: 1.0.0
 */
public class InitContent {

    private NginxEnv nginxEnv;
    private List<String> initDirs;
    private Map<String, String> initFiles;

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
