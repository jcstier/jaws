package co.riverrunners.jaws.bbhr.model.dispatcher;

import java.util.Properties;

/**
 * Default implementation for work configuration.
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
public class DefaultWorkConfiguration implements WorkConfiguration {

    private String url;
    private String loginUrl;
    private String userName;
    private String password;
    private String workerName;
    private Properties otherValues;


    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void loginUrl(String url) {
        this.loginUrl = url;
    }

    @Override
    public String getLoginUrl() {
        return loginUrl;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    @Override
    public String getWorkerName() {
        return workerName;
    }

    @Override
    public void setOtherValues(Properties values) {
        this.otherValues = values;
    }

    @Override
    public Properties getOtherValues() {
        return otherValues;
    }
}
