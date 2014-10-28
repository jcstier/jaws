package co.riverrunners.jaws.model.dispatcher;

import java.io.Serializable;

/**
 *
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
public interface WorkConfiguration extends Serializable{


    public void setUrl(String url);
    public String getUrl();

    public void setUserName(String userName);
    public String getUserName();

    public void setPassword(String password);
    public String getPassword();

    public void setWorkerName(String workerName);
    public String getWorkerName();


}
