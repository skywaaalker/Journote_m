// Generated code from Butter Knife. Do not modify!
package com.example.journote.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginActivity$$ViewBinder<T extends com.example.journote.ui.LoginActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230883, "field 'mLoginName'");
    target.mLoginName = finder.castView(view, 2131230883, "field 'mLoginName'");
    view = finder.findRequiredView(source, 2131230884, "field 'mLoginPassword'");
    target.mLoginPassword = finder.castView(view, 2131230884, "field 'mLoginPassword'");
    view = finder.findRequiredView(source, 2131230793, "field 'mBtnLogin' and method 'onClickLogin'");
    target.mBtnLogin = finder.castView(view, 2131230793, "field 'mBtnLogin'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickLogin();
        }
      });
    view = finder.findRequiredView(source, 2131230880, "field 'mLinkRegister' and method 'onClickLinkRegister'");
    target.mLinkRegister = finder.castView(view, 2131230880, "field 'mLinkRegister'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickLinkRegister();
        }
      });
  }

  @Override public void unbind(T target) {
    target.mLoginName = null;
    target.mLoginPassword = null;
    target.mBtnLogin = null;
    target.mLinkRegister = null;
  }
}
