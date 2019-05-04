package superheroes.util;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Data
public class PropertiesLoader {

    private static PropertiesLoader INSTANCE;

    private PropertiesLoader (){
        if (INSTANCE != null){
            throw  new IllegalStateException("PropertiesLoader classs instance is already initialized");
        }
    }

    public static PropertiesLoader getInstace (){
        if (INSTANCE == null){
            INSTANCE = new PropertiesLoader();
        }
        return INSTANCE;
    }

    public void loadProperties () {
        Properties properties = new Properties();
        String propertyFile = "application.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(propertyFile);
        try {
            properties.load(stream);
        } catch (IOException e) {
            System.out.println("There were problems loading property file.");
        }
        System.setProperties(properties);
    }

    public int getPropertyValue (String key){
        String value  = System.getProperty(key);
       return  Integer.parseInt(value);
    }
}