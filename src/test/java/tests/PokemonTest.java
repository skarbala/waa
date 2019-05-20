package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import base.TestBase;

public class PokemonTest extends TestBase {

  @Before
  public void openPage() {
    //1.otvorit stranku
    driver.get(BASE_URL + "/vybersi.php");
  }

  @Test
  public void itShouldSelectPokemons() {
    String[] selectedPokemons = {"Pikachu", "Bulbasaur", "Charmander", "Diglett", "Geodude"};

    for (String pokemon : selectedPokemons) {
      //vyberiem pokemona
      selectPokemon(pokemon);
      //overim hlasku
      Assert.assertEquals(getExpectedMessage(pokemon), getActualMessage());
    }
  }

  private void selectPokemon(String pokemonToSelect) {
    WebElement pokemonSelect = driver.findElement(By.cssSelector("select"));
    new Select(pokemonSelect).selectByVisibleText(pokemonToSelect);
  }

  private String getActualMessage() {
    return driver.findElement(By.cssSelector("div.pokemon h3")).getText();
  }

  private String getExpectedMessage(String pokemonName) {
    return String.format("I choose you %s !", pokemonName);
  }

}
