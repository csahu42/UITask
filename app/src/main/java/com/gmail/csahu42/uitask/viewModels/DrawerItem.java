package com.gmail.csahu42.uitask.viewModels;

import android.support.annotation.DrawableRes;

/**
 * Created by Sahu on 7/3/2016.
 */

public class DrawerItem {

  public @DrawableRes int iconRes;
  public String title;
  public int count;

  public DrawerItem(int iconRes, String title, int count) {
    this.iconRes = iconRes;
    this.title = title;
    this.count = count;
  }
}
