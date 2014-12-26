package co.riverrunners.jaws.bbhr.worker;

import co.riverrunners.jaws.bbhr.model.BBHRLineItem;
import co.riverrunners.jaws.bbhr.model.dispatcher.WorkConfiguration;
import co.riverrunners.jaws.bbhr.model.dispatcher.Worker;
import co.riverrunners.jaws.bbhr.util.CSVUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//TODO who creates this? -- Dispatcher will
//TODO who passes params? -- Dispatcher will setConfiguration
//TODO make this into a proper worker class.

/**
 * A worker class that gets the Detail Sales Traffic by SKU Report
 * from Amazon Seller Central and parses it.
 *
 * @author David Nelson <madhacker@tangouniform.org>
 */
public class BBHRWorker implements Worker {
    // Keeping until this is in db and set from dispatcher.
    /*
    protected static final String loginPageURL = "https://sellercentral.amazon.com";
    protected static final String username = "";
    protected static final String password = "";
    protected static final String reportURL = "https://sellercentral.amazon.com/gp/site-metrics/load/csv/BusinessReport-{0}.csv?reportID={1}&sortIsAscending={2}&sortColumn={3}&fromDate={4}&toDate={5}&cols={6}&rows={7}&dateUnit={8}&currentPage={9}&runDate={10}";
    */

    // Set from work configuration
    private String loginPageURL;
    private String username;
    private String password;
    private String reportURL;

    // params that might not change
    protected static final String userNameFieldId = "username";
    protected static final String passwordFieldId = "password";
    protected static final String signInButtonId = "sign-in-button";
    private String urlEncoding = "UFT-8";
    protected static final String reportID = "102:DetailSalesTrafficBySKU";
    protected static final String sortIsAscending = "0";
    protected static final String sortColumn = "13";
    protected static final String cols = "/c0/c1/c2/c3/c4/c5/c6/c7/c8/c9/c10/c11/c12";
    protected static final String rows = "";
    protected static final String dateUnit = "1";
    protected static final String currentPage = "0";
    protected static final String runDate = "";

    private WebDriver driver;
    private SimpleDateFormat dateFormat;
    private Logger logger;

    public BBHRWorker() {
    }

    @Override
    public void postRun() {
        logger = LoggerFactory.getLogger(BBHRWorker.class);
        driver = new HtmlUnitDriver();
        dateFormat = new SimpleDateFormat("MM-dd-yy");
    }


    protected void login() {

        // Get to the login page
        driver.get(loginPageURL);

        // Get the username text field and populate it with our username
        WebElement userNameField = driver.findElement(By.id(userNameFieldId));
        userNameField.sendKeys(username);

        // Get the password field and populate it with our password
        WebElement passwordField = driver.findElement(By.id(passwordFieldId));
        passwordField.sendKeys(password);

        // Get the sign in button and click it.
        WebElement signInButton = driver.findElement(By.id(signInButtonId));
        signInButton.click();
    }

    protected String getReport() {
        String report = "";
        try {
            String todaysDate = dateFormat.format(new Date());
            String fromDate = "09/08/2014";
            String toDate = "10/08/2014";

            Object[] args = new String[]{
                    URLEncoder.encode(todaysDate, urlEncoding),
                    URLEncoder.encode(reportID, urlEncoding),
                    URLEncoder.encode(sortIsAscending, urlEncoding),
                    URLEncoder.encode(sortColumn, urlEncoding),
                    URLEncoder.encode(fromDate, urlEncoding),
                    URLEncoder.encode(toDate, urlEncoding),
                    URLEncoder.encode(cols, urlEncoding),
                    URLEncoder.encode(rows, urlEncoding),
                    URLEncoder.encode(dateUnit, urlEncoding),
                    URLEncoder.encode(currentPage, urlEncoding),
                    URLEncoder.encode(runDate, urlEncoding)};

            String realURL = MessageFormat.format(reportURL, args);

            logger.debug("Here's the URL we are using: " + realURL);
            driver.get(realURL);
            report = driver.getPageSource();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return report;
    }

    @Override
    public void setConfiguration(WorkConfiguration configuration) {
        this.username = configuration.getUserName();
        this.password = configuration.getPassword();
        this.loginPageURL = configuration.getLoginUrl();
        this.reportURL = configuration.getUrl();

    }


    @Override
    public void run() {
        login();

        String report = getReport();
        BufferedReader reader = new BufferedReader(new StringReader(report));
        try {
            String line = reader.readLine(); // throw away first line as it just
            // contains column titles...
            line = reader.readLine();
            while (line != null) {
                // logger.debug(line);
                line = CSVUtils.unquote(line);
                String[] elements = CSVUtils.unquote(line.split("\",\""));
                BBHRLineItem lineItem = new BBHRLineItem();
                try {
                    lineItem = new BBHRLineItem(elements);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                logger.debug(lineItem.toString());
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Clean up nicely.
        driver.quit();
    }

}
