package Util;

import static io.restassured.RestAssured.given;


/**
 * @author Lidia Soemantoro
 * @version $Id: AuthUtil.java v 0.1 2022-08-03
 */
public class AuthUtil {

    static String urlPathAuth = ""; //example placeholder to put the url to get API authentication


    /**
     * Basic method to get authorization key for our Authorization Header
     * @param user
     * @param password
     * @return
     */
    public static String getSessionKey(String user, String password) {
        String sessionKey = given().header("Content-Type", "application/json")
                .body("{\"body\":{\"username\":\""+user+"\",\""+password+"\":\"123456\"}}")
                .log().all()
                .when().post(urlPathAuth)
                .then().extract().response().asString();

        return sessionKey;

    }

}
