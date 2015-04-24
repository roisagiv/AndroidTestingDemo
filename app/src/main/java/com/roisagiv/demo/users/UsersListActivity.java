package com.roisagiv.demo.users;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.roisagiv.demo.R;
import com.roisagiv.demo.utils.PicassoImageDownloader;
import java.util.List;

public class UsersListActivity extends ActionBarActivity
    implements LoaderManager.LoaderCallbacks<List<User>> {

  private ListUsersRecyclerViewAdapter listUsersRecyclerViewAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_users_list);

    RecyclerView usersRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_users);
    usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    listUsersRecyclerViewAdapter = new ListUsersRecyclerViewAdapter(new PicassoImageDownloader());
    usersRecyclerView.setAdapter(listUsersRecyclerViewAdapter);

    getSupportLoaderManager().initLoader(0, null, this);
  }

  @Override public Loader<List<User>> onCreateLoader(int id, Bundle args) {
    return new GetUsersLoader(this, new LocalFileUsersAPIClient(getAssets()));
  }

  @Override public void onLoadFinished(Loader<List<User>> loader, List<User> data) {
    listUsersRecyclerViewAdapter.setUsers(data);
  }

  @Override public void onLoaderReset(Loader<List<User>> loader) {

  }
}
