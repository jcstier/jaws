package co.riverrunners.jaws.test;

import co.riverrunners.jaws.App;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Base class for tests.  Creates server on port 8020 with jmx management on 8021
 * @author Chris Stier <chrisstier@riverrunners.co>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {App.class})
@WebAppConfiguration
@IntegrationTest({"server.port=8020","management.port=8021"})
public class AbstractTest {
}
