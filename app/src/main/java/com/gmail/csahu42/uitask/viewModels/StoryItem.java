package com.gmail.csahu42.uitask.viewModels;

import android.support.annotation.DrawableRes;
import com.gmail.csahu42.uitask.utils.StringUtil;

/**
 * Created by Sahu on 6/30/2016.
 */

public class StoryItem {

  public long _id;
  @DrawableRes public int avatarResId;
  public String title;
  public String content;

  public StoryItem(long _id, int avatarResId, String title, String content) {
    this._id = _id;
    this.avatarResId = avatarResId;
    this.title = title;
    this.content = content;
  }

  @Override public boolean equals(Object o) {
    if (o == null || !(o instanceof StoryItem)) return false;
    StoryItem item = (StoryItem) o;
    return item._id == _id && item.avatarResId == avatarResId && StringUtil.areEqual(item.title,
        title) && StringUtil.areEqual(item.content, content);
  }
}
