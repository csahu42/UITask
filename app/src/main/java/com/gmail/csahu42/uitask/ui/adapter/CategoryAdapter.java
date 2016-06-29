package com.gmail.csahu42.uitask.ui.adapter;

import android.content.Context;
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
import java.util.List;

/**
 * Created by Sahu on 6/28/2016.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

  private final SortedList<CategoryItem> list;
  private final LongSparseArray<CategoryItem> itemMap;
  private LayoutInflater inflater;
  private final Context context;

  public CategoryAdapter(Context context) {
    this.context = context;
    list = new SortedList<>(CategoryItem.class, new SortedListAdapterCallback<CategoryItem>(this) {
      @Override public int compare(CategoryItem o1, CategoryItem o2) {
        return 0;
      }

      @Override public boolean areContentsTheSame(CategoryItem oldItem, CategoryItem newItem) {
        return oldItem.equals(newItem);
      }

      @Override public boolean areItemsTheSame(CategoryItem item1, CategoryItem item2) {
        return item1._id == item2._id;
      }
    });
    itemMap = new LongSparseArray<>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (inflater == null) inflater = LayoutInflater.from(parent.getContext());
    FragmentCategoryItemBinding binding =
        DataBindingUtil.inflate(inflater, R.layout.fragment_category_item, parent, false);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
    binding.photoList.setHasFixedSize(true);
    binding.photoList.setLayoutManager(linearLayoutManager);
    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
    return new ViewHolder(binding);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.getBinding().setItem(list.get(position));
    PhotoAdapter photoAdapter = new PhotoAdapter();
    holder.getBinding().photoList.setAdapter(photoAdapter);
    photoAdapter.addItems(list.get(position).getPhotoList());
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
    }

    FragmentCategoryItemBinding getBinding() {
      return binding;
    }
  }
}
