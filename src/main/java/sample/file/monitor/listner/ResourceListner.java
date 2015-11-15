package sample.file.monitor.listner;

import java.io.File;
import java.net.MalformedURLException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.log4j.Logger;
import sample.file.monitor.resource.Resource;

/**
 *
 * @author yushi.koga
 */
public class ResourceListner extends FileAlterationListenerAdaptor {

    private final Logger log = Logger.getLogger(ResourceListner.class);

    private void load(File file, String event) {
        try {
            Resource.load(file.toURI().toURL());
        } catch (MalformedURLException ex) {
            log.error(file.getName() + "is " + event + "but failed to load ", ex);
        } catch (ConfigurationException ex) {
            log.error(file.getName() + "is " + event + "but failed to load ", ex);
        }
    }

    @Override
    public void onFileChange(File file) {
        log.info(file.getName() + "is updated");
        load(file, "updated");

    }

    @Override
    public void onFileCreate(File file) {
        log.info(file.getName() + "is created");
        load(file, "created");
    }

    @Override
    public void onFileDelete(File file) {
        log.info(file.getName() + "is deleted");
    }

}
