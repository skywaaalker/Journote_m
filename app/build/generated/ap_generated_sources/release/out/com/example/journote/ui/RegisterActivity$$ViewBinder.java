// Generated code from Butter Knife. Do not modify!
package com.example.journote.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RegisterActivity$$ViewBinder<T extends com.example.journote.ui.RegisterActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230938, "field 'mRegisterName'");
    target.mRegisterName = finder.castView(view, 2131230938, "field 'mRegisterName'");
    view = finder.findRequiredView(source, 2131230939, "field 'mRegisterPassword'");
    target.mRegisterPassword = finder.castView(view, 2131230939, "field 'mRegisterPassword'");
    view = finder.findRequiredView(source, 2131230940, "field 'mRegisterPasswordCheck'");
    target.mRegisterPasswordCheck = finder.castView(view, 2131230940, "field 'mRegisterPasswordCheck'");
    view = finder.findRequiredView(source, 2131230794, "field 'mBtnRegister' and method 'onClickLogin'");
    target.mBtnRegister = finder.castView(view, 2131230794, "field 'mBtnRegister'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickLogin();
        }
      });
    view = finder.findRequiredView(source, 2131230879, "field 'mLinkLogin' and method 'onClickLinkLogin'");
    target.mLinkLogin = finder.castView(view, 2131230879, "field 'mLinkLogin'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickLinkLogin();
        }
      });
  }

  @Override public void unbind(T target) {
    target.mRegisterName = null;
    target.mRegisterPassword = null;
    target.mRegisterPasswordCheck = null;
    target.mBtnRegister = null;
    target.mLinkLogin = null;
  }
}
