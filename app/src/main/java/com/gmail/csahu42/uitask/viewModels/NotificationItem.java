package com.gmail.csahu42.uitask.viewModels;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;
import com.gmail.csahu42.uitask.utils.StringUtil;
import java.util.List;

/**
 * Created by Sahu on 7/1/2016.
 */

public class NotificationItem {

  public long _id;
  public int type;
  public @DrawableRes int avatarResId;
  public Spanned title;
  public String time;
  public @DrawableRes int addedImageResId;
  public int likeCount;

  public List<PhotoItem> getFollowList() {
    return followList;
  }

  private List<PhotoItem> followList;

  public NotificationItem(long _id, int type, int avatarResId, String name, String action,
      String object, String time) {
    this._id = _id;
    this.type = type;
    this.avatarResId = avatarResId;
    setTitle(name, action, object);
    this.time = time;
  }

  public NotificationItem(long _id, int type, int avatarResId, String name, String action,
      String object, String time, List<PhotoItem> followList) {
    this._id = _id;
    this.type = type;
    this.avatarResId = avatarResId;
    setTitle(name, action, object);
    this.time = time;
    this.followList = followList;
  }

  public NotificationItem(long _id, int type, int avatarResId, String name, String action,
      String object, String time, int addedImageResId, int likeCount) {
    this._id = _id;
    this.type = type;
    this.avatarResId = avatarResId;
    setTitle(name, action, object);
    this.time = time;
    this.addedImageResId = addedImageResId;
    this.likeCount = likeCount;
  }

  private void setTitle(@NonNull String name, @NonNull String action, @NonNull String object) {
    this.title = Html.fromHtml("<font color='#333333'>"
        + name
        + "</font>"
        + "<font color='#BDBDBD'>"
        + action
        + "</font>"
        + "<font color='333333'>"
        + object
        + "</font>");
  }

  @Override public boolean equals(Object o) {
    if (o == null || !(o instanceof NotificationItem)) return false;
    NotificationItem item = (NotificationItem) o;
    return item._id == _id
        && StringUtil.areEqual(item.time, time)
        && item.title.equals(title)
        && item.likeCount == likeCount
        && item.avatarResId == avatarResId
        && item.addedImageResId == addedImageResId
        && item.followList.equals(followList);
  }
}
