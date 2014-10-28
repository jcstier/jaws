package co.riverrunners.bbhr.worker;

import co.riverrunners.jaws.model.dispatcher.WorkConfiguration;
import co.riverrunners.jaws.model.dispatcher.Worker;

/**
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
public class BBHRWorker implements Worker {
    @Override
    public void setConfiguration(WorkConfiguration configuration) {

    }

    @Override
    public WorkConfiguration getConfiguration() {
        return null;
    }

    /**
     * This is where the scraping code will go.  The set WorkConfiguration should have everything needed
     * to processes the scraping.
     */
    @Override
    public void run() {

    }
}
