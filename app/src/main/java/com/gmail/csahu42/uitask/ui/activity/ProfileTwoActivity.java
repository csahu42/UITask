package com.gmail.csahu42.uitask.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.gmail.csahu42.uitask.R;
import com.gmail.csahu42.uitask.databinding.ActivityProfileBinding;
import com.gmail.csahu42.uitask.ui.fragment.FollowerFragment;
import com.gmail.csahu42.uitask.ui.fragment.StoryFragment;
import java.util.ArrayList;
import java.util.List;

import static android.R.id.home;

public class ProfileTwoActivity extends BaseActivity
    implements TabLayout.OnTabSelectedListener, AppBarLayout.OnOffsetChangedListener {

  static Intent getIntentFor(Context context) {
    return new Intent(context, ProfileTwoActivity.class);
  }

  private ActivityProfileBinding profileBinding;

  private View storyLayout, followerLayout, followingLayout;
  private TextView storyCountView, followerCountView, followingCountView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    profileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    //noinspection ConstantConditions
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    profileBinding.name.setText(R.string.name1);
    getSupportActionBar().setTitle("");
    getSupportActionBar().setSubtitle("");
    profileBinding.location.setText(R.string.location3);
    profileBinding.appBarLayout.addOnOffsetChangedListener(this);
    setupViewPager();
  }

  private void setupViewPager() {
    ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    pagerAdapter.addFrag(new StoryFragment());
    pagerAdapter.addFrag(new FollowerFragment());
    pagerAdapter.addFrag(new FollowerFragment());
    profileBinding.viewpager.setAdapter(pagerAdapter);
    profileBinding.viewpager.setCurrentItem(0);
    profileBinding.viewpager.setOffscreenPageLimit(2);
    profileBinding.tabLayout.setupWithViewPager(profileBinding.viewpager);
    setupTabCustomView();
  }

  @SuppressLint("InflateParams") @SuppressWarnings("ConstantConditions")
  private void setupTabCustomView() {
    getCustomTabView();
    ((TextView) storyLayout.findViewById(R.id.counterName)).setText(R.string.stories);
    storyLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrayLight));
    setStoryCountView(42);
    profileBinding.tabLayout.getTabAt(0).setCustomView(storyLayout);

    ((TextView) followerLayout.findViewById(R.id.counterName)).setText(R.string.followers);
    followerLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrayLight));
    setFollowerCountView(517);
    profileBinding.tabLayout.getTabAt(1).setCustomView(followerLayout);

    ((TextView) followingLayout.findViewById(R.id.counterName)).setText(R.string.following);
    followingLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrayLight));
    setFollowingCountView(284);
    profileBinding.tabLayout.getTabAt(2).setCustomView(followingLayout);
    setSelectedTab(0);
    profileBinding.tabLayout.setOnTabSelectedListener(this);
  }

  private void getCustomTabView() {
    storyLayout = LayoutInflater.from(this).inflate(R.layout.custom_tab_view, null);
    storyCountView = (TextView) storyLayout.findViewById(R.id.counter);

    followerLayout = LayoutInflater.from(this).inflate(R.layout.custom_tab_view, null);
    followerCountView = (TextView) followerLayout.findViewById(R.id.counter);

    followingLayout = LayoutInflater.from(this).inflate(R.layout.custom_tab_view, null);
    followingCountView = (TextView) followingLayout.findViewById(R.id.counter);
  }

  @Override public void onTabSelected(TabLayout.Tab tab) {
    setSelectedTab(tab.getPosition());
  }

  @Override public void onTabUnselected(TabLayout.Tab tab) {
    setUnSelectedTab(tab.getPosition());
  }

  @Override public void onTabReselected(TabLayout.Tab tab) {
    //do nothing
  }

  private void setSelectedTab(final int tabPosition) {
    profileBinding.viewpager.setCurrentItem(tabPosition);
    switch (tabPosition) {
      case 0:
        storyLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite));
        break;
      case 1:
        followerLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite));
        break;
      case 2:
        followingLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite));
        break;
    }
  }

  private void setUnSelectedTab(final int tabPosition) {
    switch (tabPosition) {
      case 0:
        storyLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrayLight));
        break;
      case 1:
        followerLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrayLight));
        break;
      case 2:
        followingLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrayLight));
        break;
    }
  }

  private void setStoryCountView(final int count) {
    storyCountView.setText(String.valueOf(count));
  }

  private void setFollowerCountView(final int count) {
    followerCountView.setText(String.valueOf(count));
  }

  private void setFollowingCountView(final int count) {
    followingCountView.setText(String.valueOf(count));
  }

  @Override public void onBackPressed() {
    this.finish();
  }

  @Override public boolean onPrepareOptionsMenu(Menu menu) {
    menu.findItem(R.id.action_search).setVisible(false).setEnabled(false);
    return true;
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.profile, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_setting) {
      return true;
    } else if (id == home) {
      onBackPressed();
    }
    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("ConstantConditions") @Override
  public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
    if (verticalOffset == 0) {
      getSupportActionBar().setTitle(R.string.profile);
    } else {
      getSupportActionBar().setTitle(R.string.profile);
      profileBinding.name.setText(R.string.name1);
      profileBinding.location.setText(R.string.location3);
    }
  }

  private class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>(3);

    ViewPagerAdapter(@NonNull final FragmentManager manager) {
      super(manager);
    }

    private void addFrag(Fragment fragment) {
      fragmentList.add(fragment);
    }

    @Override public Fragment getItem(int position) {
      return fragmentList.get(position);
    }

    @Override public int getCount() {
      return fragmentList.size();
    }

    @Override public CharSequence getPageTitle(int position) {
      return null;
    }
  }
}