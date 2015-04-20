package com.roisagiv.demo.users;

import android.content.Context;
import com.roisagiv.demo.utils.AsyncTaskLoader2;
import java.util.List;

/**
 *
 */
public class GetUsersLoader extends AsyncTaskLoader2<List<User>> {

  public GetUsersLoader(Context context) {
    super(context);
  }

  @Override public List<User> loadInBackground() {
    return null;
  }
}
