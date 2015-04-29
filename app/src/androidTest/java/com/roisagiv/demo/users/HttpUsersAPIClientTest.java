package com.roisagiv.demo.users;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

/**
 */
@RunWith(AndroidJUnit4.class) public class HttpUsersAPIClientTest {

  private MockWebServer mockWebServer;

  private String baseUrl;

  @Before public void beforeEachTest() throws IOException {
    mockWebServer = new MockWebServer();

    String body = readFileFromAssetsAsString("randomuser.me.10.json");
    MockResponse mockResponse = new MockResponse().setResponseCode(200).setBody(body);
    mockWebServer.enqueue(mockResponse);
    mockWebServer.start();

    baseUrl = mockWebServer.getUrl("/").toString();
  }

  @After public void afterEachTest() throws IOException {
    mockWebServer.shutdown();
  }

  @Test public void getUsers_should_return_10_users() throws IOException {
    // arrange

    UsersAPIClient usersAPIClient = new HttpUsersAPIClient(baseUrl);

    // act
    List<User> users = usersAPIClient.getUsers();

    // assert
    assertThat(users.size(), equalTo(10));
  }

  /**
   *
   */
  @Test public void getUsers_should_parse_user_name() {
    // arrange
    UsersAPIClient usersAPIClient = new HttpUsersAPIClient(baseUrl);

    // act
    List<User> users = usersAPIClient.getUsers();
    User user = users.get(4);

    // assert
    assertThat(user.getName(), equalTo("peter may"));
  }

  /**
   *
   */
  @Test public void getUsers_should_parse_user_email() {
    // arrange
    UsersAPIClient usersAPIClient = new HttpUsersAPIClient(baseUrl);

    // act
    List<User> users = usersAPIClient.getUsers();
    User user = users.get(4);

    // assert
    assertThat(user.getEmail(), equalTo("peter.may40@example.com"));
  }

  /**
   *
   */
  @Test public void getUsers_should_parse_user_image_url() {
    // arrange
    UsersAPIClient usersAPIClient = new HttpUsersAPIClient(baseUrl);

    // act
    List<User> users = usersAPIClient.getUsers();
    User user = users.get(4);

    // assert
    assertThat(user.getImageUrl(), equalTo("http://api.randomuser.me/portraits/med/men/81.jpg"));
  }

  @Test public void should_send_results_query_parameter() throws InterruptedException {
    // arrange
    UsersAPIClient usersAPIClient = new HttpUsersAPIClient(baseUrl);

    // act
    usersAPIClient.getUsers();

    RecordedRequest recordedRequest = mockWebServer.takeRequest(100, TimeUnit.MILLISECONDS);
    assertThat(recordedRequest.getPath(), containsString("/?results=10"));
  }

  private String readFileFromAssetsAsString(String file) throws IOException {
    InputStream inputStream = InstrumentationRegistry.getTargetContext().getAssets().open(file);

    byte[] content = new byte[inputStream.available()];
    inputStream.read(content);

    return new String(content);
  }
}
