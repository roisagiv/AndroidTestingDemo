package com.roisagiv.demo.users;

import android.support.v7.widget.RecyclerView;
import android.test.AndroidTestCase;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.roisagiv.demo.R;
import com.roisagiv.demo.utils.ImageDownloader;
import java.util.Arrays;
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
    ListUsersRecyclerViewAdapter adapter = new ListUsersRecyclerViewAdapter(null);
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
    ListUsersRecyclerViewAdapter adapter = new ListUsersRecyclerViewAdapter(null);

    // act
    FrameLayout parent = new FrameLayout(getContext());
    ListUsersRecyclerViewAdapter.UserViewHolder viewHolder = adapter.onCreateViewHolder(parent, 0);
    View view = viewHolder.itemView;

    // assert
    assertNotNull(view);
    assertNotNull(view.findViewById(R.id.imageview_user_image));
  }

  /**
   *
   */
  public void test_onBindViewHolder_should_set_user_properties_in_views() {
    // arrange
    List<User> users = Arrays.asList(new User(), new User(), new User());
    User userUnderTest = users.get(2);

    userUnderTest.setName("name 1");
    userUnderTest.setEmail("email@email.com");

    TestableImageDownloader imageDownloader = new TestableImageDownloader();

    ListUsersRecyclerViewAdapter adapter = new ListUsersRecyclerViewAdapter(imageDownloader);
    adapter.setUsers(users);

    FrameLayout parent = new FrameLayout(getContext());
    ListUsersRecyclerViewAdapter.UserViewHolder viewHolder = adapter.onCreateViewHolder(parent, 0);

    // act
    adapter.onBindViewHolder(viewHolder, 2);

    // assert
    TextView emailTextView = (TextView) viewHolder.itemView.findViewById(R.id.textview_user_email);
    assertEquals(userUnderTest.getEmail(), emailTextView.getText());

    TextView nameTextView = (TextView) viewHolder.itemView.findViewById(R.id.textview_user_name);
    assertEquals(userUnderTest.getName(), nameTextView.getText());

    ImageView userImageView =
        (ImageView) viewHolder.itemView.findViewById(R.id.imageview_user_image);
    assertEquals(userImageView, imageDownloader.imageView);
    assertEquals(userUnderTest.getImageUrl(), imageDownloader.url);
  }

  /**
   *
   */
  public void test_setUsers_should_call_notifyDataSetChanged() {
    // arrange
    List<User> users = Collections.nCopies(6, new User());
    ListUsersRecyclerViewAdapter adapter = new ListUsersRecyclerViewAdapter(null);

    TestableAdapterDataObserver dataObserver = new TestableAdapterDataObserver();
    adapter.registerAdapterDataObserver(dataObserver);

    // act
    adapter.setUsers(users);

    // assert
    assertTrue(dataObserver.onChangedInvoked);
  }

  /**
   * A special <code><pre>RecyclerView.AdapterDataObserver</pre></code> that allows tracking the
   * calls to onChanged methods
   */
  private static class TestableAdapterDataObserver extends RecyclerView.AdapterDataObserver {

    private boolean onChangedInvoked = false;

    @Override public void onChanged() {
      super.onChanged();

      onChangedInvoked = true;
    }
  }

  /**
   *
   */
  private static class TestableImageDownloader implements ImageDownloader {

    private String url = null;
    private ImageView imageView = null;

    @Override public void setImageUrlInImageView(String url, ImageView imageView) {

      this.url = url;
      this.imageView = imageView;
    }
  }
}
