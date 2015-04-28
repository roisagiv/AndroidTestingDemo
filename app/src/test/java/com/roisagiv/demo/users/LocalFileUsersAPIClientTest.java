package com.roisagiv.demo.users;

import android.content.res.AssetManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 */
@RunWith(JUnit4.class) public class LocalFileUsersAPIClientTest {

  private AssetManager mockedAssetManager;

  private static String JSON = "{\n"
      + "  \"results\": [\n"
      + "    {\n"
      + "      \"user\": {\n"
      + "        \"gender\": \"male\",\n"
      + "        \"name\": {\n"
      + "          \"title\": \"mr\",\n"
      + "          \"first\": \"jamie\",\n"
      + "          \"last\": \"howell\"\n"
      + "        },\n"
      + "        \"location\": {\n"
      + "          \"street\": \"2477 church street\",\n"
      + "          \"city\": \"cardiff\",\n"
      + "          \"state\": \"northumberland\",\n"
      + "          \"zip\": \"21440\"\n"
      + "        },\n"
      + "        \"email\": \"jamie.howell59@example.com\",\n"
      + "        \"username\": \"purplekoala460\",\n"
      + "        \"password\": \"mickey\",\n"
      + "        \"salt\": \"fyWJk8Au\",\n"
      + "        \"md5\": \"230684ebce243809b010a5e21610a54a\",\n"
      + "        \"sha1\": \"9bd8df020af0e17a4e1ad3b73031683b44604fad\",\n"
      + "        \"sha256\": \"ae6a2dd6e7f11d11b997cff06bc53a12a16b24088360f083a102d80be8d35e54\",\n"
      + "        \"registered\": \"1381638872\",\n"
      + "        \"dob\": \"328440573\",\n"
      + "        \"phone\": \"0751-664-440\",\n"
      + "        \"cell\": \"02928-863-201\",\n"
      + "        \"NINO\": \"WX 64 85 88 P\",\n"
      + "        \"picture\": {\n"
      + "          \"large\": \"http://api.randomuser.me/portraits/men/96.jpg\",\n"
      + "          \"medium\": \"http://api.randomuser.me/portraits/med/men/96.jpg\",\n"
      + "          \"thumbnail\": \"http://api.randomuser.me/portraits/thumb/men/96.jpg\"\n"
      + "        },\n"
      + "        \"version\": \"0.5\",\n"
      + "        \"nationality\": \"GB\"\n"
      + "      },\n"
      + "      \"seed\": \"7bebde3ee2145592\"\n"
      + "    },\n"
      + "    {\n"
      + "      \"user\": {\n"
      + "        \"gender\": \"male\",\n"
      + "        \"name\": {\n"
      + "          \"title\": \"mr\",\n"
      + "          \"first\": \"larry\",\n"
      + "          \"last\": \"austin\"\n"
      + "        },\n"
      + "        \"location\": {\n"
      + "          \"street\": \"4976 the drive\",\n"
      + "          \"city\": \"birmingham\",\n"
      + "          \"state\": \"tyne and wear\",\n"
      + "          \"zip\": \"10331\"\n"
      + "        },\n"
      + "        \"email\": \"larry.austin66@example.com\",\n"
      + "        \"username\": \"bigwolf992\",\n"
      + "        \"password\": \"crescent\",\n"
      + "        \"salt\": \"UpsQZ5Zv\",\n"
      + "        \"md5\": \"9ca898f4f1e36c173d6632be009356de\",\n"
      + "        \"sha1\": \"cc015700fcc91becd2c5bb8a578f855b98106b2c\",\n"
      + "        \"sha256\": \"477ea325773cc6007c5bed4ae0192c55f8a3ed7856bab20c2a55f7535cfb65d3\",\n"
      + "        \"registered\": \"1236273031\",\n"
      + "        \"dob\": \"225443136\",\n"
      + "        \"phone\": \"0797-285-180\",\n"
      + "        \"cell\": \"02446-407-766\",\n"
      + "        \"NINO\": \"KS 58 88 52 P\",\n"
      + "        \"picture\": {\n"
      + "          \"large\": \"http://api.randomuser.me/portraits/men/12.jpg\",\n"
      + "          \"medium\": \"http://api.randomuser.me/portraits/med/men/12.jpg\",\n"
      + "          \"thumbnail\": \"http://api.randomuser.me/portraits/thumb/men/12.jpg\"\n"
      + "        },\n"
      + "        \"version\": \"0.5\",\n"
      + "        \"nationality\": \"GB\"\n"
      + "      },\n"
      + "      \"seed\": \"7c9c8e3329a6c0e7\"\n"
      + "    }\n"
      + "  ]\n"
      + "}";

  @Before public void beforeEachTest() throws IOException {
    InputStream inputStream = new ByteArrayInputStream(JSON.getBytes("UTF-8"));

    mockedAssetManager = Mockito.mock(AssetManager.class);
    Mockito.when(mockedAssetManager.open(Mockito.anyString())).thenReturn(inputStream);
  }

  /**
   *
   */
  @Test public void getUsers_should_return_10_users() {
    // arrange
    LocalFileUsersAPIClient usersAPIClient = new LocalFileUsersAPIClient(mockedAssetManager);

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

    LocalFileUsersAPIClient usersAPIClient = new LocalFileUsersAPIClient(mockedAssetManager);

    // act
    List<User> users = usersAPIClient.getUsers();
    User user = users.get(4);

    // assert
    assertEquals("peter may", user.getName());
  }

  /**
   *
   */
  @Test public void getUsers_should_parse_user_email() {
    // arrange
    LocalFileUsersAPIClient usersAPIClient = new LocalFileUsersAPIClient(mockedAssetManager);

    // act
    List<User> users = usersAPIClient.getUsers();
    User user = users.get(4);

    // assert
    assertEquals("peter.may40@example.com", user.getEmail());
  }

  /**
   *
   */
  @Test public void getUsers_should_parse_user_image_url() {
    // arrange
    LocalFileUsersAPIClient usersAPIClient = new LocalFileUsersAPIClient(mockedAssetManager);

    // act
    List<User> users = usersAPIClient.getUsers();
    User user = users.get(4);

    // assert
    assertEquals("http://api.randomuser.me/portraits/med/men/81.jpg", user.getImageUrl());
  }
}
