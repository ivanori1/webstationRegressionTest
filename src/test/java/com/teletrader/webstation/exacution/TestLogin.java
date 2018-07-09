package com.teletrader.webstation.exacution;

import com.teletrader.webstation.pages.Header_Navigation;
import com.teletrader.webstation.pages.Login;
import com.teletrader.webstation.pages.Navigation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestLogin {
    WebDriver driver;
    WebDriverWait driverWait;
    Login objLogin;
    Header_Navigation objHeader;
    Navigation objNavigation;
    String baseURL = "http://webstationtest3.ttweb.net/WebStation.aspx";

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        driver = new FirefoxDriver();
        driverWait = new WebDriverWait(driver,20);
        driver.manage().window().maximize();
        driver.get(baseURL);
    }

    /**
     * This test case will login to http://webstationtest3.ttweb.net/WebStation.aspx
     * Verifiy login page as Teletrader
     * Login to application
     */

    @Test(priority = 0)
    public void test_Login_page() throws InterruptedException {
        //Create login page object
        objLogin = new Login(driver);
        objHeader = new Header_Navigation(driver);
        objNavigation = new Navigation(driver);


        //test case: 1.Failed login – EULA is not accepted
        objLogin.failedLoginEulaX("", "");
        Assert.assertTrue(objLogin.getLoginError().contains("You have to accept the End User License Agreement"));

        //test case: 2.Failed login – EULA is accepted
        objLogin.failedLoginEulaO("", "");
        Assert.assertTrue(objLogin.getLoginError().contains("The username or password is incorrect"));

        // test case 3: Failed login – valid user name / invalid password
        objLogin.failedLoginEulaO("ivan.coric91", "");
        Assert.assertTrue(objLogin.getLoginError().contains("The username or password is incorrect"));

        // test case 4:	Failed login – valid user name / empty  password
        objLogin.failedLoginEulaO("ivan.coric91", "");
        Assert.assertTrue(objLogin.getLoginError().contains("The username or password is incorrect"));

        // test case 5:	Failed login – invalid user
        objLogin.failedLoginInvalidU("test.test18", "test12");
        Assert.assertTrue(objLogin.getLoginError().contains("This user account is disabled"));

        // test case 6:	Failed login – invalid user name / valid password (German login dialog)
        objLogin.failedLoginGerm("123", "ictrader123");
        Assert.assertTrue(objLogin.getLoginError().contains("Benutzername oder Passwort ist falsch"));

        // test case 7:	Failed login – valid user name / invalid password (German login dialog)
        objLogin.failedLoginGermXPass("ivan.coric91", "123");
        Assert.assertTrue(objLogin.getLoginError().contains("Benutzername oder Passwort ist falsch"));


        //test case 8: Successful login
        objLogin.succesLogin("ivan.coric91", "ictrader123");
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logo-ws")));
        Assert.assertTrue(driver.findElement(By.id("logo-ws")).isDisplayed());
        objHeader.Logout();


        //test case 9: Login with low permission DPA-AFX user
        driverWait.until(ExpectedConditions.elementToBeClickable(objLogin.login));
        objLogin.succesLogin("dpaafx.web92", "TTtest123");
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logo-ws")));
        Assert.assertTrue(driver.findElement(By.id("logo-ws")).isDisplayed());
        objHeader.Logout();


        //test case 10:	Login with low permission ilirika C3X user ( teletrad.95 / 7zvTFDRY )
        driverWait.until(ExpectedConditions.elementToBeClickable(objLogin.login));
        objLogin.succesLogin("teletrad.95", "7zvTFDRY");
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logo-ws")));
        Assert.assertTrue(driver.findElement(By.id("logo-ws")).isDisplayed());
        objHeader.Logout();


        //testcase 11: Expired session
        driverWait.until(ExpectedConditions.elementToBeClickable(objLogin.login));
        driver.get(baseURL + "?timeout=5000");
        objLogin.sessionExpiredAutoLoginX("ivan.coric91", "ictrader123");
        driverWait.until(ExpectedConditions.elementToBeClickable(By.id("relogButton")));
        driver.findElement(By.id("relogButton")).click();




     // quit browser
     driver.quit();
    }
}