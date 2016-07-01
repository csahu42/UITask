package com.gmail.csahu42.uitask.viewModels;

import android.support.annotation.DrawableRes;
import com.gmail.csahu42.uitask.utils.StringUtil;

/**
 * Created by Sahu on 7/1/2016.
 */

public class ProfileItem {

  public long _id;
  public String location;
  public String time;
  public int likeCount;
  public @DrawableRes int imageResId;

  public ProfileItem(long _id, String location, String time, int imageResId, int likeCount) {
    this._id = _id;
    this.location = location;
    this.time = time;
    this.imageResId = imageResId;
    this.likeCount = likeCount;
  }

  @Override public boolean equals(Object o) {
    if (o == null || !(o instanceof ProfileItem)) return false;
    ProfileItem item = (ProfileItem) o;
    return item._id == _id && item.imageResId == imageResId && item.likeCount == likeCount && StringUtil.areEqual(item.location,
        location) && StringUtil.areEqual(item.time, time);
  }
}
