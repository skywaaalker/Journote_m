// Generated code from Butter Knife. Do not modify!
package com.example.journote.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class UpdateJournoteActivity$$ViewBinder<T extends com.example.journote.ui.UpdateJournoteActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131231027, "field 'mUpdateJournalTvDate' and method 'onClick'");
    target.mUpdateJournalTvDate = finder.castView(view, 2131231027, "field 'mUpdateJournalTvDate'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131231022, "field 'mUpdateJournalEtTitle' and method 'onClick'");
    target.mUpdateJournalEtTitle = finder.castView(view, 2131231022, "field 'mUpdateJournalEtTitle'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131231021, "field 'mUpdateJournalEtContent' and method 'onClick'");
    target.mUpdateJournalEtContent = finder.castView(view, 2131231021, "field 'mUpdateJournalEtContent'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131231023, "field 'mUpdateJournalFabAdd' and method 'onClick'");
    target.mUpdateJournalFabAdd = finder.castView(view, 2131231023, "field 'mUpdateJournalFabAdd'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131231024, "field 'mUpdateJournalFabBack' and method 'onClick'");
    target.mUpdateJournalFabBack = finder.castView(view, 2131231024, "field 'mUpdateJournalFabBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131231025, "field 'mUpdateJournalFabDelete' and method 'onClick'");
    target.mUpdateJournalFabDelete = finder.castView(view, 2131231025, "field 'mUpdateJournalFabDelete'");
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
    view = finder.findRequiredView(source, 2131230808, "field 'mCommon2IvNoti' and method 'setJournoteNoti'");
    target.mCommon2IvNoti = finder.castView(view, 2131230808, "field 'mCommon2IvNoti'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.setJournoteNoti(p0);
        }
      });
    view = finder.findRequiredView(source, 2131230807, "field 'mCommon2IvLabel' and method 'setJournoteByLabel'");
    target.mCommon2IvLabel = finder.castView(view, 2131230807, "field 'mCommon2IvLabel'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.setJournoteByLabel(p0);
        }
      });
    view = finder.findRequiredView(source, 2131231033, "field 'mUpdateJournoteTvTag'");
    target.mUpdateJournoteTvTag = finder.castView(view, 2131231033, "field 'mUpdateJournoteTvTag'");
    view = finder.findRequiredView(source, 2131231026, "field 'mUpdateJournoalNotiTime'");
    target.mUpdateJournoalNotiTime = finder.castView(view, 2131231026, "field 'mUpdateJournoalNotiTime'");
    view = finder.findOptionalView(source, 2131230909, null);
    target.mMenuNotiSetdate = finder.castView(view, 2131230909, "field 'mMenuNotiSetdate'");
  }

  @Override public void unbind(T target) {
    target.mUpdateJournalTvDate = null;
    target.mUpdateJournalEtTitle = null;
    target.mUpdateJournalEtContent = null;
    target.mUpdateJournalFabAdd = null;
    target.mUpdateJournalFabBack = null;
    target.mUpdateJournalFabDelete = null;
    target.mFabMenu = null;
    target.mCommon2TitleLl = null;
    target.mCommon2IvBack = null;
    target.mCommon2TvTitle = null;
    target.mCommon2IvNoti = null;
    target.mCommon2IvLabel = null;
    target.mUpdateJournoteTvTag = null;
    target.mUpdateJournoalNotiTime = null;
    target.mMenuNotiSetdate = null;
  }
}
