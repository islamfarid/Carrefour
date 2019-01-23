package com.example.carrefour;

import com.example.carrefour.news_details.NewsDetailsActivity;
import com.example.carrefour.news_list.NewsListActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class NewsListTest {
    @Rule
    public IntentsTestRule<NewsListActivity> activityRule =
            new IntentsTestRule<>(NewsListActivity.class);

    /**
     * Prepare your test fixture for this test. In this case we register an IdlingResources with
     * Espresso. IdlingResource resource is a great way to tell Espresso when your app is in an
     * idle state. This helps Espresso to synchronize your test actions, which makes tests significantly
     * more reliable.
     */
    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(activityRule.getActivity().getCountingIdlingResource());

    }

    @Test
    public void TectArticlesRecyclerViewISDisplayed() {
        onView(withId(R.id.rv_articles))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testWhenArticlesDisplayedLoadingBarIsHidden() {
        onView(withId(R.id.pb_loading))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    @Test
    public void testOnFirstArticlClickNewsDetailsActivityIsCalled() {
        onView(withId(R.id.rv_articles)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        intended(hasComponent(NewsDetailsActivity.class.getName()));
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(activityRule.getActivity().getCountingIdlingResource());

    }


}