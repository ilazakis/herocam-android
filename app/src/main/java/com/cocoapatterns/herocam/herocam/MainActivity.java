package com.cocoapatterns.herocam.herocam;

import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.cocoapatterns.herocam.herocam.about.AboutFragment;
import com.cocoapatterns.herocam.herocam.camera.CameraFragment;
import com.cocoapatterns.herocam.herocam.contact.Contact;
import com.cocoapatterns.herocam.herocam.help.HelpContainerFragment;
import com.cocoapatterns.herocam.herocam.rate.GooglePlay;

public class MainActivity extends AppCompatActivity {

    // UI variables
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;

    /**
     * Fragment factory. <b>Meant to be injected in the activity.</b>
     */
    private FragmentFactory fragmentFactory = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // We are using a toolbar, so we need to tell the activity to use it as an actionbar too.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get a reference to the menu drawer.
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_menu_open,  R.string.drawer_menu_close);
        drawer.addDrawerListener(drawerToggle);

        // Setup the navigation view.
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(navigationView);
    }

    private void selectDefaultMenuItem() {
        // We default to the camera screen
        MenuItem camera = navigationView.getMenu().findItem(R.id.drawer_menu_camera);
        selectDrawerItem(camera);
    }

    public void setFragmentFactory(FragmentFactory factory) {
        this.fragmentFactory = factory;
        selectDefaultMenuItem();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    // TODO: lambda instead of listener?
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {

        Fragment fragment = null;
        switch(menuItem.getItemId()) {
            case R.id.drawer_menu_camera:
                fragment = fragmentFactory.getCameraFragment();
                break;
            case R.id.drawer_menu_help:
                fragment = fragmentFactory.getHelpFragment();
                break;
            case R.id.drawer_menu_rate:
                GooglePlay.visitGooglePlay(this);
                return;
            case R.id.drawer_menu_contact:
                Contact.visitWebsiteContact(this);
                return;
            case R.id.drawer_menu_about:
                fragment = fragmentFactory.getAboutFragment();
                break;
            default: // noop
                break;
        }

        if (fragment != null) {
            updateFragmentAndMenu(fragment, menuItem);
        }
    }

    private void updateFragmentAndMenu(Fragment fragment, MenuItem menuItem) {
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected menu item, change the title to reflect the selection and close the drawer.
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawer.closeDrawers();
    }
}
