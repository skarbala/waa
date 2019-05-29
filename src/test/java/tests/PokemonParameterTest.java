package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.PokemonPage;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PokemonParameterTest extends TestBase {
    private PokemonPage pokemonPage;
    private String pokemonToSelect;

    public PokemonParameterTest(String pokemonToSelect) {
        this.pokemonToSelect = pokemonToSelect;
    }

    @Parameterized.Parameters(name = "selected pokemon: {0}")
    public static List<String> getData() {
        return Arrays.asList("Pikachu", "Bulbasaur", "Charmander", "Diglett", "Geodude");
    }

    @Before
    public void openPage() {
        //1.otvorit stranku
        driver.get(BASE_URL + "/vybersi.php");
        pokemonPage = new PokemonPage(driver);
    }

    @Test
    public void itShouldSelectPokemons() {
        //vyberiem pokemona
        pokemonPage.selectPokemon(pokemonToSelect);
        //overim hlasku
        assertEquals(getExpectedMessage(pokemonToSelect), pokemonPage.getActualMessage());

    }

    private String getExpectedMessage(String pokemonName) {
        return String.format("I choose you %s !", pokemonName);
    }

}
