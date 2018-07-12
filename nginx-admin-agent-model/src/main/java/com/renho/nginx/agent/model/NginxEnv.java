package com.renho.nginx.agent.model;

/**
 * @author: renho
 * @since: 1.0.0
 */
public class NginxEnv {

    private String nginxHome;
    private String nginxBin;
    private String nginxConfPath;
    private String nginxConfFile;

    public String getNginxHome() {
        return nginxHome;
    }

    public void setNginxHome(String nginxHome) {
        this.nginxHome = nginxHome;
    }

    public String getNginxBin() {
        return nginxBin;
    }

    public void setNginxBin(String nginxBin) {
        this.nginxBin = nginxBin;
    }

    public String getNginxConfFile() {
        return nginxConfFile;
    }

    public void setNginxConfFile(String nginxConfFile) {
        this.nginxConfFile = nginxConfFile;
    }

    public String getNginxConfPath() {
        return nginxConfPath;
    }

    public void setNginxConfPath(String nginxConfPath) {
        this.nginxConfPath = nginxConfPath;
    }
}
