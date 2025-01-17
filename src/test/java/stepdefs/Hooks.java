package stepdefs;
import static org.slf4j.LoggerFactory.getLogger;
import org.slf4j.Logger;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks { 
	
	public  static String baseUrl =null;
	
	private static final Logger log = getLogger(Hooks.class);
	
	 @Before
	  public void before(Scenario scenario) {
	        log.info("--------------------------------------------------------------------------------------------");
	        log.info("S C E N A R I O  '" + scenario.getName() + "'");
	        log.info("--------------------------------------------------------------------------------------------");
	        log.info("STARTING Scenario");
	        
	 }
	 
	    @After
	    public void after(Scenario scenario) {
	       log.info("ENDING Scenario");
	       log.info("--------------------------------------------------------------------------------------------");
	       log.info("S C E N A R I O  result '" + scenario.getName() + "' " + scenario.getStatus()); // One of PASSED|UNDEFINED|PENDING|SKIPPED|FAILED
	       log.info("--------------------------------------------------------------------------------------------");

	    }

}
