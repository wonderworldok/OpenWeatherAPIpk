package actions;
/*
 * Created by IntelliJ IDEA.
 * User: Prasad
 * Date: 8/4/21
 * Time: 12:54 PM
 */
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import support.UtilSupport;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OpenWeatherTempAction extends UtilSupport {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    JsonPath js;

    public void buildGetRequest(String lat, String lon) throws IOException {

        res = given().spec(requestSpecificationWeather()).queryParam("lat", lat).queryParam("lon", lon).queryParam("daily");
        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

    }

    public int getResponse() {
        response = res.when().get("/data/2.5/onecall");
        String respString = response.asString();
        js = new JsonPath(respString);
        return response.getStatusCode();
    }

    public List<String> validateNextSevnDaysTemp(Integer temp) {
        List<String> getTempList = new ArrayList<>();
        int count = js.getInt("daily.size()");
        float dayTemp = 0;
        for (int i = 0; i < count; i++) {
            java.util.Date date = new java.util.Date(js.getLong("daily[" + i + "].dt") * 1000);
            SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
            String dateText = date_format.format(date);
            dayTemp = js.getFloat("daily[" + i + "].temp.max");

            if (dayTemp >= (float) (temp)) {
                getTempList.add("Date: " + dateText + " - Temperature: " + Float.toString(dayTemp) + " degrees ceslsius");
            }
        }
        return getTempList;
    }

}
