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
import com.gmail.csahu42.uitask.databinding.FragmentCategoryItemBinding;
import com.gmail.csahu42.uitask.viewModels.CategoryItem;
import com.gmail.csahu42.uitask.viewModels.PhotoItem;
import java.util.List;

/**
 * Created by Sahu on 6/28/2016.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
  private final String TAG = CategoryAdapter.class.getSimpleName();
  private final SortedList<CategoryItem> list;
  private final LongSparseArray<CategoryItem> itemMap;
  private LayoutInflater inflater;
  private final LongSparseArray<PhotoAdapter> adapterHashMap;
  public CategoryAdapter() {
    list = new SortedList<>(CategoryItem.class, new SortedListAdapterCallback<CategoryItem>(this) {
      @Override public int compare(CategoryItem o1, CategoryItem o2) {
        return o1.categoryTitle.compareToIgnoreCase(o2.categoryTitle);
      }

      @Override public boolean areContentsTheSame(CategoryItem oldItem, CategoryItem newItem) {
        return oldItem.equals(newItem);
      }

      @Override public boolean areItemsTheSame(CategoryItem item1, CategoryItem item2) {
        return item1._id == item2._id;
      }
    });
    itemMap = new LongSparseArray<>();
    adapterHashMap = new LongSparseArray<>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (inflater == null) inflater = LayoutInflater.from(parent.getContext());
    FragmentCategoryItemBinding binding =
        DataBindingUtil.inflate(inflater, R.layout.fragment_category_item, parent, false);
    return new ViewHolder(binding);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    final List<PhotoItem> photoItems = list.get(position).getPhotoList();
    if(adapterHashMap.get(list.get(position)._id) == null)
      adapterHashMap.put(list.get(position)._id, new PhotoAdapter());
    holder.getBinding().photoList.setAdapter(adapterHashMap.get(list.get(position)._id));
    adapterHashMap.get(list.get(position)._id).addItems(photoItems);
    holder.getBinding().setItem(list.get(position));
    holder.getBinding().executePendingBindings();
  }

  @Override public int getItemCount() {
    return list.size();
  }

  public void addItems(@NonNull List<CategoryItem> items) {
    for (int i = 0; i < items.size(); i++) {
      addItem(items.get(i));
    }
  }

  private void addItem(@NonNull CategoryItem item) {
    final long id = item._id;
    final CategoryItem oldItem = itemMap.get(id);
    itemMap.put(id, item);
    if (oldItem == null) {
      list.add(item);
    } else {
      final int position = list.indexOf(oldItem);
      if (position != SortedList.INVALID_POSITION) list.updateItemAt(position, item);
    }
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    private FragmentCategoryItemBinding binding;

    ViewHolder(FragmentCategoryItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());
      linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
      binding.photoList.setHasFixedSize(false);
      binding.photoList.setLayoutManager(linearLayoutManager);
      binding.photoList.setNestedScrollingEnabled(false);
    }

    FragmentCategoryItemBinding getBinding() {
      return binding;
    }
  }
}
