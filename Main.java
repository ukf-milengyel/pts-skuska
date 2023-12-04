package sk.lengyel;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class Main {

    final static String AIS_LOGIN = "";
    final static String AIS_PASSWORD = "";

    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.gecko.driver", "/home/miro/Downloads/geckodriver");
        WebDriver driver = new FirefoxDriver();

        // index stránka
        driver.get("https://ais2.ukf.sk/ais/start.do");
        Thread.sleep(1000);

        // cookies dialog
        try{
            driver.findElement(By.id("accept-cookies")).click();
            System.out.println("Accepting cookies!");
        } finally {
            System.out.println("Cookies already accepted!");
        }
        Thread.sleep(1000);

        // login
        driver.findElement(By.id("login")).sendKeys(AIS_LOGIN);
        driver.findElement(By.id("heslo")).sendKeys(AIS_PASSWORD);
        driver.findElement(By.id("login-form-submit-btn")).click();
        Thread.sleep(500);

        // rozvrh
        driver.findElement(By.cssSelector("button[title='Môj rozvrh, vyhľadávanie rozvrhu']")).click();
        Thread.sleep(500);

        // vytvorit screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./screenshot.png"));
        Thread.sleep(500);

        // navigovat na odhlasenie
        driver.navigate().to("https://ais2.ukf.sk/ais/logout.do");
        Thread.sleep(1000);

        // koniec
        driver.quit();
    }
}