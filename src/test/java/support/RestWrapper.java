package support;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.RequestBodyEntity;
import org.json.JSONObject;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class RestWrapper {

    private String baseUrl = "https://skryabin.com/recruit/api/v1/";
    private String loginToken;

    private final String CONTENT_TYPE = "Content-Type";
    private final String JSON = "application/json";

    public void login(HashMap<String, String> credentials) throws UnirestException {
        RequestBodyEntity request = Unirest.post(baseUrl + "login")
                .header(CONTENT_TYPE, JSON)
                .body(new JSONObject(credentials));

        HttpResponse<JsonNode> response = request.asJson();
        assertThat(response.getStatus()).isEqualTo(200);
        JSONObject body = response.getBody().getObject();
        loginToken = body.getString("token");
        System.out.println("Login successful! Token: " + loginToken);


    }

}
