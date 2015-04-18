package com.roisagiv.demo.users;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.roisagiv.demo.R;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class ListUsersRecyclerViewAdapter
    extends RecyclerView.Adapter<ListUsersRecyclerViewAdapter.UserViewHolder> {

  private final List<User> users;

  public ListUsersRecyclerViewAdapter() {
    users = new ArrayList<>();
  }

  public void setUsers(List<User> users) {
    this.users.clear();
    this.users.addAll(users);
  }

  /**
   *
   */
  @Override public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user, parent, false);

    return new UserViewHolder(view);
  }

  /**
   *
   */
  @Override public void onBindViewHolder(UserViewHolder holder, int position) {

  }

  /**
   *
   */
  @Override public int getItemCount() {
    return users.size();
  }

  public static class UserViewHolder extends RecyclerView.ViewHolder {

    public UserViewHolder(View itemView) {
      super(itemView);
    }
  }
}


