package com.ccfsoft.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件读取类
 */
public class PropertyConstants {
    private static Properties properties;

    private static void setPropertyInJar(){
        if (properties==null) {
            properties = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try {
                properties.load(loader.getResourceAsStream("data-hdfs.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setPropertyOutJar(String path){
        if (properties==null) {
            properties = new Properties();
            try {
                properties.load(new FileInputStream(path));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 获取jar包内配置文件的key对应的value
     * @param key
     * @return
     */
    public static String getPropertiesKey(String key){
        if (properties==null) {
            setPropertyInJar();
        }
        return properties.getProperty(key, "default");
    }

    /**
     * 获取jar包内配置文件的配置信息
     * @return
     */
    public static Properties getProperties() {
        if (properties==null) {
            setPropertyInJar();
        }
        return properties;
    }

    public static Properties getFullPathProperties(String path) {
        if (properties==null) {
            setPropertyOutJar(path);
        }
        return properties;
    }
}
