package com.gmail.csahu42.uitask.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gmail.csahu42.uitask.R;
import com.gmail.csahu42.uitask.databinding.FeedContentBinding;
import com.gmail.csahu42.uitask.ui.adapter.FeedAdapter;
import com.gmail.csahu42.uitask.utils.DividerItemDecoration;
import com.gmail.csahu42.uitask.viewModels.FeedItem;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

  private FeedAdapter feedAdapter;
  private FeedContentBinding contentBinding;

  public FeedFragment() {
    // Required empty public constructor
  }
  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryGreenDark));
    }
    else getActivity().setTheme(R.style.CategoryStyle);
  }
  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    Context context = new ContextThemeWrapper(getActivity(), R.style.FeedStyle);
    LayoutInflater themeInflater = inflater.cloneInContext(context);
    contentBinding =
        DataBindingUtil.inflate(themeInflater, R.layout.feed_content, container, false);
    return contentBinding.getRoot();
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    feedAdapter = new FeedAdapter();
    contentBinding.feedList.setAdapter(feedAdapter);
    contentBinding.feedList.addItemDecoration(new DividerItemDecoration(
        ContextCompat.getDrawable(getContext(), R.drawable.divider_horizontal)));
    populateDummyData();
  }

  private void populateDummyData() {
    final List<FeedItem> feedItems = new ArrayList<>(3);
    feedItems.add(new FeedItem(1, "Robert Downey Jr. ", "09:27 AM",
        "Interestingly, I have one in early january, I know the number, but I do not know the day of the weak?",
        249, 7, 12, R.drawable.yigit_pic, R.drawable.feed_pic1));
    feedItems.add(new FeedItem(2, "Geri HalliWell.", "10:30 AM",
        "What a day at the  zoo making friends with the hungry and the moody... My kind of people. G.x.",
        249, 5, 19, R.drawable.geri_pic, R.drawable.feed_image3));
    feedAdapter.addItems(feedItems);
  }
}
