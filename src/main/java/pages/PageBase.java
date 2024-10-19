package pages;

import actions.CustomDecorator;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class PageBase {

    // 1- constructor
    // 2- locators
    // 3- public action methods

    // TODO: define web driver
    WebDriver driver;

    // TODO: define constructor
    public PageBase(WebDriver driver)
    {
        this.driver=driver;
    }

    // TODO: define locators
    private final By SideLeftMenu_BTN= By.xpath("//button[text()='Open Menu']");
    private final By LogOut_BTN= By.id("logout_sidebar_link");


    // TODO: define locators
    public PageBase OpenLeftSidMenu(){
         //driver.findElement(SideLeftMenu_BTN).click();
         new CustomDecorator(driver,SideLeftMenu_BTN,100).click();
        return this;
    }
    public boolean LogOutText(){
        return new CustomDecorator(driver,LogOut_BTN,1000).isDisplayed();
        //return driver.findElement(LogOut_BTN).isDisplayed();
    }


    public static void quitBrowser(WebDriver driver) {
        // clear browser localStorage , sessionStorage and delete All Cookies
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
        ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");
        driver.manage().deleteAllCookies();
        driver.quit();
        // kill browser process on background
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
//                Runtime.getRuntime().exec("taskkill /F /IM chrome.exe /T");
            } else if (os.contains("mac") || os.contains("nix") || os.contains("nux")) {
                Runtime.getRuntime().exec("pkill -f chromedriver");
//                Runtime.getRuntime().exec("pkill -f chrome");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // TODO: Capture Screenshot
    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        try {
            FileHandler.copy(takesScreenshot.getScreenshotAs(OutputType.FILE), new File(System.getProperty("user.dir")
                    + "/src/test/resources/Screenshots/" + screenshotName + System.currentTimeMillis() + ".png"));
        } catch (WebDriverException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
