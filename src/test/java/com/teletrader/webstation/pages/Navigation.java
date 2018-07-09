package com.teletrader.webstation.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Navigation {


    WebDriver driver;


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

    public Navigation(WebDriver driver) {
        this.driver = driver;
    }


    // Change to German
    public void clickNews() {
driver.findElement(news).click();

    }

}