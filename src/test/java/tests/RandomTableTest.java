package tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.TestBase;

public class RandomTableTest extends TestBase {
  @Before
  public void openPage() {
    //1.otvorit stranku
    driver.get(BASE_URL + "/tabulka.php");
  }

  @Test
  public void itShouldPrintLastRow() {
    //2.najdem a vypisem posledny riadok
    System.out.println(driver.findElement(By.xpath("//table/tbody/tr[last()]")).getText());

    //3.najdem a vypisem meno z predposledneho riadku
    System.out.println(driver.findElement(By.xpath("//table/tbody/tr[last()-1]/td[2]")).getText());

  }

  @Test
  public void itShouldContainDataForEachRow() {
    for (WebElement tableRow : getRows()) {
      Assert.assertFalse(tableRow.getText().isEmpty());
    }
  }

  @Test
  public void itShouldContainNameForEachRow() {
    List<WebElement> tableRows = getRows();
    for (WebElement tableRow : tableRows) {
      tableRow.findElement(By.cssSelector("td:nth-child(2)"));
      WebElement rowName = tableRow.findElement(By.xpath("./td[2]"));
      Assert.assertFalse(rowName.getText().isEmpty());
    }
  }

  private List<WebElement> getRows() {
    return driver.findElements(By.cssSelector("table tbody tr"));
  }
}
