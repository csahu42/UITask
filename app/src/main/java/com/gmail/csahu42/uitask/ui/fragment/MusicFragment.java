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
import com.gmail.csahu42.uitask.databinding.FragmentMusicBinding;
import com.gmail.csahu42.uitask.ui.adapter.MusicAdapter;
import com.gmail.csahu42.uitask.utils.DividerItemDecoration;
import com.gmail.csahu42.uitask.viewModels.MusicItem;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment {

  private FragmentMusicBinding binding;
  private MusicAdapter musicAdapter;

  public MusicFragment() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_music, container, false);
    return binding.getRoot();
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    musicAdapter = new MusicAdapter();
    binding.musicList.setAdapter(musicAdapter);
    binding.musicList.addItemDecoration(new DividerItemDecoration(
        ContextCompat.getDrawable(getContext(), R.drawable.divider_horizontal)));
    populateDummyData();
  }

  private void populateDummyData() {
    List<MusicItem> musicItems = new ArrayList<>(4);
    musicItems.add(new MusicItem(1, "London", "3:34"));
    musicItems.add(new MusicItem(2, "I'm Not Who I Was", "2:50"));
    musicItems.add(new MusicItem(3, "Love Never Fails", "2:18"));
    musicItems.add(new MusicItem(4, "Red Sky", "4:22"));
    musicAdapter.addItems(musicItems);
  }
}
