package com.cocoapatterns.herocam.herocam;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainActivityTests {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Mock
    private Permissions permissions;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        // Inject the activity's dependencies
        final MainActivity activity = (MainActivity) activityRule.getActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.setFragmentFactory(new FragmentFactory(activity.getResources(), permissions));
            }
        });
    }

    /**
     * Test "About" menu option is properly displayed when selected.
     */
    @Test
    public void testNavigationDrawerMenuItemAbout() {

        // GIVEN I open the drawer menu,
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());

        // WHEN I tap on "About",
        onView(withText(R.string.drawer_menu_about)).perform(click());

        // THEN I can see the "LEARN MORE" text and button,
        onView(withId(R.id.about_learn_more_icon)).check(matches(isCompletelyDisplayed()));
        onView(withText(R.string.about_learn_more)).check(matches(isCompletelyDisplayed()));

        // AND I can see the description text and app logo,
        onView(withId(R.id.about_icon)).check(matches(isCompletelyDisplayed()));
        onView(withText(R.string.about_description)).check(matches(isCompletelyDisplayed()));
    }

    /**
     * Test "Help" menu option is properly displayed when selected.
     *
     * <p>
     * TODO: Verify drawables as well,
     * https://medium.com/@dbottillo/android-ui-test-espresso-matcher-for-imageview-1a28c832626f#.7zarajs8x
     * </p>
     *
     * <p>
     * Notice the use of {@link ViewMatchers#isDisplayed()} instead of {@link ViewMatchers#isCompletelyDisplayed()}
     * because we are swiping on the {@link ViewPager}.
     * We could have alternatively introduced some delay to verify with
     * {@link ViewMatchers#isCompletelyDisplayed()} after the swipe is finished (maybe there's a better way than a delay even?).
     * </p>
     *
     * <p>
     * That said, the current use of {@link ViewMatchers#isDisplayed()} is probably more "realistic", as in the end
     * the view pager animation is indeed displaying the incoming / outgoing fragments only partially while swiping.
     * </p>
     */
    @Test
    public void testNavigationDrawerMenuItemHelp() {

        // GIVEN I open the drawer menu,
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());

        // WHEN I tap on "Help",
        onView(withText(R.string.drawer_menu_help)).perform(click());

        // THEN I can see the "HELP STEP 1" text,
        onView(withText(R.string.help_text_step_1)).check(matches(isCompletelyDisplayed()));

        // AND WHEN I swipe left,
        onView(withId(R.id.help_viewPager)).perform(swipeLeft());

        // THEN I can now see the "HELP STEP 2" text,
        onView(withText(R.string.help_text_step_2)).check(matches(isDisplayed()));

        // AND WHEN I swipe left,
        onView(withId(R.id.help_viewPager)).perform(swipeLeft());

        // THEN I can now see the "HELP STEP 3" text,
        onView(withText(R.string.help_text_step_3)).check(matches(isDisplayed()));

        // AND if I keep swiping left,
        onView(withId(R.id.help_viewPager)).perform(swipeLeft());

        // THEN I still see the last option i.e. I've reached the end of the help / on-boarding / tutorial.
        onView(withText(R.string.help_text_step_3)).check(matches(isCompletelyDisplayed()));
    }
}
