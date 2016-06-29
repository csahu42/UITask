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
import com.gmail.csahu42.uitask.databinding.FragmentCategoryItemSubItemBinding;
import com.gmail.csahu42.uitask.viewModels.PhotoItem;
import java.util.List;

/**
 * Created by Sahu on 6/29/2016.
 */
 class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

  private SortedList<PhotoItem> photoList;
  private LongSparseArray<PhotoItem> itemMap;
  private LayoutInflater inflater;

   PhotoAdapter() {
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

  @Override public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (inflater == null) inflater = LayoutInflater.from(parent.getContext());
    return new PhotoViewHolder(
        (FragmentCategoryItemSubItemBinding) DataBindingUtil.inflate(inflater,
            R.layout.fragment_category_item_sub_item, parent, false));
  }

  @Override public void onBindViewHolder(PhotoViewHolder holder, int position) {
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

    private FragmentCategoryItemSubItemBinding binding;

    PhotoViewHolder(FragmentCategoryItemSubItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    public FragmentCategoryItemSubItemBinding getBinding() {
      return binding;
    }
  }
}