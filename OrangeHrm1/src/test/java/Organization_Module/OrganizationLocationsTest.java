package Organization_Module;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import QABootcamp_Maven.OrangeHRM.TestBase;

public class OrganizationLocationsTest extends TestBase {
	private OrganizationLocations Location;
	private LocationSearchReset Search;

	SoftAssert soft = new SoftAssert();

	@BeforeClass
	public void setUpPage() {
		Location = new OrganizationLocations(driver);
		Search = new LocationSearchReset(driver);
	}
//
	@Test(priority = 1, description = "Verify Add valid Location")
	public void ValidAddTest() {
		Location.OpenLocationForm();
		Location.AddName("Cairo20");
		Location.AddCity("cairo");
		Location.AddCountry("Egypt");
		Location.SaveAdd();
		Assert.assertTrue(Location.getSuccessMessage().contains("Successfully Saved"));

	}

	@Test(priority = 1, description = "Verify Search with valid name")
	public void ValidNameSearch() {
		Search.OpenLocationForm();
		Search.SearchByName("Cairo20");
		Search.SearchButton();
		List<WebElement> cells = Search.getRowCells();
		soft.assertEquals(cells.get(0).getText().trim(), "Cairo20", "doesnt match");
		soft.assertAll();
	}

	@Test(priority = 2, description = "Verify Search with valid city")
	public void ValidCitySearch() {
		Search.OpenLocationForm();
		Search.SearchByCity("cairo");
		Search.SearchButton();
		List<WebElement> cells = Search.getRowCells();
		soft.assertEquals(cells.get(1).getText().trim(), "cairo", "doesnt match");
		soft.assertAll();
	}

	@Test(priority = 3, description = "Verify Search with valid data ")
	public void ValidSearch() {
		Search.OpenLocationForm();
		Search.SearchByName("Cairo20");
		Search.SearchByCity("cairo");
		Search.SearchByCountry("Egypt");
		Search.SearchButton();
		List<WebElement> cells = Search.getRowCells();
		Assert.assertEquals(cells.get(0).getText().trim(), "Cairo20", "doesnt match");
		Assert.assertEquals(cells.get(1).getText().trim(), "cairo", "doesnt match");
		Assert.assertEquals(cells.get(3).getText().trim(), "Egypt", "doesnt match");
	}

	@Test(priority = 4, description = "Verify reset button")
	public void ResetTest() {
		Search.OpenLocationForm();
		Search.SearchByName("Hanaa");
		Search.SearchByCity("Ottawa");
		Search.SearchByCountry("Canada");
		Search.ResetButton();
		Assert.assertTrue(Search.isResetButtonEnabled(), "Reset button is not enabled");

	}

	@Test(priority = 5, description = "Verify Search with invalid data ")
	public void inValidSearch() {
		Search.OpenLocationForm();
		Search.SearchByName("Canadian");
		Search.SearchByCity("texsas");
		Search.SearchByCountry("United States");
		Search.SearchButton();
		Assert.assertTrue(Search.NoRecoredFoundDisplayed());

	}


}
