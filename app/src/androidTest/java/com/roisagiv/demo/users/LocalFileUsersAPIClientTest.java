package com.roisagiv.demo.users;

import android.test.InstrumentationTestCase;
import java.util.List;

/**
 *
 */
public class LocalFileUsersAPIClientTest extends InstrumentationTestCase {

  /**
   *
   */
  public void test_getUsers_should_return_10_users() {
    // arrange
    LocalFileUsersAPIClient usersAPIClient = new LocalFileUsersAPIClient();

    // act
    List<User> users = usersAPIClient.getUsers();

    // assert
    assertEquals(10, users.size());
  }

  /**
   *
   */
  public void test_getUsers_should_parse_user_name() {
    // arrange
    LocalFileUsersAPIClient usersAPIClient = new LocalFileUsersAPIClient();

    // act
    List<User> users = usersAPIClient.getUsers();
    User user = users.get(4);

    // assert
    assertEquals("peter may", user.getName());
  }

  /**
   *
   */
  public void test_getUsers_should_parse_user_email() {
    // arrange
    LocalFileUsersAPIClient usersAPIClient = new LocalFileUsersAPIClient();

    // act
    List<User> users = usersAPIClient.getUsers();
    User user = users.get(4);

    // assert
    assertEquals("peter.may40@example.com", user.getEmail());
  }

  /**
   *
   */
  public void test_getUsers_should_parse_user_image_url() {
    // arrange
    LocalFileUsersAPIClient usersAPIClient = new LocalFileUsersAPIClient();

    // act
    List<User> users = usersAPIClient.getUsers();
    User user = users.get(4);

    // assert
    assertEquals("http://api.randomuser.me/portraits/med/men/81.jpg", user.getImageUrl());
  }
}
