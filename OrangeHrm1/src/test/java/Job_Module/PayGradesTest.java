package Job_Module;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import QABootcamp_Maven.OrangeHRM.TestBase;

public class PayGradesTest extends TestBase {
    PayGrades payGrades;
   

  

    private final String gradeName      = "final1232";
    private final String minSalary      = "1200";
    private final String maxSalary      = "2400";

    @BeforeMethod
    public void setUpPage() {
        payGrades = new PayGrades(driver);
       
    }


    @Test(priority = 0, description = "TC-18+19 Add valid pay grade+Add currency to pay grade")
    public void addPayGrade() {
       

        payGrades.clickJobMenu();
        payGrades.clickPayGradesMenu();
      payGrades.addPayGrade(gradeName, 2, minSalary, maxSalary);

        Assert.assertTrue(payGrades.waitForSuccessToast(),
                "Pay Grade not saved");
    }

    @Test(priority = 1, description = "TC-02 Verify required field validation on Add Pay Grade")
    public void checkRequiredFields() {
        payGrades.clickJobMenu();
        payGrades.clickPayGradesMenu();

        payGrades.clickAdd();
        payGrades.clickSave();

        Assert.assertTrue(new JobTitles(driver).isRequired(),
                "Required message is not displayed");
    }

    @Test(priority = 2, description = "TC-20 Max smaller than Min salary ")
    public void verifyInvalidSalaryWarning() {
       
        payGrades.typeName("final1234");
        payGrades.clickSave();

        payGrades.clickAddCurrency();
        payGrades.selectCurrency(2);
        payGrades.typeMinimumSalary("6000");
        payGrades.typeMaximumSalary("3000");
        payGrades.clickCurrencySave();

        Assert.assertTrue(payGrades.isWarningDisplayed("Should be higher than Minimum Salary"),
                "warning not displayed");
    }

    @Test(priority = 3, description = "TC-21 Negative salary values")
    public void verifyNegativeSalaryWarning() {
       
        payGrades.typeMinimumSalary("-12");
        payGrades.typeMaximumSalary("-1");
        payGrades.clickCurrencySave();

        Assert.assertTrue(payGrades.isWarningDisplayed("Should be a valid number (xxx.xx)"),
                "Expected warning not displayed for negative salary values");
    }

    @Test(priority = 4, description = "TC-22 Delete single currency")
    public void deleteOneCurrency() {
    	payGrades.refreshPage();
    	 payGrades.clickAddCurrency();
         payGrades.selectCurrency(2);
         payGrades.typeMinimumSalary("2000");
         payGrades.typeMaximumSalary("3000");
         payGrades.clickCurrencySave();
        payGrades.deleteByIndex(1);

        Assert.assertTrue(payGrades.waitForSuccessToast(),
                "Delete currency did not work");
    }

    @Test(priority = 5, description = "TC-23 Delete all currencies")
    public void deleteAllCurrencies() {
    	 payGrades.clickAddCurrency();
         payGrades.selectCurrency(2);
         payGrades.typeMinimumSalary("2000");
         payGrades.typeMaximumSalary("3000");
         payGrades.clickCurrencySave();

        payGrades.deleteAll();

        Assert.assertTrue(payGrades.waitForSuccessToast(),
                "Delete All Currencies did not work");
    }
    @Test(priority = 6, description = "TC-09 Edit Pay Grade name")
    public void editPayGradeName() throws InterruptedException {
        payGrades.clickJobMenu();
        payGrades.clickPayGradesMenu();

        payGrades.editPayGradeName(1, "er6er5er");
        

        Thread.sleep(2000);
        Assert.assertTrue(payGrades.waitForSuccessToast(),
                "Success toast did not appear");
    }
    @Test(priority = 7, description = "TC-27 Edit Currency Min/Max values")
    public void editCurrencyDetails() throws InterruptedException {
       
        payGrades.editCurrencyDetails(1, "3500", "6500");
        Thread.sleep(2000);


        Assert.assertTrue(payGrades.waitForSuccessToast(),
                "Success toast did not appear");
    }

    @Test(priority = 8, description = "TC-24 Delete entire pay grade")
    public void deleteOnePayGrade() {
        payGrades.clickJobMenu();
        payGrades.clickPayGradesMenu();

        payGrades.deleteByIndex(1);

        Assert.assertTrue(payGrades.waitForSuccessToast(),
                "Delete  Pay Grade did not work");
    }

    @Test(priority = 9, description = "TC-25 Delete all grades")
    public void deleteAllPayGrades() {
       

        payGrades.deleteAll();

        Assert.assertTrue(payGrades.waitForSuccessToast(),
                "Delete All Pay Grades did not work");
    }
    




}
