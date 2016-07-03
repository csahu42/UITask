package com.gmail.csahu42.uitask.ui.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.gmail.csahu42.uitask.R;
import com.gmail.csahu42.uitask.databinding.ActivityMainBinding;
import com.gmail.csahu42.uitask.ui.fragment.CategoryFragment;
import com.gmail.csahu42.uitask.ui.fragment.FeedFragment;
import com.gmail.csahu42.uitask.ui.fragment.ProfileFragment;
import com.gmail.csahu42.uitask.viewModels.DrawerItem;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private ActivityMainBinding mainBinding;
  private ActionBarDrawerToggle toggle;
  private ListView drawerList;
  private List<DrawerItem> drawerItems;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    View header = getLayoutInflater().inflate(R.layout.nav_header_feed, mainBinding.navView, false);
    View footer = getLayoutInflater().inflate(R.layout.footer_layout, mainBinding.navView, false);
    ImageView icon = (ImageView) footer.findViewById(R.id.icon);
    icon.setImageResource(R.drawable.settings_icon);
    TextView title = (TextView) footer.findViewById(R.id.title);
    title.setText(R.string.action_settings);
    drawerList = (ListView) findViewById(R.id.itemList);
    createDrawerData();
    drawerList.setAdapter(new DrawerAdapter(this, R.layout.drawer_item, drawerItems));
    assert drawerList != null;
    drawerList.addHeaderView(header);
    drawerList.addFooterView(footer);
    drawerList.setOnItemClickListener(new DrawerItemClickListener());
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    toggle = new ActionBarDrawerToggle(this, mainBinding.drawerLayout, toolbar,
        R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    mainBinding.drawerLayout.addDrawerListener(toggle);

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

  @Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    toggle.syncState();
  }

  private void setActionBarTitle(@StringRes int resId) {
    assert getSupportActionBar() != null;
    getSupportActionBar().setTitle(resId);
  }

  private void createDrawerData() {
    drawerItems = new ArrayList<>();
    drawerItems.add(new DrawerItem(R.drawable.message_icon_gray, getString(R.string.message), 16));
    drawerItems.add(new DrawerItem(R.drawable.photo_icon, getString(R.string.photo), 54));
    drawerItems.add(new DrawerItem(R.drawable.friends_gray_icon, getString(R.string.friends), 192));
    drawerItems.add(new DrawerItem(R.drawable.music_icon_gray, getString(R.string.music), 0));
    drawerItems.add(
        new DrawerItem(R.drawable.notification__icon_gray, getString(R.string.notification), 18));
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

    } else if (id == R.id.nav_photo) {

    } else if (id == R.id.nav_friend) {

    } else if (id == R.id.nav_music) {

    } else if (id == R.id.nav_notification) {

    } else if (id == R.id.nav_settings) {

    }

    mainBinding.drawerLayout.closeDrawer(GravityCompat.START);
    return true;
  }

  private class DrawerItemClickListener implements ListView.OnItemClickListener {

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      selectItem(position);
    }
  }

  private void selectItem(int position) {
    drawerList.setItemChecked(position, true);
    drawerList.getChildAt(position)
        .setBackgroundColor(ContextCompat.getColor(this, R.color.colorSelectedItem));
    switch (position) {
      case 1:
        setActionBarTitle(R.string.feed);
        //noinspection ConstantConditions
        getSupportActionBar().setBackgroundDrawable(
            ContextCompat.getDrawable(this, R.color.colorPrimaryGreen));
        startFrangmentTransaction(new FeedFragment());
        break;
      case 2:
        setActionBarTitle(R.string.categories);
        //noinspection ConstantConditions
        getSupportActionBar().setBackgroundDrawable(
            ContextCompat.getDrawable(this, R.color.colorRed));
        startFrangmentTransaction(new CategoryFragment());
        break;
      case 3:
        startActivity(ProfileTwoActivity.getIntentFor(this));
        break;
      case 4:
        startActivity(ProfileMusicActivity.getIntentFor(this));
        break;
      case 5:
        startActivity(NotificationActivity.getIntentFor(this));
        break;
      case 6:
        startActivity(ProfileOneActivity.getIntentFor(this));
        break;
    }
    mainBinding.drawerLayout.closeDrawer(GravityCompat.START);
  }

  private class DrawerAdapter extends ArrayAdapter<DrawerItem> {

    DrawerAdapter(Context context, int resource, List<DrawerItem> objects) {
      super(context, resource, objects);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
      ViewHolder holder = new ViewHolder();
      if (convertView == null) {
        convertView =
            LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item, parent, false);
        holder.icon = (ImageView) convertView.findViewById(R.id.icon);
        holder.title = (TextView) convertView.findViewById(R.id.title);
        holder.count = (TextView) convertView.findViewById(R.id.count);
        convertView.setTag(holder);
      } else {
        holder = (ViewHolder) convertView.getTag();
      }
      DrawerItem item = getItem(position);
      if (item != null) {
        holder.icon.setImageResource(item.iconRes);
        holder.title.setText(item.title);
        if (item.count > 0) holder.count.setText(String.valueOf(item.count));
      }
      return convertView;
    }

    class ViewHolder {

      ImageView icon;
      TextView title;
      TextView count;
    }
  }
}
