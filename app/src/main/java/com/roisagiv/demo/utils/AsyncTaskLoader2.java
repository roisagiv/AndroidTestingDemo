package com.roisagiv.demo.utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * code taken from https://gist.github.com/zergtmn/7510ab0d89a25847d579
 */
public abstract class AsyncTaskLoader2<T> extends AsyncTaskLoader<T> {
  private T mResult;

  public AsyncTaskLoader2(Context context) {
    super(context);
  }

  @Override public void deliverResult(T result) {
    if (isReset()) {
      return;
    }
    mResult = result;
    if (isStarted()) {
      super.deliverResult(result);
    }
  }

  @Override protected void onStartLoading() {
    if (mResult != null) {
      deliverResult(mResult);
    }
    if (takeContentChanged() || mResult == null) {
      forceLoad();
    }
  }

  @Override protected void onStopLoading() {
    cancelLoad();
  }

  @Override protected void onReset() {
    super.onReset();
    onStopLoading();
    mResult = null;
  }
}
