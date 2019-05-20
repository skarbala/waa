package tests;

import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.TestBase;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;

public class NoteTest extends TestBase {

  @Before
  public void openPage() {
    driver.get(BASE_URL + "/odkazovac.php");
  }

  @Test
  public void itShouldAddNote() throws InterruptedException {
    //vytvorim si casovu peciatku pre unikatnost title
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    Fairy fairy = Fairy.create();
    Person fakePerson = fairy.person();
    //ulozim si hodnoty do premennych
    String title = "Title " + timestamp.getTime();
    String author = fakePerson.getFirstName() + " " + fakePerson.getLastName();
    String message = "toto je velmi dlhy a zmysluplny odkaz";

    enterNoteData(title, author, message);
    submitNewNote();
    checkNoteInList(title);
    getLastNoteFromList().click();
    //overim detail zaznamu
    Thread.sleep(1000);
    checkNoteDetail(title, author, message);
  }

  private void enterNoteData(String title, String person, String message) {
    driver.findElement(By.name("title")).sendKeys(title);
    driver.findElement(By.name("author")).sendKeys(person);
    driver.findElement(By.name("message")).sendKeys(message);
  }

  private void submitNewNote() {
    driver.findElement(By.cssSelector("button.btn-block")).click();
  }

  private WebElement getLastNoteFromList() {
    return driver.findElement(By.cssSelector("ul.list-of-sins > li:last-child"));
  }

  private void checkNoteDetail(String title, String author, String message) {
    WebElement detail = driver.findElement(By.cssSelector("div.content"));
    Assert.assertEquals(title, detail.findElement(By.cssSelector("h4.title")).getText());
    Assert.assertEquals(author, detail.findElement(By.cssSelector("h4.recipent")).getText());
    Assert.assertEquals(message, detail.findElement(By.cssSelector("p")).getText());
  }

  private void checkNoteInList(String title) {
    WebElement listItem = getLastNoteFromList();
    //overim ze sa pridal novy zaznam do zoznamu
    Assert.assertTrue(listItem.getText().contains(title));
    //overenie linku
    Assert.assertTrue(listItem.findElement(By.cssSelector("div.description a")).isDisplayed());
    Assert.assertEquals("detail", listItem.findElement(By.cssSelector("div.description a")).getText());
  }
}
