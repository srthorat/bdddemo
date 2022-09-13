package bdd.runner;
import static org.slf4j.LoggerFactory.getLogger;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class) 
@CucumberOptions(
		features= {"classpath:features"},
		glue="stepdefs",
		plugin=  { 
				//"pretty",		
				//"html:target/CucumberResults",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		},
		tags = "@Sanity"
		
		)
public class RunnerTest {
	
	public static String baseUrl;
	public static String first_name;
	public static String email;
	public static String last_name;
	private static final Logger log = getLogger(RunnerTest.class);
	@BeforeClass
	public static void readConfigFile(){
		log.info("Reading Configuration file Before Starting Features Execution");
		Properties config = new Properties();
		try {
	                FileInputStream file = new FileInputStream(
	                		System.getProperty("user.dir") + "//src//test//resources//config//config.properties");
	                		config.load(file);
	                		baseUrl = config.getProperty("BASE_URL");
	                		log.info("Base URL will used for Testing : " + baseUrl);
	                		first_name = config.getProperty("FRIST_NAME");
	                		email = config.getProperty("EMAIL");
	                		last_name = config.getProperty("LAST_NAME");
		} catch (IOException e) {
			e.printStackTrace();
			}
		
		
		
	}

}
