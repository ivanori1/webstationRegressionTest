package com.teletrader.webstation.pages;


import com.teletrader.webstation.exacution.TestLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

    WebDriver driver;


    // paceholder
    By username = By.name("userName");
    By password = By.name("password");
    // checkbox
    By eula = By.cssSelector("[for='eulaAccepted'].checkBoxLabel");
    By autoLogin = By.cssSelector("[for='autologin'].checkBoxLabel");
    //button
    public By login = By.id("loginUser");
    // ReLog button
    By relog = By.id("relogButton");
    //error container
    By errorContainer = By.className("error_container_inner");
    //info container
    By infoContainer = By.className("login-info");

    public Login(WebDriver driver) {
        this.driver = driver;
    }


    // Set username in paceholder
    public void setUserName(String strUserName) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(strUserName);
    }

    //Set password in placeholder
    public void setPassword(String strPassword)
    {
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(strPassword);
    }

    // Check EULA
    public void checkEula() {
        boolean e = driver.findElement(By.xpath("//*[@id=\"eulaAccepted\"]")).isSelected();
        if (e == false)
            driver.findElement(eula).click();

    }

    // Uncheck EULA
    public void unCheckEula() {
        boolean e = driver.findElement(By.xpath("//*[@id=\"eulaAccepted\"]")).isSelected();
        if (e == true)
            driver.findElement(eula).click();

    }

    //Check autologin
    public void checkAutoLogin() {
        boolean a = driver.findElement(By.xpath("//*[@id=\"autologin\"]")).isSelected();
        if (a == false)
            driver.findElement(autoLogin).click();
    }

    //Uncheck autologin
    public void uncheckAutoLogin() {
        boolean a = driver.findElement(By.xpath("//*[@id=\"autologin\"]")).isSelected();
        if (a == true)
            driver.findElement(autoLogin).click();
    }


    // Change to German
    public void changeLangGer() {
        driver.findElement(By.id("langButton1")).click();
        driver.findElement(By.id("langNavide")).click();
        ;
    }

    // Change to English
    public void changeLangEng() {
        driver.findElement(By.id("langButton1")).click();
        driver.findElement(By.id("langNavien")).click();
    }
    // Click on login button
    public void clickLogin() {
        driver.findElement(login).click();
    }

    //Click reLog button
    public void clickRelog() {
        driver.findElement(relog).click();
    }
    //get info text
    public String getLoginInfo() {
        return driver.findElement(infoContainer).getText();
    }
    //get the error
    public String getLoginError() {
        return driver.findElement(errorContainer).getText();
    }


    /**
     * This POM method will be exposed in test case to login in the application
     *
     * @param strUserName
     * @param strPassword
     * @return
     */
    public void failedLoginEulaX(String strUserName, String strPassword) {
        System.out.println("test case 1: Failed login – EULA is not accepted");
        //1.1.1 Username and password fields are empty
        //1.1.2 “Stay logged in” option is unchecked
        this.uncheckAutoLogin();
        //1.1.3 “I accept EULA” option is unchecked
        this.unCheckEula();
        //1.1.4 Click login button
        this.clickLogin();

    }

    public void failedLoginEulaO(String strUserName, String strPassword) {
        System.out.println("test case 2: Failed login – EULA is accepted");
        //1.1.1 Username and password fields are empty
        //1.2.2 “Stay logged in” option is unchecked
        this.uncheckAutoLogin();
        //1.2.3 “I accept EULA” option is checked
        this.checkEula();
        //1.2.4 Click login button
        this.clickLogin();
    }


    public void failedLoginInvalidU(String strUserName, String strPassword) {
        System.out.println("test case 4: Failed login – invalid user");
        //1.4.1 Enter user name “test.test18”
        this.setUserName(strUserName);
        //1.4.2 Enter password "test12"
        this.setPassword(strPassword);
        //1.4.3 “I accept EULA” option is checked
        this.checkEula();
        //1.4.4 Click login button
        this.clickLogin();

    }
    public void failedLoginGerm (String strUserName, String strPassword) {
        System.out.println("test case 6: Failed login – invalid user name / valid password (German login dialog)");
        //1.6.1Change Login dialog language to Deutsch
        this.changeLangGer();
        //1.6.2 Enter invalid username
        //1.6.3 Enter valid password
        //1.6.4 “I accept EULA” option is checked
        this.checkEula();
        //1.4.4 Click login button
        this.clickLogin();
    }
    public void failedLoginGermXPass (String strUserName, String strPassword) {
        System.out.println("test case 7: Failed login – valid user name / invalid password (German login dialog)");
        //1.7.7 Change Login dialog language to Deutsch
        this.changeLangGer();
        //1.7.2 Enter valid user name -
        //1.7.3 Enter invalid password
        //1.7.4 “I accept EULA” option is checked
        this.checkEula();
        //1.7.5 Click on “Login” button
        this.clickLogin();
    }
    public void succesLogin(String strUserName, String strPassword) throws InterruptedException {

        System.out.println("test case 8: Successful login");
        //1.8.1 Change language to English
        //this.changeLangEng();
        //1.8.2 Enter valid user name
        this.setUserName(strUserName);
        //1.8.3 Enter valid Password
        this.setPassword(strPassword);
        //1.8.4 “I accept EULA” option is checked
        this.checkEula();
        //1.8.5 Click login button
        this.clickLogin();
    }
    public void sessionExpiredAutoLoginX (String strUserName, String strPassword) throws InterruptedException {

        //1.11.2 Enter valid user name
        this.setUserName(strUserName);
        //1.8.3 Enter valid Password
        this.setPassword(strPassword);
        //1.8.4 “I accept EULA” option is checked
        this.checkEula();
        //1.11.4 “Stay logged in” option is unchecked
        this.uncheckAutoLogin();
        //1.8.5 Click login button
        this.clickLogin();
    }
}
