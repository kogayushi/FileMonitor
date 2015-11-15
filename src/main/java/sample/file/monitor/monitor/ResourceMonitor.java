package sample.file.monitor.monitor;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;
import sample.file.monitor.listner.ResourceListner;
import sample.file.monitor.resource.Resource;

/**
 *
 * @author yushi.koga
 */
public class ResourceMonitor {

    static private final ResourceMonitor resourceMonitor = new ResourceMonitor();
    static private final Logger log = Logger.getLogger(ResourceMonitor.class);
    private long refreshDelay = 4000L;
    private URI monitoringDirectory = null;
    static private final String MONITORING_FILE = "target.txt";

    public static ResourceMonitor getInstance() {
        return resourceMonitor;
    }

    public void setRefreshDelay(long refreshDelay) {
        this.refreshDelay = refreshDelay;
    }

    public void setMonitoringDirectory(URI monitoringDirectory) throws URISyntaxException, MalformedURLException {
        this.monitoringDirectory = monitoringDirectory;
    }

    private ResourceMonitor() {
    }

    public void start() throws ConfigurationException, MalformedURLException, Exception {
        // Guard Clause
        if (monitoringDirectory == null) {
            throw new IllegalStateException("Please set monitoring directory");
        }
        // Generate Monitor. Interval is milli seconds.
        FileAlterationMonitor monitor = new FileAlterationMonitor(refreshDelay);

        // Generate Observer.Set monitoring directory.
        final File dir = new File(monitoringDirectory);
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().equals(MONITORING_FILE);
            }
        };
        FileAlterationObserver observer = new FileAlterationObserver(dir, filter);

        // Generate and register Lister to Observer
        ResourceListner listener = new ResourceListner();
        observer.addListener(listener);

        // Register Observer to Monitor
        monitor.addObserver(observer);

        // Start Monitor
        Resource.load(new URL(monitoringDirectory.toURL(), MONITORING_FILE));
        monitor.start();
        log.info(this.getClass().getSimpleName() + " started monitoring");
    }
}
