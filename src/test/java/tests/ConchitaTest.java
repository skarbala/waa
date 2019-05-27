package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import base.TestBase;
import pages.ConchitaPage;

public class ConchitaTest extends TestBase {
  ConchitaPage conchitaPage;

  @Before
  public void openPage() {
    //1.otvorit stranku
    driver.get(BASE_URL + "/zenaalebomuz.php");
  }

  @Test
  public void noOptionShouldBeSelected() {
    Assert.assertFalse(driver.findElement(By.xpath("//input[@value='wurst']")).isSelected());
    Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Zena']/input")).isSelected());
  }

  @Test
  public void itShouldSelectMale() {
    conchitaPage = new ConchitaPage(driver);
    conchitaPage.selectWurst();
    //overit hlasku
    String expectedMessage = "It's wurst";
    String actualMessage = driver.findElement(By.cssSelector("h1.description")).getText();
    Assert.assertEquals(expectedMessage, actualMessage);
    //overit ze moznost zena nie je vybrata
    Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Zena']/input")).isSelected());
  }
}
