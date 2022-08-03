package HttpAdapter;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

/**
 * @author Lidia Soemantoro
 * @version $Id: ProcessOrderAPI.java v 0.1 2022-08-02
 */
public class ProcessOrderAPI {

    /** Define the endpoint path for each APIs **/
    static String urlPathProcessOrder = "/processOrder";


    /**
     * Request builder for processOrder API
     * with assumptions this API requires an Authorization Header
     * @param headerName
     * @param headerVal
     * @param body
     * @return
     */
    public static Response processOrder(String headerName, String headerVal, Map<String,Object> body) {

        RequestSpecBuilder builder = new RequestSpecBuilder();
        RequestSpecification requestSpecification = builder
                .setContentType(ContentType.JSON)
                .setBody(body, ObjectMapperType.JACKSON_2)
                .addHeader(headerName, headerVal)
                .build();

        return HttpHelper.GetResponse(urlPathProcessOrder, "POST", requestSpecification);


    }

}
