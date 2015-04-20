package com.roisagiv.demo.users;

import android.content.Context;
import com.roisagiv.demo.utils.AsyncTaskLoader2;
import java.util.List;

/**
 *
 */
public class GetUsersLoader extends AsyncTaskLoader2<List<User>> {

  private final UsersAPIClient usersAPIClient;

  public GetUsersLoader(Context context, UsersAPIClient usersAPIClient) {
    super(context);
    this.usersAPIClient = usersAPIClient;
  }

  @Override public List<User> loadInBackground() {
    return usersAPIClient.getUsers();
  }
}
