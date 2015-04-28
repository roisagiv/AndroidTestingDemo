package com.roisagiv.demo.users;

import android.content.res.AssetManager;
import com.roisagiv.demo.BuildConfig;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

/**
 */
@RunWith(RobolectricGradleTestRunner.class) @Config(constants = BuildConfig.class, emulateSdk = 18)
public class LocalFileUsersAPIClientTest {

  private AssetManager assetManager;

  @Before public void beforeEachTest() throws IOException {
    assetManager = RuntimeEnvironment.application.getAssets();
  }

  /**
   *
   */
  @Test public void getUsers_should_return_10_users() {
    // arrange
    LocalFileUsersAPIClient usersAPIClient = new LocalFileUsersAPIClient(assetManager);

    // act
    List<User> users = usersAPIClient.getUsers();

    // assert
    assertEquals(10, users.size());
  }

  /**
   *
   */
  @Test public void getUsers_should_parse_user_name() {
    // arrange

    LocalFileUsersAPIClient usersAPIClient = new LocalFileUsersAPIClient(assetManager);

    // act
    List<User> users = usersAPIClient.getUsers();
    User user = users.get(1);

    // assert
    assertEquals("larry austin", user.getName());
  }

  /**
   *
   */
  @Test public void getUsers_should_parse_user_email() {
    // arrange
    LocalFileUsersAPIClient usersAPIClient = new LocalFileUsersAPIClient(assetManager);

    // act
    List<User> users = usersAPIClient.getUsers();
    User user = users.get(1);

    // assert
    assertEquals("larry.austin66@example.com", user.getEmail());
  }

  /**
   *
   */
  @Test public void getUsers_should_parse_user_image_url() {
    // arrange
    LocalFileUsersAPIClient usersAPIClient = new LocalFileUsersAPIClient(assetManager);

    // act
    List<User> users = usersAPIClient.getUsers();
    User user = users.get(1);

    // assert
    assertEquals("http://api.randomuser.me/portraits/med/men/12.jpg", user.getImageUrl());
  }
}
