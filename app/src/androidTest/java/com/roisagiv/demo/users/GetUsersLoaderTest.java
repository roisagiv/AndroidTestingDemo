package com.roisagiv.demo.users;

import android.test.AndroidTestCase;
import java.util.List;

/**
 *
 */
public class GetUsersLoaderTest extends AndroidTestCase {

  public void test_loadInBackground_should_call_UsersAPIClient() {
    // arrange
    TestableUsersAPIClient usersAPIClient = new TestableUsersAPIClient();
    GetUsersLoader loader = new GetUsersLoader(getContext());

    // act
    loader.loadInBackground();

    // assert
    assertTrue(usersAPIClient.getUsers_invoked);
  }

  private static class TestableUsersAPIClient implements UsersAPIClient {

    private boolean getUsers_invoked = false;

    @Override public List<User> getUsers() {
      getUsers_invoked = true;
      return null;
    }
  }
}
