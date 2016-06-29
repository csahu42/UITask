package com.gmail.csahu42.uitask.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.gmail.csahu42.uitask.R;
import com.gmail.csahu42.uitask.databinding.ActivityProfileMusicBinding;
import com.gmail.csahu42.uitask.ui.fragment.MusicFragment;
import java.util.ArrayList;
import java.util.List;

import static android.R.id.home;

public class ProfileMusicActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

  static Intent getIntentFor(Context context) {
    return new Intent(context, ProfileMusicActivity.class);
  }

  private final int[] tabSelectedIcons =
      { R.drawable.grid_icon_white, R.drawable.grid_icon_white, R.drawable.list_icon_white };
  private final int[] tabUnSelectedIcons =
      { R.drawable.grid_gray_icon, R.drawable.grid_gray_icon, R.drawable.list_icon_gray };

  private ActivityProfileMusicBinding musicBinding;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    musicBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile_music);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    assert getSupportActionBar() != null;
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setupViewPager();
  }

  @Override public void onTabSelected(TabLayout.Tab tab) {
    tab.setIcon(tabSelectedIcons[tab.getPosition()]);
  }

  @Override public void onTabUnselected(TabLayout.Tab tab) {
    tab.setIcon(tabUnSelectedIcons[tab.getPosition()]);
  }

  @Override public void onTabReselected(TabLayout.Tab tab) {
    tab.setIcon(tabSelectedIcons[tab.getPosition()]);
  }

  private void setupViewPager() {
    ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    pagerAdapter.addFrag(new MusicFragment());
    pagerAdapter.addFrag(new MusicFragment());
    pagerAdapter.addFrag(new MusicFragment());
    musicBinding.viewpager.setAdapter(pagerAdapter);
    musicBinding.viewpager.setCurrentItem(2);
    musicBinding.viewpager.setOffscreenPageLimit(2);
    musicBinding.tabs.setupWithViewPager(musicBinding.viewpager);
    setupTabIcons();
  }

  @SuppressWarnings("ConstantConditions") private void setupTabIcons() {
    musicBinding.tabs.setOnTabSelectedListener(this);
    musicBinding.tabs.getTabAt(0).setIcon(tabUnSelectedIcons[0]);
    musicBinding.tabs.getTabAt(1).setIcon(tabUnSelectedIcons[1]);
    musicBinding.tabs.getTabAt(2).setIcon(tabSelectedIcons[2]);
  }

  @Override public void onBackPressed() {
    this.finish();
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
