package org.example;
import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.image.UnableToCompareImagesException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

public class Main {
    public static void main(String[] args) throws Throwable{

        System.setProperty("web-driver.chrome.driver","C:\\Users\\manid\\Downloads\\chromedriver-win64\\chromedriver-win64");
        WebDriver driver= new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().window().maximize();

        driver.get("https://american-technology.office-qa.auzmor.com/login");

        driver.findElement(By.name("email")).sendKeys("monika.gedam@american-technology.net");
        driver.findElement(By.name("password")).sendKeys("Test@123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.findElement(By.name("arrowDownOutline")).click();
        driver.findElement(By.xpath("//button[@data-testid='user-menu-profile']")).click();
//        driver.findElement(By.xpath("//div[@class='absolute bg-white rounded-full p-[5px] cursor-pointer top-1 right-1']")).click();

        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
        fileInput.sendKeys("C:\\Users\\manid\\OneDrive\\Desktop\\river.jpg");
        driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/div[3]/div[3]/div[2]/button[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div/form/div[5]/button[2]")).submit();
        Thread.sleep(1000);


        WebElement logo = driver.findElement(By.xpath("//img[@class='object-cover h-full w-full rounded-full']"));

        String imageSrc = logo.getAttribute("src");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(imageSrc);
        Thread.sleep(6000);
        File image = new File("C:\\Users\\manid\\OneDrive\\Desktop\\river.jpg");
        BufferedImage expectedImage = ImageIO.read(image);
        String OldImagePath = "C:\\Users\\manid\\Downloads";
        try {
            boolean isImagesame = Shutterbug.shootPage(driver, Capture.FULL, 500, true).withName("Actual").equalsWithDiff(expectedImage, OldImagePath, 0);
        } catch (UnableToCompareImagesException e) {
            System.out.println("Images dimensions mismatch: " + e.getMessage());

        }
//        driver.quit();
    }
}


