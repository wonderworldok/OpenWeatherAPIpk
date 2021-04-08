package stepDef;
/*
 * Created by IntelliJ IDEA.
 * User: Prasad
 * Date: 8/4/21
 * Time: 12:54 PM
 */

import actions.OpenWeatherTempAction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import support.UtilSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class OpenWeatherTempStepDef extends UtilSupport {

    private OpenWeatherTempAction openWeatherTempAction;
    Logger log = Logger.getLogger("devpinoyLogger");

    @Given("I like to view next seven days of temperature with {string}  {string}")
    public void iLikeToViewNextSevenDaysOfTemperatureWith(String lat, String lon) {
        if (openWeatherTempAction == null) {
            openWeatherTempAction = new OpenWeatherTempAction();
        }
        try {
            openWeatherTempAction.buildGetRequest(lat, lon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("I look up weather forecast and returns status {int}")
    public void iLookUpWeatherForecastAndReturnsStatus(int statusNum) {
        Assert.assertEquals("Some issue with response", statusNum, openWeatherTempAction.getResponse());
    }

    @Then("weather for {string} is above {int} degree")
    public void weatherForIsAboveDegree(String city, int temp) {
        List<String> tempLS = new ArrayList<>();
        tempLS = openWeatherTempAction.validateNextSevnDaysTemp(temp);
        log.info("=================================== " + city + " ==================================");
        log.info("==> " + " City: " + city + " - Number of days found more than " + temp + " degrees are: " + tempLS.size());
        log.info("==> " + " City: " + city + " - List of temperatures as per Date:");
        for (String ls : tempLS) {
            System.out.println("==> " + ls.toString());
        }
        log.info("=====================================================================");
        Assert.assertTrue("Temperature is not more than specified degree", tempLS.size() > 0);
    }



}
