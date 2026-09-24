package com.test;

import com.test.pages.InventoryPage;
import com.test.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {"standard_user", "secret_sauce", true, "/inventory.html"},
                {"standard_user", "wrong_password", false, "Username and password do not match"},
                {"", "", false, "Username is required"},
                {"locked_out_user", "secret_sauce", false, "Sorry, this user has been locked out"},
        };
    }

    @Test(dataProvider = "loginData", description = "数据驱动登录测试")
    public void testLogin(String username, String password, boolean shouldPass, String expectedKeyword) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        if (shouldPass) {
            InventoryPage inventoryPage = new InventoryPage(driver);
            Assert.assertTrue(inventoryPage.isOnInventoryPage(), "登录成功应跳转到库存页面");
        } else {
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "错误信息应显示");
            Assert.assertTrue(loginPage.getErrorMessage().contains(expectedKeyword),
                    "错误提示应包含 '" + expectedKeyword + "'");
        }
    }
}