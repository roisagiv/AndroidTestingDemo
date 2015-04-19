package com.roisagiv.demo.users;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

    notifyDataSetChanged();
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
    User user = users.get(position);

    holder.userNameTextView.setText(user.getName());
    holder.userEmailTextView.setText(user.getEmail());
  }

  /**
   *
   */
  @Override public int getItemCount() {
    return users.size();
  }

  public static class UserViewHolder extends RecyclerView.ViewHolder {

    private final TextView userNameTextView;
    private final TextView userEmailTextView;

    public UserViewHolder(View itemView) {
      super(itemView);

      userNameTextView = (TextView) itemView.findViewById(R.id.textview_user_name);
      userEmailTextView = (TextView) itemView.findViewById(R.id.textview_user_email);
    }
  }
}


