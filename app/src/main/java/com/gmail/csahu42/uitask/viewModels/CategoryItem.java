package com.gmail.csahu42.uitask.viewModels;

import com.gmail.csahu42.uitask.utils.StringUtil;
import java.util.List;

/**
 * Created by Sahu on 6/29/2016.
 */

public class CategoryItem {

  public long _id;
  public String categoryTitle;
  public int photoCount;
  private List<PhotoItem> photoList;

  public CategoryItem(long _id, String categoryTitle, int photoCount, List<PhotoItem> photoList) {
    this._id = _id;
    this.categoryTitle = categoryTitle;
    this.photoCount = photoCount;
    this.photoList = photoList;
  }

  @Override public boolean equals(Object o) {
    if (o == null || !(o instanceof CategoryItem)) return false;
    CategoryItem item = (CategoryItem) o;
    return item._id == _id
        && StringUtil.areEqual(item.categoryTitle, categoryTitle)
        && item.photoCount == photoCount
        && item.photoList == photoList;
  }
  public List<PhotoItem> getPhotoList() {
    return photoList;
  }

  public void setPhotoList(List<PhotoItem> photoList) {
    this.photoList = photoList;
  }
}
