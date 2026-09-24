package com.test.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = "test-output/ExtentReport.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setReportName("SauceDemo 自动化测试报告");
            spark.config().setDocumentTitle("登录模块测试报告");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}