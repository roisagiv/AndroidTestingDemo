package com.roisagiv.demo.utils;

import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/**
 *
 */
public class PicassoImageDownloader implements ImageDownloader {

  @Override public void setImageUrlInImageView(String url, ImageView imageView) {
    Picasso.with(imageView.getContext()).load(url).into(imageView);
  }
}
