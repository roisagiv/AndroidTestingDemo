package com.roisagiv.demo;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 */
public class HelloWorldActivityTest extends ActivityInstrumentationTestCase2<HelloWorldActivity> {

  public HelloWorldActivityTest() {
    super(HelloWorldActivity.class);
  }

  @UiThreadTest public void test_when_clicking_on_button_should_set_text_on_textview() {
    final EditText nameEditText = (EditText) getActivity().findViewById(R.id.edittext_name);
    nameEditText.setText("World");

    View clickMeButton = getActivity().findViewById(R.id.button_click_me);
    clickMeButton.performClick();

    TextView helloNameTextView = (TextView) getActivity().findViewById(R.id.textview_hello_name);
    assertEquals("Hello World!", helloNameTextView.getText());
  }
}
