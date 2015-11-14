package sample.file.monitor.listner;

import java.io.File;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.log4j.Logger;

/**
 *
 * @author yushi.koga
 */
public class ResourceListner extends FileAlterationListenerAdaptor {

    private final Logger log = Logger.getLogger(ResourceListner.class);

    @Override
    public void onFileChange(File file) {
        log.debug("onFileChange:" + file.getName());
        super.onFileChange(file);
    }

    @Override
    public void onFileCreate(File file) {
        log.debug("onFileCreate:" + file.getName());
        super.onFileCreate(file);
    }

    @Override
    public void onFileDelete(File file) {
        log.debug("onFileDelete:" + file.getName());
        super.onFileDelete(file);
    }

}
