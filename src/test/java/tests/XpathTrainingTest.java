package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import base.TestBase;

public class XpathTrainingTest extends TestBase {

  @Before
  public void openPage() {
    //1.otvorit stranku
    driver.get(BASE_URL + "/xpathtrainingcenter.php");
  }

  @Test
  public void itShouldDisplayAction() {
    String buttonText = "One more button";
    driver.findElement(By.xpath("//button[contains(text(),'" + buttonText + "')]")).click();

    String actualMessage = driver.findElement(By.cssSelector("div.output h2 span")).getText();

    Assert.assertEquals(
        "you clicked " + buttonText.toLowerCase(),
        actualMessage
    );
  }

  @Test
  public void itShouldDisplayEnteredMessage() {
    String message = "vonku prsi";
    driver.findElement(By.cssSelector("input")).sendKeys(message);
    //stlacim tlacidlo Hit ME!
    driver.findElement(By.id("hitme")).click();

    //precitam hodnotu zo stranky a ulozim ju do premennej
    String actualMessage = driver.findElement(By.cssSelector("div.output h2 span")).getText();

    Assert.assertEquals(
        "you entered " + message,
        actualMessage
    );
  }
}
