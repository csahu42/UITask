package com.gmail.csahu42.uitask.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gmail.csahu42.uitask.R;
import com.gmail.csahu42.uitask.databinding.FragmentProfileBinding;
import com.gmail.csahu42.uitask.ui.adapter.ProfileAdapter;
import com.gmail.csahu42.uitask.viewModels.ProfileItem;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

  private FragmentProfileBinding profileBinding;
  private ProfileAdapter adapter;

  public ProfileFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      getActivity().getWindow()
          .setStatusBarColor(
              ContextCompat.getColor(getContext(), R.color.colorTealShadePrimaryDark));
    } else {
      getActivity().setTheme(R.style.ProfileTealStyle);
    }
    setHasOptionsMenu(true);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    profileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
    return profileBinding.getRoot();
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    adapter = new ProfileAdapter();
    final StaggeredGridLayoutManager layoutManager =
        new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
    profileBinding.list.setLayoutManager(layoutManager);
    profileBinding.list.setAdapter(adapter);
    populateDummyData();
  }

  private void populateDummyData() {
    List<ProfileItem> items = new ArrayList<>();
    items.add(new ProfileItem(1, "Village", "46 minutes ago", R.drawable.feed_image1, 93));
    items.add(new ProfileItem(2, "NY Street", "2 hours ago", R.drawable.feed_pic1, 214));
    items.add(new ProfileItem(3, "Brooklyn Bridge", "5 days ago", R.drawable.feed_image3, 96));
    adapter.addItems(items);
  }
}
