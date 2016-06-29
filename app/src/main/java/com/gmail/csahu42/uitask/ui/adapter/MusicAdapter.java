package com.gmail.csahu42.uitask.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.util.LongSparseArray;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.gmail.csahu42.uitask.R;
import com.gmail.csahu42.uitask.databinding.ActivityProfileMusicItemBinding;
import com.gmail.csahu42.uitask.viewModels.MusicItem;
import java.util.List;

/**
 * Created by Sahu on 6/29/2016.
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

  private final SortedList<MusicItem> list;
  private final LongSparseArray<MusicItem> itemMap;
  private LayoutInflater inflater;

  public MusicAdapter() {
    list = new SortedList<>(MusicItem.class, new SortedListAdapterCallback<MusicItem>(this) {
      @Override public int compare(MusicItem o1, MusicItem o2) {
        return o1.title.compareToIgnoreCase(o2.title);
      }

      @Override public boolean areContentsTheSame(MusicItem oldItem, MusicItem newItem) {
        return oldItem.equals(newItem);
      }

      @Override public boolean areItemsTheSame(MusicItem item1, MusicItem item2) {
        return item1._id == item2._id;
      }
    });
    itemMap = new LongSparseArray<>();
  }

  @Override public MusicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (inflater == null) inflater = LayoutInflater.from(parent.getContext());
    return new MusicAdapter.ViewHolder(
        (ActivityProfileMusicItemBinding) DataBindingUtil.inflate(inflater,
            R.layout.activity_profile_music_item, parent, false));
  }

  @Override public void onBindViewHolder(MusicAdapter.ViewHolder holder, int position) {
    holder.getBinding().setItem(list.get(position));
    holder.getBinding().executePendingBindings();
  }

  @Override public int getItemCount() {
    return list.size();
  }

  public void addItems(@NonNull List<MusicItem> items) {
    for (int i = 0; i < items.size(); i++) {
      addItem(items.get(i));
    }
  }

  private void addItem(@NonNull MusicItem item) {
    final long id = item._id;
    final MusicItem oldItem = itemMap.get(id);
    itemMap.put(id, item);
    if (oldItem == null) {
      list.add(item);
    } else {
      final int position = list.indexOf(oldItem);
      if (position != SortedList.INVALID_POSITION) list.updateItemAt(position, item);
    }
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    private ActivityProfileMusicItemBinding binding;

    ViewHolder(ActivityProfileMusicItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    ActivityProfileMusicItemBinding getBinding() {
      return binding;
    }
  }
}