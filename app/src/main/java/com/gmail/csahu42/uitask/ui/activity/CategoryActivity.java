package com.gmail.csahu42.uitask.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.gmail.csahu42.uitask.R;
import com.gmail.csahu42.uitask.databinding.FragmentCategoryBinding;
import com.gmail.csahu42.uitask.ui.adapter.CategoryAdapter;
import com.gmail.csahu42.uitask.utils.CustomLinearLayoutManager;
import com.gmail.csahu42.uitask.viewModels.CategoryItem;
import com.gmail.csahu42.uitask.viewModels.PhotoItem;
import java.util.ArrayList;
import java.util.List;

import static android.R.id.home;

public class CategoryActivity extends BaseActivity {

  static Intent getIntentFor(Context context) {
    return new Intent(context, CategoryActivity.class);
  }

  private FragmentCategoryBinding categoryBinding;
  private CategoryAdapter adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    categoryBinding = DataBindingUtil.setContentView(this, R.layout.fragment_category);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    adapter = new CategoryAdapter(this);
    final CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this);
    categoryBinding.list.setLayoutManager(layoutManager);
    categoryBinding.list.setHasFixedSize(true);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    categoryBinding.list.setAdapter(adapter);
    populateDummyData();
  }

  private void populateDummyData() {
    List<CategoryItem> items = new ArrayList<>(1);
    List<PhotoItem> photoItems1 = new ArrayList<>(4);
    photoItems1.add(new PhotoItem(1, R.drawable.myphoto));
    photoItems1.add(new PhotoItem(2, R.drawable.feed_pic1));
    photoItems1.add(new PhotoItem(3, R.drawable.feed_image1));
    photoItems1.add(new PhotoItem(4, R.drawable.feed_image3));
    CategoryItem item1 = new CategoryItem(1, "LandScape", 2348, photoItems1);
    items.add(item1);

    List<PhotoItem> photoItems2 = new ArrayList<>(4);
    photoItems1.add(new PhotoItem(1, R.drawable.myphoto));
    photoItems1.add(new PhotoItem(2, R.drawable.yigit_pic));
    photoItems1.add(new PhotoItem(3, R.drawable.geri_pic));
    photoItems1.add(new PhotoItem(4, R.drawable.prof_pic1));
    CategoryItem item2 = new CategoryItem(2, "People", 3649, photoItems2);
    items.add(item2);

    List<PhotoItem> photoItems3 = new ArrayList<>(4);
    photoItems1.add(new PhotoItem(1, R.drawable.myphoto));
    photoItems1.add(new PhotoItem(2, R.drawable.feed_pic1));
    photoItems1.add(new PhotoItem(3, R.drawable.feed_image1));
    photoItems1.add(new PhotoItem(4, R.drawable.feed_image3));
    CategoryItem item3 = new CategoryItem(3, "Architecture", 5207, photoItems3);
    items.add(item3);

    adapter.addItems(items);
  }

  @Override public void onBackPressed() {
    this.finish();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
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
    } else if (id == home) {
      onBackPressed();
    }

    return super.onOptionsItemSelected(item);
  }
}
