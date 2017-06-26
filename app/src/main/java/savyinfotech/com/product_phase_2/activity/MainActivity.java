package savyinfotech.com.product_phase_2.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import savyinfotech.com.product_phase_2.R;
import savyinfotech.com.product_phase_2.fragment.ContactFragment;
import savyinfotech.com.product_phase_2.fragment.FeedbackFragment;
import savyinfotech.com.product_phase_2.fragment.HscFragment;
import savyinfotech.com.product_phase_2.fragment.MyDownlaodFragment;
import savyinfotech.com.product_phase_2.fragment.SscFragment;
import savyinfotech.com.product_phase_2.utill.Util;

import static savyinfotech.com.product_phase_2.utill.Util.hideSoftKeyboard;


/**
 * Main Acitivity created on 28 apr 2017
 */

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    private View view;
    private TextView tvFullName;
    private ImageView ivProfile;
    private static boolean isSSC=true;
    private static boolean isHSC=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Util.replaceFragment(MainActivity.this, new SscFragment(isSSC), R.id.content_main_container);

    }

    @Override
    public void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);

        final ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_close, R.string.navigation_drawer_open) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                Util.hideSoftKeyboard(MainActivity.this);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
//        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawer.addDrawerListener(actionBarDrawerToggle);


        actionBarDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerLayout = navigationView.getHeaderView(0);
        tvFullName = (TextView) headerLayout.findViewById(R.id.nav_header_main_tv_full_name);
        ivProfile = (ImageView) headerLayout.findViewById(R.id.nav_neader_main_iv_profile);
//        final String strFullName = Preference.getInstance().mSharedPreferences.getString(Constants.PRE_FULL_NAME, "");
//        if (!strFullName.equalsIgnoreCase("")) {
//            tvFullName.setText(Preference.getInstance().mSharedPreferences.getString(Constants.PRE_FULL_NAME, ""));
//
//        }
//
//        path=path+Preference.getInstance().mSharedPreferences.getString(Constants.PRE_USER_ID,"")+prefix;
//
//
//        Glide.with(MainActivity.this).load(path).asBitmap().placeholder(R.drawable.ic_person_black_24dp).into(new BitmapImageViewTarget(ivProfile) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable circularBitmapDrawable =
//                        RoundedBitmapDrawableFactory.create(getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
//                ivProfile.setImageDrawable(circularBitmapDrawable);
//            }
//        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            hideSoftKeyboard(MainActivity.this);
            getFragmentManager().popBackStack();
        } else {
            buildAlertMessageExit();
        }
    }


    private void buildAlertMessageExit() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.TAG_EXIT_WARN_MSG)).setCancelable(false).setPositiveButton(getString(R.string.TAG_YES), new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int id) {
                callToFinish();
            }
        }).setNegativeButton(getString(R.string.TAG_NO), new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int id) {
                dialog.cancel();
            }
        });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void callToFinish() {
        super.finish();
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//            return true;
//        }


        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_ssc) {
            isSSC=true;
            isHSC=false;
            Util.replaceFragment(MainActivity.this, new SscFragment(isSSC), R.id.content_main_container);
        } else if (id == R.id.nav_hsc) {
            isHSC=true;
            isSSC=false;
            Util.replaceFragment(MainActivity.this, new HscFragment(isSSC), R.id.content_main_container);
        } else if (id == R.id.nav_download) {
            Util.replaceFragment(MainActivity.this, new MyDownlaodFragment(), R.id.content_main_container);
        } else if (id == R.id.nav_contact) {
            Util.replaceFragment(MainActivity.this, new ContactFragment(), R.id.content_main_container);
        } else {
            Util.replaceFragment(MainActivity.this, new FeedbackFragment(), R.id.content_main_container);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
