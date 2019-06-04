package tests;

import base.TestBase;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import org.junit.Before;
import org.junit.Test;
import pages.SavingsCalculatorPage;

import static org.junit.Assert.*;
import static org.openqa.selenium.By.cssSelector;

public class SavingsCalculatorTest extends TestBase {
    private SavingsCalculatorPage savingsCalculatorPage;
    private Person person;

    @Before
    public void openPage() {
        driver.get(BASE_URL.concat("/savingscalculator.php"));
        savingsCalculatorPage = new SavingsCalculatorPage(driver);
        person = Fairy.create().person();
    }

    @Test
    public void itShouldEnableApplyButton() {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail(person.getEmail());

        assertTrue(savingsCalculatorPage.getApplyButton().isEnabled());
    }

    @Test
    public void itShouldDisplayCalculatedAmounts() {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail(person.getEmail());

        assertFalse(savingsCalculatorPage.getCalculatedTotalIncomeElement().getText().isEmpty());
        assertFalse(savingsCalculatorPage.getCalculatedInterestIncomeElement().getText().isEmpty());
    }

    @Test
    public void itShouldDisplayCalculatedRisk() {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail(person.getEmail());

        assertFalse(savingsCalculatorPage.getCalculatedRiskElement().getText().isEmpty());
        assertFalse(savingsCalculatorPage.getCalculatedInterestIncomeElement().getText().isEmpty());
    }


    @Test
    public void itShouldContainFundNameInNewRequest() {
        String fundToSelect = "Hoggwart's Fund";

        savingsCalculatorPage.selectFund(fundToSelect);
        savingsCalculatorPage.enterOneTimeInvestment("25000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail(person.getEmail());

        savingsCalculatorPage.applyForSaving();

        assertEquals(
            fundToSelect,
            savingsCalculatorPage.getRecentRequestDetail().findElement(cssSelector("p.fund-description")).getText()
        );
    }

}



