package com.example.maru.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.maru.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddMeetingActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    private View decorView;

    @Before
    public void setup() {
        mActivityScenarioRule.getScenario().onActivity(activity -> {
           decorView = activity.getWindow().getDecorView();
        });
    }

    @Test
    public void addMeetingWithMissingSubject() {
        onView(allOf(withId(R.id.fab), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 2), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.name), childAtPosition(childAtPosition(withId(R.id.nameLyt), 0), 0))).perform(scrollTo(), replaceText("abcd"), closeSoftKeyboard());

        onView(allOf(withId(R.id.btn_date_picker), withText("Date"), childAtPosition(childAtPosition(withClassName(is("android.widget.LinearLayout")), 3), 0))).perform(scrollTo(), click());

        onView(allOf(withId(android.R.id.button1), withText("OK"), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 3))).perform(scrollTo(), click());

        onView(allOf(withId(R.id.btn_time_picker), withText("Heure"), childAtPosition(childAtPosition(withClassName(is("android.widget.LinearLayout")), 3), 1))).perform(scrollTo(), click());

        onView(allOf(withId(android.R.id.button1), withText("OK"), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 3))).perform(scrollTo(), click());

        onView(allOf(withId(R.id.submitButton), withText("Sauvegarder"), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 6))).perform(scrollTo(), click());
        // Check if we are in the same layout
        onView(withId(R.id.root_create_meeting)).check(matches(isDisplayed()));
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
