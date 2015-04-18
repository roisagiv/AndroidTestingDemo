package com.roisagiv.demo.users;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 */
public class ListUsersRecyclerViewAdapter
    extends RecyclerView.Adapter<ListUsersRecyclerViewAdapter.UserViewHolder> {

  /**
   *
   */
  @Override public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  /**
   *
   */
  @Override public void onBindViewHolder(UserViewHolder holder, int viewType) {

  }

  /**
   *
   */
  @Override public int getItemCount() {
    return 0;
  }

  public static class UserViewHolder extends RecyclerView.ViewHolder {

    public UserViewHolder(View itemView) {
      super(itemView);
    }
  }
}

