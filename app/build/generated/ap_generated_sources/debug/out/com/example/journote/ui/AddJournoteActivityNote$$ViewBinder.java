// Generated code from Butter Knife. Do not modify!
package com.example.journote.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AddJournoteActivityNote$$ViewBinder<T extends com.example.journote.ui.AddJournoteActivityNote> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230783, "field 'mAddJournalTvDate'");
    target.mAddJournalTvDate = finder.castView(view, 2131230783, "field 'mAddJournalTvDate'");
    view = finder.findRequiredView(source, 2131230780, "field 'mAddJournalEtTitle' and method 'onClick'");
    target.mAddJournalEtTitle = finder.castView(view, 2131230780, "field 'mAddJournalEtTitle'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131230779, "field 'mAddJournalEtContent' and method 'onClick'");
    target.mAddJournalEtContent = finder.castView(view, 2131230779, "field 'mAddJournalEtContent'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131230781, "field 'mAddJournalFabAdd' and method 'onClick'");
    target.mAddJournalFabAdd = finder.castView(view, 2131230781, "field 'mAddJournalFabAdd'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131230782, "field 'mAddJournalFabBack' and method 'onClick'");
    target.mAddJournalFabBack = finder.castView(view, 2131230782, "field 'mAddJournalFabBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131230845, "field 'mFabMenu'");
    target.mFabMenu = finder.castView(view, 2131230845, "field 'mFabMenu'");
    view = finder.findRequiredView(source, 2131230809, "field 'mCommon2TitleLl'");
    target.mCommon2TitleLl = finder.castView(view, 2131230809, "field 'mCommon2TitleLl'");
    view = finder.findRequiredView(source, 2131230806, "field 'mCommon2IvBack' and method 'onClick'");
    target.mCommon2IvBack = finder.castView(view, 2131230806, "field 'mCommon2IvBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131230810, "field 'mCommon2TvTitle'");
    target.mCommon2TvTitle = finder.castView(view, 2131230810, "field 'mCommon2TvTitle'");
    view = finder.findRequiredView(source, 2131230808, "field 'mCommon2IvNoti'");
    target.mCommon2IvNoti = finder.castView(view, 2131230808, "field 'mCommon2IvNoti'");
    view = finder.findRequiredView(source, 2131230807, "field 'mCommon2IvLabel'");
    target.mCommon2IvLabel = finder.castView(view, 2131230807, "field 'mCommon2IvLabel'");
  }

  @Override public void unbind(T target) {
    target.mAddJournalTvDate = null;
    target.mAddJournalEtTitle = null;
    target.mAddJournalEtContent = null;
    target.mAddJournalFabAdd = null;
    target.mAddJournalFabBack = null;
    target.mFabMenu = null;
    target.mCommon2TitleLl = null;
    target.mCommon2IvBack = null;
    target.mCommon2TvTitle = null;
    target.mCommon2IvNoti = null;
    target.mCommon2IvLabel = null;
  }
}
