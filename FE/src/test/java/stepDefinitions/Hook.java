package stepDefinitions;

import com.amazon.config.ConfigPropFileData;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amazon.utilities.Common;

public class Hook extends Common {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hook.class);

    @Before
    public void setUp() {
        ConfigPropFileData.getInstance();
        initializeDriver();
        navigateToURL();
    }

    @After
    public void endDriver() {
//        getDriver().quit(); //commented this out so that the reviewer can see the browser instance. Please close the browser instance manually.
    }
}
