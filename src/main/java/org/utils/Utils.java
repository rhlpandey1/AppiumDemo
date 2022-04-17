package org.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {
    /*
     * This method is used to get the property values from .properties file
     */
    public static String getProperty(String key) throws IOException, IOException {
        Properties prop=new Properties();
        String baseProjectPath = System.getProperty(Constants.USER_DIR);
        FileInputStream fis=new FileInputStream(baseProjectPath.concat(Constants.CONFIG_PROPERTY));
        prop.load(fis);
        String value=prop.getProperty(key).trim();
        return value;
    }
}
