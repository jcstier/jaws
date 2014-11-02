package co.riverrunners.jaws.bbhr.model.dispatcher;

import java.io.Serializable;

/**
 * Work dispatcher runnable for doing work.  Actual work will implement this interface
 * and pushed over the work dispatcher through central to remote clients.
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
public interface Worker extends Runnable, Serializable {

    /**
     * Sets the work configuration.
     * @param configuration
     */
    public void setConfiguration(WorkConfiguration configuration);

    /**
     * Use this to setup your worker.  The work dispatcher will call this method before it calls
     * run.
     */
    public void postRun();

    @Override
    void run();
}
