package Util;

import io.restassured.response.Response;
import org.testng.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Lidia Soemantoro
 * @version $Id: AssertionUtil.java v 0.1 2022-08-03
 */
public class AssertionUtil {

    /**
     * Assertions for HTTP status code
     * @param response
     * @param expectedCode
     */
    public static void assertResponseCode(Response response, int expectedCode) {
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, expectedCode);

    }

    /**
     * Basic assertion to compare actual resultMsg with expected resultMsg
     * e.g expected: "failed order", actual: "success order"
     * @param response
     * @param expectedResultMsg
     */
    public static void basicAssertionResultMsg(Response response, String expectedResultMsg) {
        assertThat("value of resultMsg: ", response.jsonPath().getString("resultMsg"), equalTo(expectedResultMsg));
    }


}
