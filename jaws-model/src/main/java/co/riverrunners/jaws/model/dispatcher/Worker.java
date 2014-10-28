package co.riverrunners.jaws.model.dispatcher;

/**
 * Work dispatcher runnable for doing work.  Actual work will implement this interface
 * and pushed over the work dispatcher through central to remote clients.
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
public interface Worker extends Runnable {

    /**
     * Sets the work configuration.
     * @param configuration
     */
    public void setConfiguration(WorkConfiguration configuration);

    /**
     *
     * @return
     */
    public WorkConfiguration getConfiguration();


    @Override
    void run();
}
