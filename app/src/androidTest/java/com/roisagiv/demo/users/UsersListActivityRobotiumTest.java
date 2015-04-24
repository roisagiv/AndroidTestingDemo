package com.roisagiv.demo.users;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;
import com.robotium.solo.Solo;

/**
 *
 */
public class UsersListActivityRobotiumTest
    extends ActivityInstrumentationTestCase2<UsersListActivity> {

  private Solo solo;

  public UsersListActivityRobotiumTest() {
    super(UsersListActivity.class);
  }

  @Override protected void setUp() throws Exception {
    super.setUp();

    injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    getActivity();

    solo = new Solo(getInstrumentation(), getActivity());
  }

  public void test_should_display_list_of_10_users() {
    solo.waitForText("jamie.howell59@example.com");

    solo.scrollToBottom();

    assertTrue(solo.searchText("allan.reyes42@example.com"));
  }
}
