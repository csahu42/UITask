package com.gmail.csahu42.uitask.viewBinding;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;
import com.gmail.csahu42.uitask.CircleImageView;

@SuppressWarnings("unused") public final class ImageViewBindings {

  private ImageViewBindings() {
    throw new AssertionError("This class should not be instantiated.");
  }

  @BindingAdapter({ "resId" })
  public static void setImage(CircleImageView imageView, @DrawableRes int drawableResId) {
    imageView.setImageResource(drawableResId);
  }

  @BindingAdapter({ "resId" }) public static void setImage(ImageView imageView,@DrawableRes int drawableResId) {
    imageView.setImageResource(drawableResId);
  }
}
