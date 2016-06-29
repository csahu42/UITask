package com.gmail.csahu42.uitask.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gmail.csahu42.uitask.R;
import com.gmail.csahu42.uitask.databinding.FragmentCategoryBinding;
import com.gmail.csahu42.uitask.ui.adapter.CategoryAdapter;
import com.gmail.csahu42.uitask.utils.CustomLinearLayoutManager;
import com.gmail.csahu42.uitask.viewModels.CategoryItem;
import com.gmail.csahu42.uitask.viewModels.PhotoItem;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

  private FragmentCategoryBinding categoryBinding;
  private CategoryAdapter adapter;

  public CategoryFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(), R.color.colorRedDark));
    }
    else getActivity().setTheme(R.style.CategoryStyle);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    categoryBinding =
        DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
    return categoryBinding.getRoot();
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    adapter = new CategoryAdapter(getContext());
    final CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(getContext());
    categoryBinding.list.setLayoutManager(layoutManager);
    categoryBinding.list.setHasFixedSize(true);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    categoryBinding.list.setAdapter(adapter);
    populateDummyData();
  }

  private void populateDummyData() {
    List<CategoryItem> items = new ArrayList<>(1);
    List<PhotoItem> photoItems1 = new ArrayList<>(4);
    photoItems1.add(new PhotoItem(1, R.drawable.prof_pic1));
    photoItems1.add(new PhotoItem(2, R.drawable.feed_pic1));
    photoItems1.add(new PhotoItem(3, R.drawable.feed_image1));
    photoItems1.add(new PhotoItem(4, R.drawable.feed_image3));
    CategoryItem item1 = new CategoryItem(1, "LandScape", 2348, photoItems1);
    items.add(item1);

   /* List<PhotoItem> photoItems2 = new ArrayList<>(4);
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
    items.add(item3);*/

    adapter.addItems(items);
  }
}
