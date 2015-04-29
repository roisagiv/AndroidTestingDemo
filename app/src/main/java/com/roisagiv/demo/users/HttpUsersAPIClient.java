package com.roisagiv.demo.users;

import com.github.kevinsawicki.http.HttpRequest;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 */
public class HttpUsersAPIClient implements UsersAPIClient {

  private static final String HTTP_API_RANDOM_USER_ME = "http://api.randomuser.me";
  private final String baseUrl;

  public HttpUsersAPIClient() {
    this(HTTP_API_RANDOM_USER_ME);
  }

  public HttpUsersAPIClient(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  @Override public List<User> getUsers() {
    HttpRequest httpRequest = HttpRequest.get(baseUrl, new HashMap<String, String>() {{
      put("results", "10");
    }}, true);

    if (httpRequest.ok()) {

      List<User> users = new ArrayList<>();

      try {
        JSONObject root = new JSONObject(httpRequest.body());

        JSONArray results = root.getJSONArray("results");
        users = new ArrayList<>(results.length());

        for (int i = 0; i < results.length(); i++) {

          JSONObject userJsonObject = results.getJSONObject(i).getJSONObject("user");
          User user = getUserFromJson(userJsonObject);

          users.add(user);
        }

        //
      } catch (JSONException e) {
        e.printStackTrace();
      }

      return users;
    }

    return null;
  }

  private User getUserFromJson(JSONObject userJsonObject) throws JSONException {
    User user = new User();

    user.setEmail(userJsonObject.getString("email"));
    user.setImageUrl(userJsonObject.getJSONObject("picture").getString("medium"));

    JSONObject nameJsonObject = userJsonObject.getJSONObject("name");
    String firstName = nameJsonObject.getString("first");
    String lastName = nameJsonObject.getString("last");
    user.setName(String.format("%s %s", firstName, lastName));

    return user;
  }
}
