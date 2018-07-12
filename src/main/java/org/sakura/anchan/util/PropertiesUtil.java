package org.sakura.anchan.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Anchan on 2017/12/1.
 */
@Slf4j
public class PropertiesUtil {

//        private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

        private static Properties props;

        static {
            String fileName = "anchan.properties";
            props = new Properties();
            try {
                props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
            } catch (IOException e) {
                log.error("配置文件读取异常",e);
            }
        }

    /**
     * 获取字符串
     * @param key
     * @return
     */
    public static String getProperty(String key){
            String value = props.getProperty(key.trim());
            if(StringUtils.isBlank(value)){
                return null;
            }
            return value.trim();
     }

    /**
     * 获取字符串，带有默认值
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getProperty(String key,String defaultValue){

        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            value = defaultValue;
        }
        return value.trim();
    }

    /**
     * 获取Integer
     * @param key
     * @return
     */
    public static Integer getIntegerProperty(String key){
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return null;
        }
        return Integer.valueOf(value.trim());
    }

    /**
     * 获取Integer，带有默认值
     * @param key
     * @param defaultValue
     * @return
     */
    public static Integer getIntegerProperty(String key,String defaultValue){
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            value = defaultValue;
        }
        return Integer.valueOf(value.trim());
    }

    /**
     * 获取Integer
     * @param key
     * @return
     */
    public static Boolean getBooleanProperty(String key){
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return null;
        }
        return Boolean.valueOf(value.trim());
    }

    /**
     * 获取Integer，带有默认值
     * @param key
     * @param defaultValue
     * @return
     */
    public static Boolean getBooleanProperty(String key,String defaultValue){
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            value = defaultValue;
        }
        return Boolean.valueOf(value.trim());
    }

}
