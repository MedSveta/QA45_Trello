package manager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.WDListener;

import java.lang.reflect.Method;

public class AppManager {
    //private WebDriver driver;
    private EventFiringWebDriver driver;
    public Logger logger = LoggerFactory.getLogger(AppManager.class);

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setup(Method method) {
        //driver = new ChromeDriver();
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.manage().window().maximize();
        logger.info("start method --> "+method.getName());

        driver.register(new WDListener());

    }

    @AfterMethod
    public void tearDown(Method method) {
        logger.info("stop tests --> "+method.getName());
        //if(driver!=null)
        //driver.quit();
    }
}
