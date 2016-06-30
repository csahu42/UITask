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
import com.gmail.csahu42.uitask.databinding.ContentFeedItemBinding;
import com.gmail.csahu42.uitask.viewModels.FeedItem;
import java.util.List;

/**
 * Created by Sahu on 6/28/2016.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

  private final SortedList<FeedItem> list;
  private final LongSparseArray<FeedItem> itemMap;
  private LayoutInflater inflater;

  public FeedAdapter() {
    list = new SortedList<>(FeedItem.class, new SortedListAdapterCallback<FeedItem>(this) {
      @Override public int compare(FeedItem o1, FeedItem o2) {
        return o1.name.compareToIgnoreCase(o2.name);
      }

      @Override public boolean areContentsTheSame(FeedItem oldItem, FeedItem newItem) {
        return oldItem.equals(newItem);
      }

      @Override public boolean areItemsTheSame(FeedItem item1, FeedItem item2) {
        return item1.postId == item2.postId;
      }
    });
    itemMap = new LongSparseArray<>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (inflater == null) inflater = LayoutInflater.from(parent.getContext());
    return new ViewHolder(
        (ContentFeedItemBinding) DataBindingUtil.inflate(inflater, R.layout.content_feed_item,
            parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.getBinding().setItem(list.get(position));
    holder.getBinding().executePendingBindings();
  }

  @Override public int getItemCount() {
    return list.size();
  }

  public void addItems(@NonNull List<FeedItem> items) {
    for (int i = 0; i < items.size(); i++) {
      addItem(items.get(i));
    }
  }

  private void addItem(@NonNull FeedItem item) {
    final long id = item.postId;
    final FeedItem oldItem = itemMap.get(id);
    itemMap.put(id, item);
    if (oldItem == null) {
      list.add(item);
    } else {
      final int position = list.indexOf(oldItem);
      if (position != SortedList.INVALID_POSITION) list.updateItemAt(position, item);
    }
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    private ContentFeedItemBinding binding;

    ViewHolder(ContentFeedItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    ContentFeedItemBinding getBinding() {
      return binding;
    }
  }
}
