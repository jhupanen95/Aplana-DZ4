import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestSber {
    WebDriver driver;
    String baseUrl;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.sberbank.ru/ru/person/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void testInsurance() {
        driver.get(baseUrl);
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);

        driver.findElement(By.xpath("(//div[@class='hd-ft-region__title'])[1]")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//H4[@class='kit-grid-modal__header'][text()='Выбор региона']"))));
        driver.findElement(By.xpath("(//INPUT[@class='kit-input__control'])[5]")).click();

        driver.findElement(By.xpath("(//INPUT[@class='kit-input__control'])[5]")).sendKeys("Нижегородская область"); // //div[@class='kit-input']/input - Почему-то не работает
        driver.findElement(By.xpath("//A[@class='kit-link kit-link_m hd-ft-region__city']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("footer"))));

        Assert.assertEquals("Нижегородская область", driver.findElement(By.xpath("(//div[@class='hd-ft-region__title']/span)[1]")).getText());
        Assert.assertEquals("https://www.facebook.com/bankdruzey", driver.findElement(By.xpath("//SPAN[@class='footer__social_logo footer__social_fb']/parent::a")).getAttribute("href"));
        Assert.assertEquals("http://twitter.com/sberbank", driver.findElement(By.xpath("//SPAN[@class='footer__social_logo footer__social_tw']/parent::a")).getAttribute("href"));
        Assert.assertEquals("http://www.youtube.com/sberbank", driver.findElement(By.xpath("//SPAN[@class='footer__social_logo footer__social_yt']/parent::a")).getAttribute("href"));
        Assert.assertEquals("http://instagram.com/sberbank", driver.findElement(By.xpath("//SPAN[@class='footer__social_logo footer__social_ins']/parent::a")).getAttribute("href"));
        Assert.assertEquals("http://vk.com/sberbank", driver.findElement(By.xpath("//SPAN[@class='footer__social_logo footer__social_vk']/parent::a")).getAttribute("href"));
        Assert.assertEquals("https://ok.ru/sberbank", driver.findElement(By.xpath("//SPAN[@class='footer__social_logo footer__social_ok']/parent::a")).getAttribute("href"));

    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
