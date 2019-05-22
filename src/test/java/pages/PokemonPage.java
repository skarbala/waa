package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PokemonPage {
  private WebDriver driver;


  public PokemonPage(WebDriver driver) {
    this.driver = driver;
  }

  public void selectPokemon(String pokemonToSelect) {
    WebElement pokemonSelect = driver.findElement(By.cssSelector("select"));
    new Select(pokemonSelect).selectByVisibleText(pokemonToSelect);
  }

  public String getActualMessage() {
    return driver.findElement(By.cssSelector("div.pokemon h3")).getText();
  }
}
