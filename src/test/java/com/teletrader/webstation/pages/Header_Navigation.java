package com.teletrader.webstation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;


public class Header_Navigation {
    WebDriver driver;


    //app icon
    By appIcon = By.id("appIcon");

    //Search
    By searchBox = By.id("searchField");
    By searchButton = By.id("searchButton");
    By searchTable = By.id("searchFieldResults");
    By detailTable = By.cssSelector("[data-link-type='kursliste']");
    By searchHeader = By.cssSelector("#searchFieldResults h2");
    By searchTermStrong = By.tagName("strong");
    By detailPageIconSearch = By.cssSelector("#searchFieldResults .detail-pages-icon");


    //Logo
    By logo = By.id("logo-ws");

    //Header chart
    By headerChart = By.id("headerChart");
    By asiaSymbol = By.className("asia-symbol");
    By europeSymbol = By.className("europe-symbol");
    By americaSymbol = By.className("america-symbol");

    //Favorite button
    By favoritePopUp = By.id("favoritesButton");

    //Help button
    By helpButton = By.id("helpButton");

    //Open in new browser
    By newWindow = By.id("newWindow");

    //Layout button
    By layout = By.cssSelector(".layout-button");

    //Favorite page
    By favoritePageOff = By.cssSelector(".fl_button_off");
    By getFavoritePageOn = By.id("fl_button");

    //User button
    By userButton = By.id("user-button");
    By accountSettings = By.cssSelector("#user-menu [href='personal_registration.aspx']");
    By setAsStartPage = By.cssSelector("[onclick*='setAsStartPage']");
    By pushActivator = By.id("rtActivator");
    By print = By.id("printButton");
    By excelAdds = By.cssSelector("[href*='excel']");
    By logout = By.cssSelector("[href='Logout.aspx']");

    //Footer
    By feedbackText = By.className("feedback-text");
    By sendFeedback = By.xpath("//*[@value='Send']");

    //Navigation button

    By markets = By.cssSelector("[href='securities_overview.aspx']");
    By currencies = By.cssSelector("[href='currencies_Currencies.aspx']");
    By commodities = By.cssSelector("[href='commodities.aspx']");
    By fixedIncome = By.cssSelector(".navigation-vertical [href='bonds_governmentyields.aspx']");
    By futures = By.cssSelector("[href*='Futures']");
    By trumpEffect = By.cssSelector("[href='trump_effect.aspx'");
    By smartBackTester = By.cssSelector("[href='portfolio_backtester.aspx']");
    By screener = By.cssSelector("[href='screener_overview.aspx']");
    By news = By.cssSelector("[href='news_handler.aspx']");
    By calendar = By.cssSelector("[href='company_calendar.aspx?default']");

    //Logout
    public void Logout() {
        driver.findElement(userButton).click();
        driver.findElement(logout).click();
    }


    public Header_Navigation(WebDriver driver) {
        this.driver = driver;
    }
    // WebDriverWait driverWait = new WebDriverWait(driver, 15);

    public void clickNews() {
        driver.findElement(news).click();

    }

    public void setSetAsStartPage() {
        driver.findElement(userButton).click();
        driver.findElement(setAsStartPage).click();
    }

    public void clickOnAppIcon() {
        driver.findElement(appIcon).click();
    }

    public void typeSearchField(String strSearchTerm) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(strSearchTerm);
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    public void homeButton() {
        //2.1.1 Click on the news button in nav-bar and then customize and control button   [>Set as start page],
        // then click on any other nav-bar button and finally appIcon
        this.setSetAsStartPage();
        String startPage = driver.getCurrentUrl();
        this.clickOnAppIcon();
        Assert.assertEquals(driver.getCurrentUrl(), startPage+"&startpage=yes");
    }

    /**
     * @param strSearchTerm
     * @return
     */
    //2.2. Find symbol using live search
    //1. Start typing „bank “in the „Live Search “field
    public void typeSearch(String strSearchTerm) {
        this.typeSearchField(strSearchTerm);
    }

    public void typeBank() {
        this.typeSearch("bank");
        WebElement table = driver.findElement(searchTable);
        driver.manage().timeouts().implicitlyWait(14, TimeUnit.SECONDS);
        List<WebElement> rows = table.findElements(searchTermStrong);
        java.util.Iterator<WebElement> i = rows.iterator();
        boolean contains = false;
        if (rows.get(0).getText().equalsIgnoreCase((rows.get(1).getText()))) {
        }
        for (int k = 0; k < rows.size(); k++) {
            String firstString = rows.get(k).getText();
            for (int s = 1; s < rows.size(); s++) {
                String secondString = rows.get(s).getText();
                if (secondString.equalsIgnoreCase(firstString)) {
                    contains = true;
                }
            }
        }

        assertTrue(contains);

    }

    public void notFoundAnyResults() {

        this.typeSearch("//");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElement(By.cssSelector("[data-link-type='fullsearch']")).isDisplayed());
    }

    public void changeToGerman() {
        this.typeSearch("--d");
        driver.findElement(searchButton).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        assertTrue(driver.findElement(By.id("footer")).getText().contains(
                "TeleTrader Software GmbH. Alle Rechte vorbehalten."));
    }

    public void searchSymbolBMW() {
        this.typeSearch("bmw");
        WebElement bmwSearch = driver.findElement(By.cssSelector("#liveSearch [data-id='tts-458822']"));
        String title = bmwSearch.getAttribute("title");
        bmwSearch.click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".header-name")).getText(), title);
    }

public void clickDetailTable() {
        this.typeSearch("bmw");
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.findElement(detailTable).click();
    //TODO Make list of liveSearch results and to compare it with detailPage results
   /* WebElement table = driver.findElement(searchTable);
    driver.manage().timeouts().implicitlyWait(14, TimeUnit.SECONDS);
    List<WebElement> header = table.findElements(searchHeader);
    driver.findElement(searchButton).click();
    List<WebElement> detailHeader = driver.findElements(By.className("header-name"));
    System.out.println(detailHeader);
    */
    }
    public void clickDetailSearch() {
        typeSearch("bmw");
        driver.findElement(searchButton).click();
        //Assert.assertEquals("Search",driver.findElement(By.className("title")).getText());
        //TODO make correct assertation, current gives [NEWS], instead of Search
    }

    //Header Chart
    public void cickOnAsiaSymbol() {
        driver.findElement(asiaSymbol).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // TODO find element location, to click on dropdown symbol
    }
    public void compareHeaderChange() {
        driver.findElement(By.cssSelector("#market-tab-link")).click();
       String val1 = driver.findElement(By.cssSelector("#left_514562\\|changePercent")).getText();
       String  val2 = "+"+driver.findElement(By.cssSelector("#headerChart_514562\\|changePercent")).getText();
       // change precentage is equal at DAX symbol of makret tab and in header chart
       Assert.assertEquals(val1,val2);
    }
    public void clickOnEURUSD() {
        driver.findElement(By.cssSelector(".america-symbol .header-chart-value-holder")).click();
        driver.findElement(
                By.cssSelector("#header-symbol-holder-america [data-symbol=\"america\\|USDEUR_FXInt\"]:nth-of-type(4) span")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".america-symbol .header-chart-value-holder")).getText().contains(
                "USD/EUR"
        ));
    }
    public void clickOnHelp() {
        driver.findElement(helpButton).click();
       //TODO assert new window is help page and to close window
    }
    public void openInNewBrowser() {
        driver.findElement(newWindow).click();
        //TODO assert new window is identical page and to close window
    }
    public void addToFavorites() {

    }
}



