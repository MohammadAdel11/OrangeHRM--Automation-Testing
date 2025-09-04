package snippet;

public class Snippet {
	try (BufferedReader bf = new BufferedReader(
	        new FileReader(getClass().getClassLoader().getResource("LoginData.csv").getFile())))
}

