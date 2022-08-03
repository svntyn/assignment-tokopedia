package TestingApp;

import HttpAdapter.ProcessOrderAPI;
import Util.AssertionUtil;
import Util.AuthUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.File;
import java.util.Map;

/**
 * @author Lidia Soemantoro
 * @version $Id: OrderTest.java v 0.1 2022-08-01
 */
public class OrderTest {

    private static JsonPath jsonBodyProcessOrder = JsonPath.from(new File("src/test/resources/test-data/processOrder.json"));



    @Test
    @Title("Test success normal flow process order")
    public void successProcessOrder() {
        // prepare test data
        Map<String,Object> reqBody = jsonBodyProcessOrder.get("successProcessOrder");

        //make request and get response
        //example if using authorization
        Response response = ProcessOrderAPI.processOrder("Authorization", AuthUtil.getSessionKey("testUser", "123456"), reqBody);

        //assertions
        AssertionUtil.assertResponseCode(response, 200);
        successAssertOrder(response, "Processed");

    }


    @Test
    @Title("Test failed process order due to invalid request")
    public void failProcessOrderInvalidRequest() {
        // prepare test data
        Map<String, Object> reqBody = jsonBodyProcessOrder.get("failProcessOrderInvalidRequest");

        //make request and get response
        //example if using authorization
        Response response = ProcessOrderAPI.processOrder("Authorization", AuthUtil.getSessionKey("testUser", "123456"), reqBody);

        //assertions
        AssertionUtil.assertResponseCode(response, 400);

    }


    @Test
    @Title("Test failed process order due to invalid auth")
    public void failProcessOrderInvalidAuth() {
        // prepare test data
        Map<String, Object> reqBody = jsonBodyProcessOrder.get("failProcessOrderInvalidRequest");

        //make request and get response
        //example if using authorization
        Response response = ProcessOrderAPI.processOrder("Authorization", null, reqBody);

        //assertions
        AssertionUtil.assertResponseCode(response, 401);

    }


    @Test
    @Title("Test failed flow process order due to orderId not exists")
    public void failProcessOrderIdNotExist() {
        // prepare test data
        Map<String,Object> reqBody = jsonBodyProcessOrder.get("failProcessOrderIdNotExist");

        //make request and get response
        //example if using authorization
        Response response = ProcessOrderAPI.processOrder("Authorization", AuthUtil.getSessionKey("testUser", "123456"), reqBody);


        //assertions
        AssertionUtil.assertResponseCode(response, 200);
        AssertionUtil.basicAssertionResultMsg(response, "order_id not exists");

    }


    @Test
    @Title("Test failed flow process order due to orderId already processed")
    public void failProcessOrderIdProcessed() {
        // prepare test data
        Map<String,Object> reqBody = jsonBodyProcessOrder.get("failProcessOrderIdProcessed");

        //make request and get response
        //example if using authorization
        Response response = ProcessOrderAPI.processOrder("Authorization", AuthUtil.getSessionKey("testUser", "123456"), reqBody);


        //assertions
        AssertionUtil.assertResponseCode(response, 200);
        AssertionUtil.basicAssertionResultMsg(response, "order_id already processed");

    }

    @Test
    @Title("Test failed flow process order due to last_updated_timestamp is not unix")
    public void failProcessOrderInvalidTimestamp() {
        // prepare test data
        Map<String,Object> reqBody = jsonBodyProcessOrder.get("failProcessOrderInvalidTimestamp");

        //make request and get response
        //example if using authorization
        Response response = ProcessOrderAPI.processOrder("Authorization", AuthUtil.getSessionKey("testUser", "123456"), reqBody);


        //assertions
        AssertionUtil.assertResponseCode(response, 200);
        AssertionUtil.basicAssertionResultMsg(response, "timestamp invalid");

    }


    private void successAssertOrder(Response response, String status) {
        String orderStatus = response.path("order_status");
        Assert.assertEquals(orderStatus, status);

    }


}
