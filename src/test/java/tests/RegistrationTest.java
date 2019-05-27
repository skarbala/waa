package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import base.TestBase;
import pages.RegistrationPage;

public class RegistrationTest extends TestBase {

  @Before
  public void openPage() {
    //1.otvorit stranku
    driver.get(BASE_URL + "/registracia.php ");
  }

  @Test
  public void itShouldRegisterValidUser() {
    String email = "brano@mojsej.sk";
    String meno = "branislav";
    String priezvisko = "mojsej";
    String heslo = "123456";

    //zadam zakladne udaje
    RegistrationPage registrationPage = new RegistrationPage(driver);
    registrationPage.enterData(email, meno, priezvisko, heslo);

    //kliknut na checkbox som robot
    driver.findElement(By.name("robot")).click();
    //kliknut na tlacidlo registrovat sa
    driver.findElement(By.cssSelector("button.btn-success")).click();
    //overit uspesnu hlasku
    Assert.assertTrue(driver.findElement(By.cssSelector("div.alert-success")).isDisplayed());
  }

  @Test
  public void itShouldDisplayErrorMessageWhenInputsAreEmpty() {
    //kliknut na checkbox som robot
    driver.findElement(By.name("robot")).click();
    //kliknut na tlacidlo registrovat sa
    driver.findElement(By.cssSelector("button.btn-success")).click();
    //overit uspesnu hlasku
    Assert.assertTrue(driver.findElement(By.cssSelector("div.alert-danger")).isDisplayed());
  }


}
