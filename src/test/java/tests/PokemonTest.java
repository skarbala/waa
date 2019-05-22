package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import base.TestBase;
import pages.PokemonPage;

public class PokemonTest extends TestBase {
  private PokemonPage pokemonPage;

  @Before
  public void openPage() {
    //1.otvorit stranku
    driver.get(BASE_URL + "/vybersi.php");
    pokemonPage = new PokemonPage(driver);
  }

  @Test
  public void itShouldSelectPokemons() {
    String[] selectedPokemons = {"Pikachu", "Bulbasaur", "Charmander", "Diglett", "Geodude"};

    for (String pokemon : selectedPokemons) {
      //vyberiem pokemona
      pokemonPage.selectPokemon(pokemon);
      //overim hlasku
      Assert.assertEquals(getExpectedMessage(pokemon), pokemonPage.getActualMessage());
    }
  }

  private String getExpectedMessage(String pokemonName) {
    return String.format("I choose you %s !", pokemonName);
  }

}
