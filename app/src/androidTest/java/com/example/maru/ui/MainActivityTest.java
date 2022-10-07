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

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.maru.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        onView(allOf(withId(R.id.fab), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 2), isDisplayed()));

        onView(withId(R.id.fab)).perform(click());

        onView(allOf(withId(R.id.name), childAtPosition(childAtPosition(withId(R.id.nameLyt), 0), 0)));

        onView(withId(R.id.name)).perform(scrollTo(), replaceText("abc"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner = onView(allOf(withId(R.id.rooms_spinner), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 2)));

        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction materialTextView = onData(anything()).inAdapterView(childAtPosition(withClassName(is("android.widget.PopupWindow$PopupBackgroundView")), 0)).atPosition(6);
        materialTextView.perform(click());

        ViewInteraction materialButton = onView(allOf(withId(R.id.btn_date_picker), withText("Date"), childAtPosition(childAtPosition(withClassName(is("android.widget.LinearLayout")), 3), 0)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction materialButton2 = onView(allOf(withId(android.R.id.button1), withText("OK"), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction materialButton3 = onView(allOf(withId(R.id.btn_time_picker), withText("Heure"), childAtPosition(childAtPosition(withClassName(is("android.widget.LinearLayout")), 3), 1)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction materialButton4 = onView(allOf(withId(android.R.id.button1), withText("OK"), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 3)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction textInputEditText2 = onView(allOf(withId(R.id.edit_email), childAtPosition(allOf(withId(R.id.emailList), childAtPosition(withClassName(is("android.widget.LinearLayout")), 4)), 0)));
        textInputEditText2.perform(scrollTo(), replaceText("test@email.com"), closeSoftKeyboard());

        ViewInteraction appCompatImageView = onView(allOf(withId(R.id.email_add), childAtPosition(allOf(withId(R.id.emailList), childAtPosition(withClassName(is("android.widget.LinearLayout")), 4)), 1)));
        appCompatImageView.perform(scrollTo(), click());

        ViewInteraction materialButton5 = onView(allOf(withId(R.id.submitButton), withText("Sauvegarder"), childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 6)));
        materialButton5.perform(scrollTo(), click());

        pressBackUnconditionally();
    }

    @Test
    public void deleteMeetingWithSuccess() {
        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.item_meeting_delete_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler),
                                        4),
                                3),
                        isDisplayed()));
        appCompatImageView.perform(click());
    }

    @Test
    public void addMeetingWithMissingSubject() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nameLyt),
                                        0),
                                0)));
        textInputEditText.perform(scrollTo(), replaceText("abcd"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btn_date_picker), withText("Date"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btn_time_picker), withText("Heure"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.submitButton), withText("Sauvegarder"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton5.perform(scrollTo(), click());
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
