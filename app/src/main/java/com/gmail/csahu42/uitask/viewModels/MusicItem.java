package com.gmail.csahu42.uitask.viewModels;

import com.gmail.csahu42.uitask.utils.StringUtil;

/**
 * Created by Sahu on 6/29/2016.
 */

public class MusicItem {

  public long _id;
  public String title;
  public String duration;

  public MusicItem(long _id, String title, String duration) {
    this._id = _id;
    this.title = title;
    this.duration = duration;
  }

  @Override public boolean equals(Object o) {
    if (o == null || !(o instanceof MusicItem)) return false;
    MusicItem item = (MusicItem) o;
    return item._id == _id && StringUtil.areEqual(item.title, title) && StringUtil.areEqual(
        item.duration, duration);
  }
}
