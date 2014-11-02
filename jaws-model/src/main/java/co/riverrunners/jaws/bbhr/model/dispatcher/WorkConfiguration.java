package co.riverrunners.jaws.bbhr.model.dispatcher;

import java.io.Serializable;
import java.util.Properties;

/**
 * Work configuration interface to hold the relevant info for
 * {@link co.riverrunners.jaws.bbhr.model.dispatcher.Worker}
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
public interface WorkConfiguration extends Serializable{

    /**
     * The login url for authentication.
     * @param url Standard url in the form of  protocol://hostname
     */
    public void loginUrl(String url);
    public String getLoginUrl();

    /**
     * The user name to login with.
     * @param userName
     */
    public void setUserName(String userName);
    public String getUserName();

    /**
     * The password to login with
     * @param password
     */
    public void setPassword(String password);
    public String getPassword();

    /**
     * The url to scrap
     * @param url
     */
    public void setUrl(String url);
    public String getUrl();

    /**
     * The class name for the worker to use to process this
     * configuration.  This will be used by the Worker factory to create
     * the proper worker at the right time.
     * @param workerName
     */
    public void setWorkerName(String workerName);
    public String getWorkerName();

    /**
     * Generic properties in case there are other values that need to
     * be used for processing.
     * @param values
     */
    public void setOtherValues(Properties values);
    public Properties getOtherValues();

}
