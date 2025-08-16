package org.webclock.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webclock.config.Config;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebClockBot {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private final Config config;

    public WebClockBot(Config config) {
        this.config = config;
    }

    public void run() {

        final String hours = "8";

        // Set path to your ChromeDriver executable if not in PATH
        System.setProperty("webdriver.chrome.driver", config.getChromedriver().getPath());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // open in full screen
        // options.addArguments("--headless"); // uncomment for headless mode

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            // Go to the web clock login page
            driver.get(config.getWebclock().getUrl());

            // Switch into the iframe containing the login form
            WebElement iframe = driver.findElement(By.name("main"));
            driver.switchTo().frame(iframe);

            // Fill username and password
            driver.findElement(By.cssSelector("#username")).sendKeys(config.getCredentials().getUsername());
            driver.findElement(By.cssSelector("#password")).sendKeys(config.getCredentials().getPassword());

            // Click the login button
            driver.findElement(By.cssSelector("#emp_login > div:nth-child(5) > div > button")).click();

            // Enter hours
            driver.findElement(By.cssSelector("#EntryHour")).sendKeys(hours);

            // Click the OK button
            String okButtonCss = "#tcHourEntry > div > div > div > div > div:nth-child(4) > div > input";
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(okButtonCss)));
            driver.findElement(By.cssSelector(okButtonCss)).click();

            // Approve time sheet
            if ( getTotalHours(driver) >= 80 ) {

                // Click "Time Management" navbar link
                driver.findElement(By.cssSelector("#navbarText > ul.navbar-nav.mr-auto > li:nth-child(1) > a")).click();

                // Click current time sheet
                String timeSheetAnchorCss = "#timemanagement > div > div > div > div > div.modal-body > div:nth-child(2) > div:nth-child(1) > a";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(timeSheetAnchorCss)));
                driver.findElement(By.cssSelector(timeSheetAnchorCss)).click();

                // Click "Approve" button
                String approveAnchorCssSelector = "#supvtimesheet > div > div:nth-child(12) > div > div > input:nth-child(2)";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(approveAnchorCssSelector)));
                driver.findElement(By.cssSelector(approveAnchorCssSelector)).click();

            }

            // Wait to observe the result (you can replace with explicit waits for post-login elements)
            Thread.sleep(5000);
        } catch (InterruptedException e) { // Compliant; the interrupted state is restored
            logger.log(Level.WARNING, "Interrupted!", e);
            /* Clean up whatever needs to be handled before interrupting  */
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage(), e);
        } finally {
            driver.quit();
        }
    }

    public int getTotalHours(WebDriver driver) {
        // Locate the <td> element
        WebElement tdElement = driver.findElement(By.xpath("//td[contains(text(),'Total Hours:')]"));

        // Get text content (e.g., "Total Hours: 88.00")
        String text = tdElement.getText();

        // Extract the number part
        String hoursStr = text.replace("Total Hours:", "").trim();

        // Convert to double
        return (int) Double.parseDouble(hoursStr);
    }
}
