package com.gmail.csahu42.uitask.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.gmail.csahu42.uitask.R;
import com.gmail.csahu42.uitask.databinding.ActivityProfilePinkActionBarBinding;

import static android.R.id.home;

public class ProfileOneActivity extends BaseActivity {

  static Intent getIntentFor(Context context) {
    return new Intent(context, ProfileOneActivity.class);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityProfilePinkActionBarBinding binding =
        DataBindingUtil.setContentView(this, R.layout.activity_profile_pink_action_bar);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    assert getSupportActionBar() != null;
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}
