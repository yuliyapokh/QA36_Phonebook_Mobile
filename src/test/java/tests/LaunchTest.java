package tests;

import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

public class LaunchTest extends AppiumConfig
{
    @Test
    public void launchTest(){

      String version = new SplashScreen(driver)
                .getVersionText();
        System.out.println(version);
        Assert.assertTrue(version.contains("1.0.0"));

    }
}
