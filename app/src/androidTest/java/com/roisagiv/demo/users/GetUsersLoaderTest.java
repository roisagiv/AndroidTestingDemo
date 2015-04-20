package com.roisagiv.demo.users;

import android.test.AndroidTestCase;
import java.util.List;
import org.mockito.Mockito;

/**
 *
 */
public class GetUsersLoaderTest extends AndroidTestCase {

  @Override protected void setUp() throws Exception {
    super.setUp();

    // required by mockito
    System.setProperty("dexmaker.dexcache", mContext.getCacheDir().getPath());
  }

  public void test_loadInBackground_should_call_UsersAPIClient() {
    // arrange
    TestableUsersAPIClient usersAPIClient = new TestableUsersAPIClient();
    GetUsersLoader loader = new GetUsersLoader(getContext(), usersAPIClient);

    // act
    loader.loadInBackground();

    // assert
    assertTrue(usersAPIClient.getUsers_invoked);
  }

  public void test_loadInBackground_should_call_UsersAPIClient_with_mockito() {
    // arrange
    UsersAPIClient mockedUsersAPIClient = Mockito.mock(UsersAPIClient.class);
    GetUsersLoader loader = new GetUsersLoader(getContext(), mockedUsersAPIClient);

    // act
    loader.loadInBackground();

    // assert
    Mockito.verify(mockedUsersAPIClient).getUsers();
  }

  private static class TestableUsersAPIClient implements UsersAPIClient {

    private boolean getUsers_invoked = false;

    @Override public List<User> getUsers() {
      getUsers_invoked = true;
      return null;
    }
  }
}
