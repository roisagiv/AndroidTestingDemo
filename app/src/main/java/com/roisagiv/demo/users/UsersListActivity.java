package com.roisagiv.demo.users;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.roisagiv.demo.R;

public class UsersListActivity extends ActionBarActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_users_list);

    RecyclerView usersRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_users);
    usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
  }
}
