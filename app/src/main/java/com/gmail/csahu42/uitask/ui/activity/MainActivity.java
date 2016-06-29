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
import com.gmail.csahu42.uitask.R;
import com.gmail.csahu42.uitask.databinding.ActivityFeedBinding;
import com.gmail.csahu42.uitask.ui.fragment.CategoryFragment;
import com.gmail.csahu42.uitask.ui.fragment.FeedFragment;

public class MainActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private ActivityFeedBinding activityFeedBinding;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityFeedBinding = DataBindingUtil.setContentView(this, R.layout.activity_feed);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, activityFeedBinding.drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    activityFeedBinding.drawerLayout.addDrawerListener(toggle);
    toggle.syncState();
    activityFeedBinding.navView.setNavigationItemSelectedListener(this);
    setActionBarTitle(R.string.feed);
    //noinspection ConstantConditions
    getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this,R.color.colorPrimaryGreen));
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.add(R.id.fragment_container, new FeedFragment());
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }
  private void setActionBarTitle(@StringRes int resId){
    assert getSupportActionBar() != null;
    getSupportActionBar().setTitle(resId);
  }
  @Override public void onBackPressed() {
    if (activityFeedBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
      activityFeedBinding.drawerLayout.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.feed, menu);
    return true;
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
 private void startFrangmentTransaction(Fragment fragment){
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
      getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this,R.color.colorPrimaryGreen));
      startFrangmentTransaction(new FeedFragment());
    } else if (id == R.id.nav_photo) {
      setActionBarTitle(R.string.categories);
      //noinspection ConstantConditions
      getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this,R.color.colorRed));
      startFrangmentTransaction(new CategoryFragment());
    } else if (id == R.id.nav_friend) {

    } else if (id == R.id.nav_music) {
      startActivity(ProfileMusicActivity.getIntentFor(this));
    } else if (id == R.id.nav_notification) {

    } else if (id == R.id.nav_settings) {
      startActivity(ProfileOneActivity.getIntentFor(this));
    }

    activityFeedBinding.drawerLayout.closeDrawer(GravityCompat.START);
    return true;
  }
}
