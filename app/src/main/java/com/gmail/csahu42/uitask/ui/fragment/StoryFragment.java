package com.gmail.csahu42.uitask.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gmail.csahu42.uitask.R;
import com.gmail.csahu42.uitask.databinding.FragmentStoryBinding;
import com.gmail.csahu42.uitask.ui.adapter.StoryAdapter;
import com.gmail.csahu42.uitask.utils.DividerItemDecoration;
import com.gmail.csahu42.uitask.viewModels.StoryItem;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryFragment extends Fragment {

  private FragmentStoryBinding storyBinding;
  private StoryAdapter storyAdapter;

  public StoryFragment() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    storyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_story, container, false);
    return storyBinding.getRoot();
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    storyAdapter = new StoryAdapter();
    storyBinding.storyList.setAdapter(storyAdapter);
    storyBinding.storyList.addItemDecoration(new DividerItemDecoration(
        ContextCompat.getDrawable(getContext(), R.drawable.divider_horizontal_story)));
    populateDummyData();
  }

  private void populateDummyData() {
    List<StoryItem> story = new ArrayList<>(3);
    story.add(new StoryItem(1, R.drawable.italy, "My trip to Italy",
        "I just returned from italy, visiting Rome, Venice over the coarse of two weeks. I went with my friends"));
    story.add(new StoryItem(2, R.drawable.dog, "Amazing story of a faithful dog",
        "Jasmine become famous for playing mather over the years to puppies, foxes, a fawn, 4.."));
    story.add(new StoryItem(3, R.drawable.music, "My passion for music",
        "I love all kinds of music but mostly older rock (except elevator music and that electronic.."));
    storyAdapter.addItems(story);
  }
}
