package com.test.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.test.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    private ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = ExtentManager.getInstance()
                .createTest(result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (extentTest != null) {
            extentTest.log(Status.PASS, "测试通过");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (extentTest != null) {
            WebDriver driver = getDriverFromResult(result);
            if (driver != null) {
                String screenshotPath = takeScreenshot(driver, result.getName());
                extentTest.log(Status.FAIL, result.getThrowable());
                if (screenshotPath != null) {
                    extentTest.addScreenCaptureFromPath(screenshotPath);
                }
            } else {
                extentTest.log(Status.FAIL, result.getThrowable());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (extentTest != null) {
            extentTest.log(Status.SKIP, "测试跳过");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }

    private WebDriver getDriverFromResult(ITestResult result) {
        Object testInstance = result.getInstance();
        if (testInstance instanceof BaseTest) {
            return ((BaseTest) testInstance).getDriver();
        }
        return null;
    }

    private String takeScreenshot(WebDriver driver, String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            Path dir = Paths.get("test-output", "screenshots");
            Files.createDirectories(dir);
            String filename = testName + "_" + timestamp + ".png";
            Path dest = dir.resolve(filename);
            Files.copy(src.toPath(), dest);
            return "screenshots/" + filename;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}