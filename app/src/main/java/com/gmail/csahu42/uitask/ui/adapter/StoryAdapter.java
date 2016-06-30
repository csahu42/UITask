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
import com.gmail.csahu42.uitask.databinding.FragmentStoryItemBinding;
import com.gmail.csahu42.uitask.viewModels.StoryItem;
import java.util.List;

/**
 * Created by Sahu on 6/30/2016.
 */

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

  private final SortedList<StoryItem> list;
  private final LongSparseArray<StoryItem> itemMap;
  private LayoutInflater inflater;

  public StoryAdapter() {
    list = new SortedList<>(StoryItem.class, new SortedListAdapterCallback<StoryItem>(this) {
      @Override public int compare(StoryItem o1, StoryItem o2) {
        return o1.title.compareToIgnoreCase(o2.title);
      }

      @Override public boolean areContentsTheSame(StoryItem oldItem, StoryItem newItem) {
        return oldItem.equals(newItem);
      }

      @Override public boolean areItemsTheSame(StoryItem item1, StoryItem item2) {
        return item1._id == item2._id;
      }
    });
    itemMap = new LongSparseArray<>();
  }

  @Override public StoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (inflater == null) inflater = LayoutInflater.from(parent.getContext());
    return new StoryAdapter.ViewHolder(
        (FragmentStoryItemBinding) DataBindingUtil.inflate(inflater,
            R.layout.fragment_story_item, parent, false));
  }

  @Override public void onBindViewHolder(StoryAdapter.ViewHolder holder, int position) {
    holder.getBinding().setItem(list.get(position));
    holder.getBinding().executePendingBindings();
  }

  @Override public int getItemCount() {
    return list.size();
  }

  public void addItems(@NonNull List<StoryItem> items) {
    for (int i = 0; i < items.size(); i++) {
      addItem(items.get(i));
    }
  }

  private void addItem(@NonNull StoryItem item) {
    final long id = item._id;
    final StoryItem oldItem = itemMap.get(id);
    itemMap.put(id, item);
    if (oldItem == null) {
      list.add(item);
    } else {
      final int position = list.indexOf(oldItem);
      if (position != SortedList.INVALID_POSITION) list.updateItemAt(position, item);
    }
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    private FragmentStoryItemBinding binding;

    ViewHolder(FragmentStoryItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    FragmentStoryItemBinding getBinding() {
      return binding;
    }
  }

}
