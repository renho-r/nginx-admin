package com.renho.nginx.agent.util;

/**
 * @author: renho
 * @since: 1.0.0
 */

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    public static Properties props;
    static{
        loadProps();
    }

    private static synchronized void loadProps(){
        props = new Properties();
        InputStream in = null;
        try {
            String configPath = System.getProperty("agent.conf.path");
            if(StringUtils.isBlank(configPath)) {
                configPath = System.getProperty("user.home") + File.separator + "nginxagent" + File.separator + "nginxagent.properties";
            }
            props.load(new FileInputStream(configPath));
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
