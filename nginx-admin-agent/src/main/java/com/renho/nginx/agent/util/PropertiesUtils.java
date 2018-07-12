package com.renho.nginx.agent.util;

/**
 * @author: renho
 * @since: 1.0.0
 */

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    public static Properties props;
    static{
        loadProps();
    }

    private static synchronized void loadProps(){
        props = new Properties();
        InputStream in = null;
        try {
            String configPath = System.getProperty("agent.conf.path");
            if(StringUtils.isBlank(configPath) || !new File(configPath).exists()) {
                logger.debug("start property System.Property not exists, try use Home.Property!");
                configPath = System.getProperty("user.home") + File.separator + "nginxagent" + File.separator + "nginxagent.properties";
            }
            if(!new File(configPath).exists()) {
                logger.debug("start property Home.Property not exists will use Default.Property!");
                configPath = PropertiesUtils.class.getClassLoader().getResource("nginxagent.properties").getFile();
            }
            logger.info("start with properties: " + configPath);
            in = new FileInputStream(configPath);
            props.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }

    public static void main(String[] args) {
        System.out.println(PropertiesUtils.props.size());
    }
}
