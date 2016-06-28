package com.gmail.csahu42.uitask.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.gmail.csahu42.uitask.R;
import com.gmail.csahu42.uitask.databinding.ActivityFeedBinding;
import com.gmail.csahu42.uitask.ui.adapter.FeedAdapter;
import com.gmail.csahu42.uitask.utils.DividerItemDecoration;
import com.gmail.csahu42.uitask.viewModels.FeedItem;
import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private ActivityFeedBinding activityFeedBinding;
  private FeedAdapter feedAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityFeedBinding = DataBindingUtil.setContentView(this, R.layout.activity_feed);
    setSupportActionBar(activityFeedBinding.appBarLayout.toolbar);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, activityFeedBinding.drawerLayout,
        activityFeedBinding.appBarLayout.toolbar, R.string.navigation_drawer_open,
        R.string.navigation_drawer_close);
    activityFeedBinding.drawerLayout.setDrawerListener(toggle);
    toggle.syncState();
    activityFeedBinding.navView.setNavigationItemSelectedListener(this);
    feedAdapter = new FeedAdapter();
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.feedList);
    recyclerView.setAdapter(feedAdapter);
    recyclerView.addItemDecoration(
        new DividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.divider_horizontal)));
    populateDummyData();
  }

  private void populateDummyData() {
    final List<FeedItem> feedItems = new ArrayList<>(3);
    feedItems.add(new FeedItem(1, "Robert Downey Jr. ", "09:27 AM",
        "Interestingly, I have one in early january, I know the number, but I do not know the day of the weak?",
        249, 7, 12, R.drawable.sunder, R.drawable.google_home));
    feedItems.add(new FeedItem(2, "Geri HalliWell.", "10:30 AM",
        "What a day at the  zoo making friends with the hungry and the moody... My kind of people. G.x.",
        249, 5, 19, R.drawable.geri_pic, R.drawable.feed_image2));
    feedItems.add(new FeedItem(3, "Yigit Boyar", "09:20 AM",
        "Interestingly, I have one in early january, I know the number, but I do not know the day of the weak?",
        229, 13, 5, R.drawable.yigit_pic, R.drawable.feed_image3));
    feedAdapter.addItems(feedItems);
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

  @SuppressWarnings("StatementWithEmptyBody") @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_message) {
      // Handle the camera action
    } else if (id == R.id.nav_photo) {

    } else if (id == R.id.nav_friend) {

    } else if (id == R.id.nav_music) {

    } else if (id == R.id.nav_notification) {

    } else if (id == R.id.nav_settings) {

    }

    activityFeedBinding.drawerLayout.closeDrawer(GravityCompat.START);
    return true;
  }
}
