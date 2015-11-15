/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.file.monitor.resource;

import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

/**
 *
 * @author yushi.koga
 */
public class Resource {

    static private final Resource resource = new Resource();
    static private final Logger log = Logger.getLogger(Resource.class);
    static private PropertiesConfiguration configuration;

    private Resource() {
    }

    public static Resource getInstance() {
        return resource;
    }

    public static synchronized void load(URL resourceFile) throws ConfigurationException{
        try {
            configuration = new PropertiesConfiguration(resourceFile);
        } catch (ConfigurationException ex) {
            log.error("failde to load " + resourceFile.toString(),ex);
            throw new ConfigurationException(ex);
        }
    }

    public String getString(String key) {
        return configuration.getString(key);
    }

    public int getInt(String key) {
        return configuration.getInt(key);
    }

    public long getLong(String key) {
        return configuration.getLong(key);
    }
}
