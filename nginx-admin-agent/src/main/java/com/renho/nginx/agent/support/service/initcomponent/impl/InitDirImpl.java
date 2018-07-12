package com.renho.nginx.agent.support.service.initcomponent.impl;

import com.renho.nginx.agent.model.InitContent;
import com.renho.nginx.agent.model.NginxEnv;
import com.renho.nginx.agent.support.service.initcomponent.INeedInit;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: renho
 * @since: 1.0.0
 */
@Component
public class InitDirImpl implements INeedInit {

    @Override
    public void make(InitContent initContent) {
        NginxEnv nginxEnv = initContent.getNginxEnv();
        List<String> initDirs = new ArrayList<>();
        initDirs.add(nginxEnv.getNginxConfPath() + File.separator + INeedInit.SERVER_PATH);
        initDirs.add(nginxEnv.getNginxConfPath() + File.separator + INeedInit.LOCATION_PATH);
        initContent.setInitDirs(initDirs);
    }

}
