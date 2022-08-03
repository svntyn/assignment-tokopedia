package HttpAdapter;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;

/**
 * @author Lidia Soemantoro
 * @version $Id: HttpHelper.java v 0.1 2022-08-01
 */
public class HttpHelper {


    public static Response GetResponse(String url, String method, RequestSpecification requestSpec) {
        //method to print the request and response
        ByteArrayOutputStream requestOutputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream responseOutputStream = new ByteArrayOutputStream();
        PrintStream requestPrintStream = new PrintStream(requestOutputStream);
        PrintStream responsePrintStream = new PrintStream(responseOutputStream);

        //response builder
        Response response = given(requestSpec)
                .filter(new RequestLoggingFilter(LogDetail.ALL, requestPrintStream))
                .filter(new ResponseLoggingFilter(LogDetail.ALL, responsePrintStream))
                .when()
                .request(method, url)
                .then()
                .extract().response();

        //print the request and response to console
        System.out.println("REQUEST :");
        System.out.println(requestOutputStream.toString());

        System.out.println("RESPONSE :");
        System.out.println(responseOutputStream.toString());

        return response;
    }

}
