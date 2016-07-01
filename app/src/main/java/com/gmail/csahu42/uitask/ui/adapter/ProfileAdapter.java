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
import com.gmail.csahu42.uitask.databinding.FragmentProfileItemBinding;
import com.gmail.csahu42.uitask.viewModels.ProfileItem;
import java.util.List;

/**
 * Created by Sahu on 7/1/2016.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

  private final SortedList<ProfileItem> list;
  private final LongSparseArray<ProfileItem> itemMap;
  private LayoutInflater inflater;

  public ProfileAdapter() {
    list = new SortedList<>(ProfileItem.class, new SortedListAdapterCallback<ProfileItem>(this) {
      @Override public int compare(ProfileItem o1, ProfileItem o2) {
        return 0;
      }

      @Override public boolean areContentsTheSame(ProfileItem oldItem, ProfileItem newItem) {
        return oldItem.equals(newItem);
      }

      @Override public boolean areItemsTheSame(ProfileItem item1, ProfileItem item2) {
        return item1._id == item2._id;
      }
    });
    itemMap = new LongSparseArray<>();
  }

  @Override public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (inflater == null) inflater = LayoutInflater.from(parent.getContext());
    return new ProfileAdapter.ViewHolder(
        (FragmentProfileItemBinding) DataBindingUtil.inflate(inflater,
            R.layout.fragment_profile_item, parent, false));
  }

  @Override public void onBindViewHolder(ProfileAdapter.ViewHolder holder, int position) {
    holder.getBinding().setItem(list.get(position));
    holder.getBinding().executePendingBindings();
  }

  @Override public int getItemCount() {
    return list.size();
  }

  public void addItems(@NonNull List<ProfileItem> items) {
    for (int i = 0; i < items.size(); i++) {
      addItem(items.get(i));
    }
  }

  private void addItem(@NonNull ProfileItem item) {
    final long id = item._id;
    final ProfileItem oldItem = itemMap.get(id);
    itemMap.put(id, item);
    if (oldItem == null) {
      list.add(item);
    } else {
      final int position = list.indexOf(oldItem);
      if (position != SortedList.INVALID_POSITION) list.updateItemAt(position, item);
    }
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    private FragmentProfileItemBinding binding;

    ViewHolder(FragmentProfileItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    FragmentProfileItemBinding getBinding() {
      return binding;
    }
  }
}
