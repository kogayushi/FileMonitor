/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.file.monitor.entry;

import java.io.File;
import org.apache.log4j.Logger;
import sample.file.monitor.monitor.ResourceMonitor;

/**
 *
 * @author yushi.koga
 */
public class Main {

    static final Logger log = Logger.getLogger(Main.class);

    public static void main(String... args) {
        try {
            ResourceMonitor monitor = ResourceMonitor.getInstance();
            monitor.setRefreshDelay(500L);
            monitor.setMonitoringDirectory(new File("D:\\target").toURI());
            monitor.start();
            while (true) {
                Thread.sleep(50);
            }
        } catch (Exception ex) {
            log.error(ex);
        }
    }

}
