package com.gmail.csahu42.uitask.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.gmail.csahu42.uitask.R;
import com.gmail.csahu42.uitask.databinding.ActivityMainBinding;
import com.gmail.csahu42.uitask.ui.fragment.CategoryFragment;
import com.gmail.csahu42.uitask.ui.fragment.FeedFragment;
import com.gmail.csahu42.uitask.ui.fragment.ProfileFragment;

public class MainActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private ActivityMainBinding mainBinding;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, mainBinding.drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    mainBinding.drawerLayout.addDrawerListener(toggle);
    toggle.syncState();
    mainBinding.navView.setNavigationItemSelectedListener(this);
    setActionBarTitle(R.string.feed);
    //noinspection ConstantConditions
    getSupportActionBar().setBackgroundDrawable(
        ContextCompat.getDrawable(this, R.color.colorPrimaryGreen));
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.add(R.id.fragment_container, new FeedFragment());
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }

  private void setActionBarTitle(@StringRes int resId) {
    assert getSupportActionBar() != null;
    getSupportActionBar().setTitle(resId);
  }

  @Override public void onBackPressed() {
    if (mainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
      mainBinding.drawerLayout.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.feed, menu);
    return true;
  }

  public void onClickProfileNav(View view) {
    //noinspection ConstantConditions
    getSupportActionBar().setBackgroundDrawable(
        ContextCompat.getDrawable(this, R.color.colorTealShadePrimary));
    startFrangmentTransaction(new ProfileFragment());
    mainBinding.drawerLayout.closeDrawer(GravityCompat.START);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_search) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void startFrangmentTransaction(Fragment fragment) {
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.replace(R.id.fragment_container, fragment);
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }

  @SuppressWarnings("StatementWithEmptyBody") @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_message) {
      setActionBarTitle(R.string.feed);
      //noinspection ConstantConditions
      getSupportActionBar().setBackgroundDrawable(
          ContextCompat.getDrawable(this, R.color.colorPrimaryGreen));
      startFrangmentTransaction(new FeedFragment());
    } else if (id == R.id.nav_photo) {
      setActionBarTitle(R.string.categories);
      //noinspection ConstantConditions
      getSupportActionBar().setBackgroundDrawable(
          ContextCompat.getDrawable(this, R.color.colorRed));
      startFrangmentTransaction(new CategoryFragment());
    } else if (id == R.id.nav_friend) {
      startActivity(ProfileTwoActivity.getIntentFor(this));
    } else if (id == R.id.nav_music) {
      startActivity(ProfileMusicActivity.getIntentFor(this));
    } else if (id == R.id.nav_notification) {
      startActivity(NotificationActivity.getIntentFor(this));
    } else if (id == R.id.nav_settings) {
      startActivity(ProfileOneActivity.getIntentFor(this));
    }

    mainBinding.drawerLayout.closeDrawer(GravityCompat.START);
    return true;
  }
}
