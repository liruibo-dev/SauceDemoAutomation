# SauceDemo 自动化测试框架

基于 Java + Selenium + TestNG 的 Web 自动化测试框架，对 SauceDemo 电商演示站进行登录模块自动化测试。

## 技术栈
- Java 17
- Selenium WebDriver 4.27
- TestNG 7.10
- Maven
- ExtentReports 5.1.1
- Page Object 设计模式

## 项目结构
```
SauceDemoAutomation/
├── pom.xml
├── README.md
├── src/
│   ├── main/java/com/test/
│   │   ├── FirstScript.java
│   │   └── Main.java
│   └── test/java/com/test/
│       ├── BaseTest.java
│       ├── LoginTest.java
│       ├── pages/
│       │   ├── BasePage.java
│       │   ├── LoginPage.java
│       │   └── InventoryPage.java
│       └── utils/
│           ├── ExtentManager.java
│           └── TestListener.java
└── test-output/
    ├── ExtentReport.html
    └── screenshots/
```
`test-output/`（HTML 测试报告与失败截图）在测试运行后生成，未纳入版本控制。

## 运行方式
1. 前置条件：JDK 17、Maven 3.9+、Chrome 浏览器
2. 克隆项目：`git clone https://github.com/liruibo-dev/SauceDemoAutomation.git`
3. 运行：在 IDEA 中右键 `LoginTest.java` → Run
   （无需手动配置 ChromeDriver，Selenium Manager 会自动下载）
4. 查看报告：打开 `test-output/ExtentReport.html`

## 测试用例
| 编号 | 场景 | 数据 |
|------|------|------|
| TC-001 | 标准用户登录成功 | standard_user / secret_sauce |
| TC-002 | 错误密码登录失败 | standard_user / wrong_password |
| TC-003 | 空用户名密码登录失败 | 空 / 空 |
| TC-004 | 锁定用户登录失败 | locked_out_user / secret_sauce |

## 致谢
被测网站 [SauceDemo](https://www.saucedemo.com/) 由 Sauce Labs 提供。