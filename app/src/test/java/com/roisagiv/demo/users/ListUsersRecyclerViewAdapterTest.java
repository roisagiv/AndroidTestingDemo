package com.roisagiv.demo.users;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.roisagiv.demo.BuildConfig;
import com.roisagiv.demo.R;
import com.roisagiv.demo.utils.ImageDownloader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 */
@RunWith(RobolectricGradleTestRunner.class) @Config(constants = BuildConfig.class, emulateSdk = 18)
public class ListUsersRecyclerViewAdapterTest {

  /**
   *
   */
  @Test public void getItemCount_should_return_size_of_users_array() {
    // arrange
    List<User> users = Collections.nCopies(6, new User());
    ListUsersRecyclerViewAdapter adapter = new ListUsersRecyclerViewAdapter(null);
    adapter.setUsers(users);

    // act
    int itemCount = adapter.getItemCount();

    // assert
    assertThat(itemCount, equalTo(6));
  }

  /**
   *
   */
  @Test public void onCreateViewHolder_should_inflate_view_and_create_new_viewHolder() {
    // arrange
    ListUsersRecyclerViewAdapter adapter = new ListUsersRecyclerViewAdapter(null);

    // act
    FrameLayout parent = new FrameLayout(RuntimeEnvironment.application);
    ListUsersRecyclerViewAdapter.UserViewHolder viewHolder = adapter.onCreateViewHolder(parent, 0);
    View view = viewHolder.itemView;

    // assert
    assertThat(view, notNullValue());
    assertThat(view.findViewById(R.id.imageview_user_image), notNullValue());
  }

  /**
   *
   */
  @Test public void onBindViewHolder_should_set_user_properties_in_views() {
    // arrange
    List<User> users = Arrays.asList(new User(), new User(), new User());
    User userUnderTest = users.get(2);

    userUnderTest.setName("name 1");
    userUnderTest.setEmail("email@email.com");

    ImageDownloader imageDownloader = Mockito.mock(ImageDownloader.class);

    ListUsersRecyclerViewAdapter adapter = new ListUsersRecyclerViewAdapter(imageDownloader);
    adapter.setUsers(users);

    FrameLayout parent = new FrameLayout(RuntimeEnvironment.application);
    ListUsersRecyclerViewAdapter.UserViewHolder viewHolder = adapter.onCreateViewHolder(parent, 0);

    // act
    adapter.onBindViewHolder(viewHolder, 2);

    // assert
    TextView emailTextView = (TextView) viewHolder.itemView.findViewById(R.id.textview_user_email);
    assertThat(emailTextView.getText().toString(), equalTo(userUnderTest.getEmail()));

    TextView nameTextView = (TextView) viewHolder.itemView.findViewById(R.id.textview_user_name);
    assertThat(nameTextView.getText().toString(), equalTo(userUnderTest.getName()));

    ImageView userImageView =
        (ImageView) viewHolder.itemView.findViewById(R.id.imageview_user_image);

    Mockito.verify(imageDownloader)
        .setImageUrlInImageView(userUnderTest.getImageUrl(), userImageView);
  }

  /**
   *
   */
  @Test public void setUsers_should_call_notifyDataSetChanged() {
    // arrange
    List<User> users = Collections.nCopies(6, new User());
    ListUsersRecyclerViewAdapter adapter = new ListUsersRecyclerViewAdapter(null);

    RecyclerView.AdapterDataObserver dataObserver =
        Mockito.mock(RecyclerView.AdapterDataObserver.class);

    adapter.registerAdapterDataObserver(dataObserver);

    // act
    adapter.setUsers(users);

    // assert
    Mockito.verify(dataObserver).onChanged();
  }
}
