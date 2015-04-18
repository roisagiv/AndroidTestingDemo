package com.roisagiv.demo.users;

import android.test.AndroidTestCase;
import android.view.View;
import android.widget.FrameLayout;
import com.roisagiv.demo.R;
import java.util.Collections;
import java.util.List;

/**
 */
public class ListUsersRecyclerViewAdapterTest extends AndroidTestCase {

  /**
   *
   */
  public void test_getItemCount_should_return_size_of_users_array() {
    // arrange
    List<User> users = Collections.nCopies(6, new User());
    ListUsersRecyclerViewAdapter adapter = new ListUsersRecyclerViewAdapter();
    adapter.setUsers(users);

    // act
    int itemCount = adapter.getItemCount();

    // assert
    assertEquals(6, itemCount);
  }

  /**
   *
   */
  public void test_onCreateViewHolder_should_inflate_view_and_create_new_viewHolder() {
    // arrange
    ListUsersRecyclerViewAdapter adapter = new ListUsersRecyclerViewAdapter();

    // act
    FrameLayout parent = new FrameLayout(getContext());
    ListUsersRecyclerViewAdapter.UserViewHolder viewHolder = adapter.onCreateViewHolder(parent, 0);
    View view = viewHolder.itemView;

    // assert
    assertNotNull(view);
    assertNotNull(view.findViewById(R.id.imageview_user_image));
  }
}
