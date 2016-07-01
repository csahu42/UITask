package com.gmail.csahu42.uitask.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import com.gmail.csahu42.uitask.R;
import com.gmail.csahu42.uitask.databinding.ActivityNotificationBinding;
import com.gmail.csahu42.uitask.ui.adapter.NotificationAdapter;
import com.gmail.csahu42.uitask.utils.CustomLinearLayoutManager;
import com.gmail.csahu42.uitask.viewModels.NotificationItem;
import com.gmail.csahu42.uitask.viewModels.PhotoItem;
import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends BaseActivity {

  static Intent getIntentFor(Context context) {
    return new Intent(context, NotificationActivity.class);
  }

  private ActivityNotificationBinding notificationBinding;
  private NotificationAdapter adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    notificationBinding = DataBindingUtil.setContentView(this, R.layout.activity_notification);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    assert getSupportActionBar() != null;
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    final CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    notificationBinding.notificationList.setLayoutManager(layoutManager);
    notificationBinding.notificationList.setHasFixedSize(true);
    adapter = new NotificationAdapter();
    notificationBinding.notificationList.setAdapter(adapter);
    populateDummyData();
  }

  private void populateDummyData() {
    List<NotificationItem> list = new ArrayList<>();
    list.add(new NotificationItem(1, 1, R.drawable.geri_pic, "Mary White", " liked ", "Golden Gate",
        "Just now"));
    list.add(new NotificationItem(2, 2, R.drawable.yigit_pic, "Benjamin Rauling", " commented ",
        "your post", "7 minutes ago"));
    List<PhotoItem> followList = new ArrayList<>();
    followList.add(new PhotoItem(1, R.drawable.myphoto));
    followList.add(new PhotoItem(2, R.drawable.people1));
    followList.add(new PhotoItem(3, R.drawable.people2));
    list.add(new NotificationItem(3, 3, R.drawable.geri_pic, "Alexandra jonnsen", " followed ",
        "3 people", "20 minutes ago", followList));
    list.add(new NotificationItem(4, 4, R.drawable.people3, "Zack McMiles", " added ", "1 photo",
        "1 hour ago", R.drawable.italy, 9));
    adapter.addItems(list);
  }

  @Override public void onBackPressed() {
    this.finish();
  }
}
