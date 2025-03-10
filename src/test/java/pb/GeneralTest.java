package pb;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeneralTest extends Configuration {

    @Test
    public void AllMethods() throws InterruptedException {

        driver.findElement(AppiumBy.accessibilityId("Graphics")).click();

        //Scroll down to "Touch Paint"
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Touch Paint\"))")).click();

        //Drag and drop
        WebElement source = driver.findElement(By.className("android.view.View"));
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) source).getId(),
                "startX", 252, "startY", 455,
                "endX", 671, "endY", 1500
        ));

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        //Scroll down to "Patterns"
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Patterns\"))")).click();

        //Swipe
        WebElement firstImage = driver.findElement(AppiumBy.className("android.view.View"));
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) firstImage).getId(),
                "direction", "down",
                "percent", 1
        ));

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();

        driver.findElement(AppiumBy.accessibilityId("9. Switch")).click();

        //Click checkbox and switch bar
        driver.findElement(By.className("android.widget.CheckBox")).click();
        driver.findElement(AppiumBy.xpath("(//android.widget.Switch[@resource-id='android:id/switch_widget'])[1]")).click();

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.findElement(AppiumBy.accessibilityId("7. Fragment")).click();

        //Long press
        WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='List preference']"));
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement)element).getId(),"duration",1000));

        //Verify the alert
        String alertText = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alertText, "Choose one");
        System.out.println("Menu Text: " + alertText);

        //Choose an option from the alert
        driver.findElement(AppiumBy.xpath("//android.widget.CheckedTextView[@text='Beta Option 02']")).click();

        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Edit text preference']")).click();

        //Copy paste
        driver.setClipboardText("Catssss");
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys(driver.getClipboardText());
        driver.findElement(AppiumBy.id("android:id/button1")).click();
        Thread.sleep(2000);

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        Thread.sleep(2000);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        //Scroll down to "Image Switcher"
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"ImageSwitcher\"))")).click();

        //Rotate the device
        DeviceRotation landScape = new DeviceRotation(0, 0, 90);
        driver.rotate(landScape);

        //Choose the third image
        driver.findElement(AppiumBy.xpath("//android.widget.Gallery[@resource-id='io.appium.android.apis:id/gallery']/android.widget.ImageView[3]")).click();
        Thread.sleep(2000);

        driver.rotate(ScreenOrientation.PORTRAIT);
        Thread.sleep(2000);
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }
}
