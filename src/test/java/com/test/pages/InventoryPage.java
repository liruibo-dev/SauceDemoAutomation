package com.test.pages;

import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnInventoryPage() {
        return driver.getCurrentUrl().contains("/inventory.html");
    }
}