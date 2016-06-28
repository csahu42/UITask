package com.gmail.csahu42.uitask.viewModels;

import android.support.annotation.DrawableRes;
import com.gmail.csahu42.uitask.utils.StringUtil;

/**
 * Created by Sahu on 6/28/2016.
 */

public class FeedItem {

  public long postId;
  @DrawableRes public int avatarResId;
  public String name;
  public String time;
  public String message;
  @DrawableRes public int feedImageResId;
  public int viewCount;
  public int commentCount;
  public int likeCount;

  public FeedItem(long postId, String name, String time, String message, int viewCount,
      int commentCount, int likeCount, int avatarResId, int feedImageResId) {
    this.postId = postId;
    this.name = name;
    this.time = time;
    this.viewCount = viewCount;
    this.message = message;
    this.commentCount = commentCount;
    this.likeCount = likeCount;
    this.avatarResId = avatarResId;
    this.feedImageResId = feedImageResId;
  }

  @Override public boolean equals(Object o) {
    if (o == null || !(o instanceof FeedItem)) return false;
    FeedItem item = (FeedItem) o;
    return item.postId == postId
        && StringUtil.areEqual(item.name, name)
        && StringUtil.areEqual(item.time, time)
        && StringUtil.areEqual(item.message, message)
        && item.viewCount == viewCount
        && item.commentCount == commentCount
        && item.likeCount == likeCount
        && item.avatarResId == avatarResId
        && item.feedImageResId == feedImageResId;
  }
}
