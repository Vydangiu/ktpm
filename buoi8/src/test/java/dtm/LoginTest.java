package dtm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void login(String username, String password) {
        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))
        );
        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("password"))
        );
        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("login-button"))
        );

        usernameField.clear();
        passwordField.clear();

        if (username != null) {
            usernameField.sendKeys(username);
        }
        if (password != null) {
            passwordField.sendKeys(password);
        }

        loginButton.click();
    }

    private String getErrorMessage() {
        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h3[data-test='error']")
                )
        );
        return errorMessage.getText();
    }

    @Test(description = "Đăng nhập thành công với tài khoản hợp lệ")
    public void testLoginSuccess() {
        login("standard_user", "secret_sauce");

        wait.until(ExpectedConditions.urlContains("inventory.html"));
        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(
                currentUrl.contains("inventory.html"),
                "Đăng nhập thành công nhưng không chuyển đến trang inventory!"
        );
    }

    @Test(description = "Đăng nhập sai mật khẩu")
    public void testLoginWrongPassword() {
        login("standard_user", "sai_mat_khau");

        String actualError = getErrorMessage();

        Assert.assertTrue(
                actualError.toLowerCase().contains("username and password do not match"),
                "Sai mật khẩu nhưng thông báo lỗi không đúng!"
        );
    }

    @Test(description = "Bỏ trống username")
    public void testLoginEmptyUsername() {
        login("", "secret_sauce");

        String actualError = getErrorMessage();

        Assert.assertTrue(
                actualError.contains("Username is required"),
                "Bỏ trống username nhưng không hiện đúng thông báo lỗi!"
        );
    }

    @Test(description = "Bỏ trống password")
    public void testLoginEmptyPassword() {
        login("standard_user", "");

        String actualError = getErrorMessage();

        Assert.assertTrue(
                actualError.contains("Password is required"),
                "Bỏ trống password nhưng không hiện đúng thông báo lỗi!"
        );
    }

    @Test(description = "Tài khoản bị khóa")
    public void testLoginLockedUser() {
        login("locked_out_user", "secret_sauce");

        String actualError = getErrorMessage();

        Assert.assertTrue(
                actualError.contains("Sorry, this user has been locked out"),
                "Tài khoản bị khóa nhưng thông báo lỗi không đúng!"
        );
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}