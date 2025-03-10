package pb;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class Configuration {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public  void ConfigureAppium() throws URISyntaxException, MalformedURLException {

        service = new AppiumServiceBuilder().withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("PelinEmulator");
        options.setApp("//Users//pelinbasormanci//Documents//Mobile Otomation//Appium//src//test//java//resources//ApiDemos-debug.apk");
        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterClass
    public void tearDown() {

        driver.quit();
        service.stop();
    }
}
