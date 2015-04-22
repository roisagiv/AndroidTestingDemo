package com.roisagiv.demo.users;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;
import com.roisagiv.demo.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 *
 */
public class UsersListActivityEspressoTest
    extends ActivityInstrumentationTestCase2<UsersListActivity> {

  public UsersListActivityEspressoTest() {
    super(UsersListActivity.class);
  }

  @Override protected void setUp() throws Exception {
    super.setUp();

    injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    getActivity();
  }

  public void test_should_display_list_of_10_users() {
    // act
    onView(withId(R.id.recyclerview_users)).perform(scrollToPosition(9));

    // assert
    onView(withText("allan.reyes42@example.com")).check(matches(isDisplayed()));
  }
}
