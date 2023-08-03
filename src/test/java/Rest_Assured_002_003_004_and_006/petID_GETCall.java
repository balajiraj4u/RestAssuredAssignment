package Rest_Assured_002_003_004_and_006;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class petID_GETCall {

    @Test
    public void do_petstore_Validation_Ass_003() {

        try {

            given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .when()
                    .get("https://petstore.swagger.io/v2/user/Uname001")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.SC_OK)
                    .body("$", hasKey("firstName"))
                    .body("$", hasKey("lastName"))
                    .body("username", equalTo("Uname001"))
                    .body("email", equalTo("Positive@Attitude.com"))
                    .body("userStatus", equalTo(1));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    public void do_Authentication_Validation_Ass_004() {

        try {


            given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .auth().basic("Uname001", "@tt!tude")
                    .when()
                    .get("https://petstore.swagger.io/v2/user/login")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.SC_OK)
                    .body("$", hasKey("code"))
                    .body("$", hasKey("message"))
                    .body("code", equalTo(200));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    public void do_petstore_logout_Validation_Ass_006() {

        try {

            given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .when()
                    .get("https://petstore.swagger.io/v2/user/logout")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.SC_OK)
                    .body("message", equalTo("ok"))
                    .body("code", equalTo(200));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test

    public void do_petstore_data_Environments_Ass_002() {

        try {

            putCallTesting("DEV");
            putCallTesting("QA");
            putCallTesting("PROD");


        }

        catch (Exception e) {
            System.out.println(e);
        }

    }

    public void putCallTesting(String Env)
    {
        HashMap<String, String> map =new HashMap<>();
        map.put("DEV","available_DEV");
        map.put("QA","available_QA");
        map.put("PROD","available_PROD");
        String status=map.get(Env);
        System.out.println(status);
        String txt_body="{\n" +
                "  \"id\": 9223372036854295000,\n" +
                "  \"category\": {\n" +
                "    \"id\": 20021,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \""+status+"\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .body(txt_body)
                .put("https://petstore.swagger.io/v2/pet")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("category.id", equalTo(20021))
                .body("status",equalTo(status));

    }

}
