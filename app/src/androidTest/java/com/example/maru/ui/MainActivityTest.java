package com.example.maru.ui;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBackUnconditionally;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.maru.R;
import com.example.maru.di.DI;
import com.example.maru.service.MeetingApiService;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getMeetingApiService();
    }

    @Test
    public void addMeetingWithSuccess() {

        onView(withId(R.id.fab)).perform(click());
        // Set a name
        onView(allOf(withId(R.id.name), childAtPosition(childAtPosition(withId(R.id.nameLyt), 0), 0))).perform(scrollTo(), replaceText("abc"), closeSoftKeyboard());
        // Click on spinner to set a room
        onView(allOf(withId(R.id.rooms_spinner), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 2))).perform(scrollTo(), click());
        DataInteraction materialTextView = onData(anything()).inAdapterView(childAtPosition(withClassName(is("android.widget.PopupWindow$PopupBackgroundView")), 0)).atPosition(6);
        materialTextView.perform(click());
        // Click on date picker to choose the Date
        onView(allOf(withId(R.id.btn_date_picker), withText("Date"), childAtPosition(childAtPosition(withClassName(is("android.widget.LinearLayout")), 3), 0))).perform(scrollTo(), click());
        // Ok to save the Date
        onView(allOf(withId(android.R.id.button1), withText("OK"), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 3))).perform(scrollTo(), click());
        // Click on date picker to choose the Hour
        onView(allOf(withId(R.id.btn_time_picker), withText("Heure"), childAtPosition(childAtPosition(withClassName(is("android.widget.LinearLayout")), 3), 1))).perform(scrollTo(), click());
        // Ok to save the Hour
        onView(allOf(withId(android.R.id.button1), withText("OK"), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 3))).perform(scrollTo(), click());
        // Click to write an Email
        onView(allOf(withId(R.id.edit_email), childAtPosition(allOf(withId(R.id.emailList), childAtPosition(withClassName(is("android.widget.LinearLayout")), 4)), 0))).perform(scrollTo(), replaceText("test@email.com"), closeSoftKeyboard());
        // Add the Email to the list
        onView(allOf(withId(R.id.email_add), childAtPosition(allOf(withId(R.id.emailList), childAtPosition(withClassName(is("android.widget.LinearLayout")), 4)), 1))).perform(scrollTo(), click());
        // Submit the new Meeting
        onView(allOf(withId(R.id.submitButton), withText("Sauvegarder"), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 6))).perform(scrollTo(), click());

        pressBackUnconditionally();
    }

    @Test
    public void deleteMeetingWithSuccess() {
        // Get Meetings number before delete one
        int itemSizeBegin = service.getMeetings().size();
        // Click on trash to delete meeting
        onView(allOf(withId(R.id.item_meeting_delete_button), childAtPosition(childAtPosition(withId(R.id.recycler), 4), 3), isDisplayed())).perform(click());
        // Get meetings number after delete one
        int itemSizeEnd = service.getMeetings().size();
        // Check the Meetings number
        assertEquals(itemSizeBegin - 1, itemSizeEnd);
    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
