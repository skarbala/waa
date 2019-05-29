package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.CalculatorPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CalculatorSumTest extends TestBase {
    private int firstNumber;
    private int secondNumber;
    private int result;
    private CalculatorPage calculatorPage;

    public CalculatorSumTest(int firstNumber, int secondNumber, int result) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.result = result;
    }

    @Parameterized.Parameters(name = "{0} + {1} = {2}")
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
            {1, 2, 3},
            {-5, 7, 2},
            {4, 4, 8},
            {10, -20, -10},
            {10, 2, 14},
        });
    }

    @Before
    public void openPage() {
        driver.get(BASE_URL.concat("/kalkulacka.php"));
        calculatorPage = new CalculatorPage(driver);

    }

    @Test()
    public void checkCalculation() {
        calculatorPage.enterFirstInput(String.valueOf(firstNumber));
        calculatorPage.enterSecondInput(String.valueOf(secondNumber));
        calculatorPage.sumNumbers();
        assertEquals(String.valueOf(result), calculatorPage.getResult());
    }
}
