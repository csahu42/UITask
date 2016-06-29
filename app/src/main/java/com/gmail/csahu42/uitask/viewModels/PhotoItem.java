package com.gmail.csahu42.uitask.viewModels;

import android.support.annotation.DrawableRes;

/**
 * Created by Sahu on 6/29/2016.
 */

public class PhotoItem {

  public long _id;
  @DrawableRes public int photoResId;

  public PhotoItem(long _id, int photoResId) {
    this._id = _id;
    this.photoResId = photoResId;
  }

  @Override public boolean equals(Object o) {
    if (o == null || !(o instanceof PhotoItem)) return false;
    PhotoItem item = (PhotoItem) o;
    return item._id == _id && item.photoResId == photoResId;
  }
}
