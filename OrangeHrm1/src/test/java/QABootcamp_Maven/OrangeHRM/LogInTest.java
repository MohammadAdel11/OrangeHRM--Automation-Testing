package QABootcamp_Maven.OrangeHRM;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LogInTest extends TestBase {
    LogIn login;

    @BeforeMethod
    public void setUpPage() {
        login = new LogIn(driver);
    }

    @Test(dataProvider = "LogInData")
    public void logIn(String userName, String pass, String expected) {
        login.Login(userName, pass);

        if (expected.equals("Dashboard")) {
            Assert.assertTrue(login.isLogedIn(), "Dashboard should display");
        } else {
            Assert.assertEquals(login.getMassage(), expected, "Error message should match");
        }
    }

    @DataProvider(name = "LogInData")
    public Object[][] readFile() throws IOException {
        List<Object[]> rows = new ArrayList<>();

        try (BufferedReader bf = new BufferedReader(
                new FileReader(getClass().getClassLoader().getResource("LoginData.csv").getFile()))) {

            String line;
            boolean firstLine = true;

            while ((line = bf.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip header
                    continue;
                }

                if (line.isBlank())
                    continue;

                String[] parts = line.split(",", -1);
                if (parts.length < 3)
                    continue;

                String userName = parts[0].trim();
                String password = parts[1].trim();
                String expected = parts[2].trim();

                rows.add(new Object[] { userName, password, expected });
            }
        }

        return rows.toArray(new Object[0][]);
    }
}
