package com.gmail.csahu42.uitask.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.util.LongSparseArray;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.gmail.csahu42.uitask.R;
import com.gmail.csahu42.uitask.databinding.ActivityNotificationItemBinding;
import com.gmail.csahu42.uitask.databinding.ActivityNotificationsFollowItemBinding;
import com.gmail.csahu42.uitask.viewModels.NotificationItem;
import com.gmail.csahu42.uitask.viewModels.PhotoItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sahu on 7/1/2016.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

  private final String TAG = NotificationAdapter.class.getSimpleName();
  private final List<NotificationItem> list;
  private final LongSparseArray<NotificationItem> itemMap;
  private LayoutInflater inflater;
  private final LongSparseArray<FollowAdapter> adapterHashMap;

  public NotificationAdapter() {

   /* list = new SortedList<>(NotificationItem.class,
        new SortedListAdapterCallback<NotificationItem>(this) {
          @Override public int compare(NotificationItem o1, NotificationItem o2) {
            return o1.title.equals(o2.title) ? 1: -1
                    ;
          }

          @Override
          public boolean areContentsTheSame(NotificationItem oldItem, NotificationItem newItem) {
            return oldItem.equals(newItem);
          }

          @Override public boolean areItemsTheSame(NotificationItem item1, NotificationItem item2) {
            return item1._id == item2._id;
          }
        });*/
    list = new ArrayList<>();
    itemMap = new LongSparseArray<>();
    adapterHashMap = new LongSparseArray<>();
  }

  @Override
  public NotificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (inflater == null) inflater = LayoutInflater.from(parent.getContext());
    ActivityNotificationItemBinding binding =
        DataBindingUtil.inflate(inflater, R.layout.activity_notification_item, parent, false);
    return new NotificationAdapter.ViewHolder(binding);
  }

  @Override public void onBindViewHolder(NotificationAdapter.ViewHolder holder, int position) {
    final List<PhotoItem> photoItems = list.get(position).getFollowList();
    if (list.get(position).type == 3) {
      if (adapterHashMap.get(list.get(position)._id) == null) {
        adapterHashMap.put(list.get(position)._id, new FollowAdapter());
      }
      holder.getBinding().followList.setAdapter(adapterHashMap.get(list.get(position)._id));
      adapterHashMap.get(list.get(position)._id).addItems(photoItems);
    }
    holder.getBinding().setItem(list.get(position));
    holder.getBinding().executePendingBindings();
  }

  @Override public int getItemCount() {
    return list.size();
  }

  public void addItems(@NonNull List<NotificationItem> items) {
    for (int i = 0; i < items.size(); i++) {
      addItem(items.get(i));
    }
  }

  private void addItem(@NonNull NotificationItem item) {
    final long id = item._id;
    final NotificationItem oldItem = itemMap.get(id);
    itemMap.put(id, item);
    if (oldItem == null) {
      list.add(item);
    } else {
      final int position = list.indexOf(oldItem);
      if (position != SortedList.INVALID_POSITION) list.add(position, item);
    }
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    private ActivityNotificationItemBinding binding;

    ViewHolder(ActivityNotificationItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
      LinearLayoutManager linearLayoutManager =
          new LinearLayoutManager(binding.getRoot().getContext());
      linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
      binding.followList.setHasFixedSize(false);
      binding.followList.setLayoutManager(linearLayoutManager);
      binding.followList.setNestedScrollingEnabled(false);
    }

    ActivityNotificationItemBinding getBinding() {
      return binding;
    }
  }

  class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.PhotoViewHolder> {

    private SortedList<PhotoItem> photoList;
    private LongSparseArray<PhotoItem> itemMap;
    private LayoutInflater inflater;

    FollowAdapter() {
      photoList = new SortedList<>(PhotoItem.class, new SortedListAdapterCallback<PhotoItem>(this) {
        @Override public int compare(PhotoItem o1, PhotoItem o2) {
          return 0;
        }

        @Override public boolean areContentsTheSame(PhotoItem oldItem, PhotoItem newItem) {
          return oldItem.equals(newItem);
        }

        @Override public boolean areItemsTheSame(PhotoItem item1, PhotoItem item2) {
          return item1._id == item2._id;
        }
      });
      itemMap = new LongSparseArray<>();
    }

    @Override public FollowAdapter.PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      if (inflater == null) inflater = LayoutInflater.from(parent.getContext());
      return new FollowAdapter.PhotoViewHolder(
          (ActivityNotificationsFollowItemBinding) DataBindingUtil.inflate(inflater,
              R.layout.activity_notifications_follow_item, parent, false));
    }

    @Override public void onBindViewHolder(FollowAdapter.PhotoViewHolder holder, int position) {
      holder.getBinding().setItem(photoList.get(position));
      holder.getBinding().executePendingBindings();
    }

    @Override public int getItemCount() {
      return photoList.size();
    }

    void addItems(@NonNull List<PhotoItem> items) {
      for (int i = 0; i < items.size(); i++) {
        addItem(items.get(i));
      }
    }

    private void addItem(@NonNull PhotoItem item) {
      final long id = item._id;
      final PhotoItem oldItem = itemMap.get(id);
      itemMap.put(id, item);
      if (oldItem == null) {
        photoList.add(item);
      } else {
        final int position = photoList.indexOf(oldItem);
        if (position != SortedList.INVALID_POSITION) photoList.updateItemAt(position, item);
      }
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {

      private ActivityNotificationsFollowItemBinding binding;

      PhotoViewHolder(ActivityNotificationsFollowItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
      }

      public ActivityNotificationsFollowItemBinding getBinding() {
        return binding;
      }
    }
  }
}