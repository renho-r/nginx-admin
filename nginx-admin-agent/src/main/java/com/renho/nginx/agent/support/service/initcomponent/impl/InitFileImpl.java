package com.renho.nginx.agent.support.service.initcomponent.impl;

import com.renho.nginx.agent.model.InitContent;
import com.renho.nginx.agent.model.NginxEnv;
import com.renho.nginx.agent.support.service.impl.NginxAgentSupportImpl;
import com.renho.nginx.agent.support.service.initcomponent.INeedInit;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: renho
 * @since: 1.0.0
 */
@Component
public class InitFileImpl implements INeedInit {

    @Override
    public void make(InitContent initContent) {
        NginxEnv nginxEnv = initContent.getNginxEnv();
        Configuration conf = new Configuration();
        conf.setClassForTemplateLoading(NginxAgentSupportImpl.class, "/nginx");
        Map<String, String> initFiles = new HashMap<>();
        try {

            Map<String, String> params = new HashMap<>();
            params.put("config_path_server", INeedInit.SERVER_PATH);

            Template nginxConfTpl = conf.getTemplate("nginx.tpl");
            StringWriter outer = new StringWriter();
            nginxConfTpl.process(params, outer);
            String nginxConfStr = outer.toString();
            initFiles.put(nginxEnv.getNginxConfFile(), nginxConfStr);

            params = new HashMap<>();
            params.put("config_path_locations", INeedInit.LOCATION_PATH);

            nginxConfTpl = conf.getTemplate("nginx_server.tpl");
            outer = new StringWriter();
            nginxConfTpl.process(params, outer);
            nginxConfStr = outer.toString();



            initFiles.put(nginxEnv.getNginxConfPath() + File.separator + INeedInit.SERVER_PATH + File.separator + "nginx_server.conf", nginxConfStr);
            initContent.setInitFiles(initFiles);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

}
