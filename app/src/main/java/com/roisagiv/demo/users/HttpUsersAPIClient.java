package com.roisagiv.demo.users;

import java.util.List;

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
    return null;
  }
}
