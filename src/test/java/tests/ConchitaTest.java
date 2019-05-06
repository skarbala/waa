package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConchitaTest {

  WebDriver driver;

  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    //0.spustit prehliadac
    driver = new ChromeDriver();
    //1.otvorit stranku
    driver.get("http://localhost:8888/zenaalebomuz.php");
  }

  @Test
  public void noOptionShouldBeSelected() {
    Assert.assertFalse(driver.findElement(By.xpath("//input[@value='wurst']")).isSelected());
    Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Zena']/input")).isSelected());
  }
}
