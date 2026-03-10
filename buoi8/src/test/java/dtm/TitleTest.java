package dtm;



import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TitleTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test(description = "Kiểm thử tiêu đề trang chủ")
    public void testTitle() {
        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();

        Assert.assertEquals(
                actualTitle,
                expectedTitle,
                "Tiêu đề trang không đúng!"
        );
    }

    @Test(description = "Kiểm thử URL trang chủ")
    public void testURL() {
        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(
                actualUrl.contains("saucedemo"),
                "URL không hợp lệ!"
        );
    }

    @Test(description = "Kiểm thử page source có chứa nội dung đặc trưng của trang login")
    public void testPageSource() {
        String pageSource = driver.getPageSource();

        Assert.assertTrue(
                pageSource.contains("Swag Labs") || pageSource.contains("login-button"),
                "Page source không chứa nội dung mong đợi của trang đăng nhập!"
        );
    }

    @Test(description = "Kiểm thử form đăng nhập có hiển thị")
    public void testLoginFormDisplayed() {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        Assert.assertTrue(
                usernameField.isDisplayed(),
                "Ô Username không hiển thị!"
        );
        Assert.assertTrue(
                passwordField.isDisplayed(),
                "Ô Password không hiển thị!"
        );
        Assert.assertTrue(
                loginButton.isDisplayed(),
                "Nút Login không hiển thị!"
        );
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}