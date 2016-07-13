import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.matcher.RestAssuredMockMvcMatchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by yury on 8.7.16.
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= GreetingControllerConfiguration.class)

public class GreetingControllerTest {

    @Test
    public void exampleRestTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/greeting")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("content", equalTo("Hello, Johan!"));
    }

  /*  @Test public void
    greeting_resource_returns_an_id_and_expected_greeting_in_body() {
        given().
                param("name", "Johan").
                when().
                get("/greeting").
                then().
                statusCode(200).
                body("id", equalTo(1)).
                body("content", equalTo("Hello, Johan!"));
    }*/

    @Before public void
    given_rest_assured_is_configured_with_greeting_controller() {
        RestAssuredMockMvc.standaloneSetup(new GreetingController());
    }

    @Test public void
    greeting_resource_returns_an_id_in_the_response_body() {

        given ().
                param("name", "Johan").
                when ().
                get ( "/greeting" ).
                then ().
                contentType(ContentType.JSON).
                body ( "content" , equalTo ( "Hello, Johan!" ));

      /*  given().
                param("name", "Johan").
                contentType(ContentType.JSON).
                when().
                get("/greeting").
                then().
                contentType(ContentType.JSON).
                body("id", equalTo(1)); */
    }

    @Test public void
    greeting_resource_returns_expected_greeting_in_body() {
        given().
                param("name", "Johan").
                contentType(ContentType.JSON).
                when().
                get("/greeting").
                then().
                contentType(ContentType.JSON).
                body("content", equalTo("Hello, Johan!"));
    }

}
