package com.roisagiv.demo.users;

import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 */
public class LocalFileUsersAPIClient implements UsersAPIClient {

  private static final String FILE_NAME = "randomuser.me.10.json";

  private final AssetManager assetManager;

  public LocalFileUsersAPIClient(AssetManager assetManager) {
    this.assetManager = assetManager;
  }

  @Override public List<User> getUsers() {
    List<User> users = null;

    try {
      // read the file from assets
      InputStream inputStream = assetManager.open(FILE_NAME);
      byte[] content = new byte[inputStream.available()];
      inputStream.read(content);

      // convert string into json
      String jsonContent = new String(content);
      JSONObject root = new JSONObject(jsonContent);

      JSONArray results = root.getJSONArray("results");
      users = new ArrayList<>(results.length());

      for (int i = 0; i < results.length(); i++) {

        JSONObject userJsonObject = results.getJSONObject(i).getJSONObject("user");
        User user = getUserFromJson(userJsonObject);

        users.add(user);
      }

      //
    } catch (IOException | JSONException e) {
      e.printStackTrace();
    }

    return users;
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
