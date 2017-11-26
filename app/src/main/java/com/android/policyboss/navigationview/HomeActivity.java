package com.android.policyboss.navigationview;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.createlead.CreateLeadFragment;
import com.android.policyboss.dashboard.DashboardFragment;
import com.android.policyboss.facade.LoginFacade;
import com.android.policyboss.garaj.RegisterGarageFragment;
import com.android.policyboss.login.LoginActivity;
import com.android.policyboss.salessupport.SalesSupportFragment;
import com.android.policyboss.utility.Constants;

import io.realm.Realm;

public class HomeActivity extends BaseActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    boolean doubleBackToExitPressedOnce = false;
    private Handler mHandler;

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;

    // tags used to attach the fragments
    private static final String TAG_HOME = "Dashboard";
    private static final String TAG_CREATE_LEAD = "Create Lead";
    private static final String TAG_SALES_SUPPORT = "Sales support";
    private static final String TAG_REGISTER_GARAJ = "Register Garage";

    private Toolbar toolbar;

    //set current selected fragment TAG
    public static String CURRENT_TAG = TAG_HOME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setSupportActionBar(toolbar);

        initialise_widgets();
        toolbar.setTitleTextColor(getResources().getColor(R.color.dashboard_text));
        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.application_primary_text_color));
        }
        realm = Realm.getDefaultInstance();


        mHandler = new Handler();

        // initializing navigation menu
        setUpNavigationView();
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment(TAG_HOME);
        }
        bindNavigationView();

    }

    private void bindNavigationView() {
        View profileView = navigationView.getHeaderView(0);
        TextView txtUserName = (TextView) profileView.findViewById(R.id.txtUserName);
        TextView txtEmpCode = (TextView) profileView.findViewById(R.id.txtEmpCode);
        txtUserName.setText(new LoginFacade(HomeActivity.this).getUser().getEmp_Name());
        txtEmpCode.setText(new LoginFacade(HomeActivity.this).getUser().getEmp_Code());
    }

    //Show Garage menu only

    private void ShowGarageMenu() {

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_home).setVisible(false);
        nav_Menu.findItem(R.id.nav_autoLead).setVisible(false);
        nav_Menu.findItem(R.id.nav_salesupport).setVisible(false);

    }

    private void initialise_widgets() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_autoLead:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_CREATE_LEAD;
                        break;

                    case R.id.nav_salesupport:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_SALES_SUPPORT;
                        break;
                    case R.id.nav_register_garaj:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_REGISTER_GARAJ;
                        break;
                    case R.id.nav_logout:
                        Constants.getSharedPreferenceEditor(HomeActivity.this).clear().commit();
                        finish();
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        break;

/*
                    case R.id.nav_offer:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_OFFERS;
                        break;
                    case R.id.nav_referfriend:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_REFER;
                        break;
                    case R.id.nav_pickup_friend:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_REFER;
                        break;
                    case R.id.nav_logout:
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        new UserFacade(MainActivity.this).clearUserCache();
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        break;*/
                    default:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment(CURRENT_TAG);

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {

            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment(CURRENT_TAG);
                return;
            } else {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    return;
                }

                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();


                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }


        }

        // super.onBackPressed();
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setToolbarTitle(String title) {
        toolbar.setTitle(title);
        //getSupportActionBar().setTitle(title);
    }


    private Fragment getHomeFragment() {
        Fragment fragment = null;
        switch (navItemIndex) {
            case 0:
                // home
                fragment = new DashboardFragment();
                return fragment;
            case 1:
                // home
                fragment = new CreateLeadFragment();
                return fragment;
            case 2:
                // photos
                fragment = new SalesSupportFragment();
                return fragment;
            case 3:
                // movies fragment
                fragment = new RegisterGarageFragment();
                return fragment;
            /*case 3:
                // notifications fragment
                fragment = new OffersFragment();
                return fragment;

            case 4:
                fragment = new ReferFriendFragment();
                return fragment;*/

            default:
                fragment = new DashboardFragment();
                return fragment;
        }
    }

    private void loadHomeFragment(String title) {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle(title);

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
        // toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }


}
