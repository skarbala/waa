package tests;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import base.TestBase;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import models.Note;
import pages.NotePage;

public class NoteTest extends TestBase {
  private NotePage notePage;

  @Before
  public void openPage() {
    driver.get(BASE_URL + "/odkazovac.php");
    notePage = new NotePage(driver);
  }

  @Test
  public void itShouldAddNote() throws InterruptedException {
    //vytvorim si casovu peciatku pre unikatnost title
    Fairy fairy = Fairy.create();
    Person fakePerson = fairy.person();
    //ulozim si hodnoty do premennych

    Note noteToAdd = new Note(
        generateUniqueTitle(),
        "Anton",
        "toto je velmi dlhy a zmysluplny odkaz"
    );

    notePage.enterNoteData(noteToAdd);
    notePage.submitNewNote();
    notePage.checkNoteInList(noteToAdd.getTitle());

    notePage.openLastNote();
    notePage.checkNoteDetail(noteToAdd);
  }

  private String generateUniqueTitle() {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    return "Title " + timestamp.getTime();
  }
}
