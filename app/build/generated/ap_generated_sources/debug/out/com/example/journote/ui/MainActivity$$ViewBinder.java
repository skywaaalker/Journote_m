// Generated code from Butter Knife. Do not modify!
package com.example.journote.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.example.journote.ui.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230811, "field 'mCommonIvBack'");
    target.mCommonIvBack = finder.castView(view, 2131230811, "field 'mCommonIvBack'");
    view = finder.findRequiredView(source, 2131230814, "field 'mCommonTvTitle'");
    target.mCommonTvTitle = finder.castView(view, 2131230814, "field 'mCommonTvTitle'");
    view = finder.findRequiredView(source, 2131230812, "field 'mCommonIvMenu' and method 'switchJournalOrNote'");
    target.mCommonIvMenu = finder.castView(view, 2131230812, "field 'mCommonIvMenu'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.switchJournalOrNote(p0);
        }
      });
    view = finder.findRequiredView(source, 2131230813, "field 'mCommonTitleLl1'");
    target.mCommonTitleLl1 = finder.castView(view, 2131230813, "field 'mCommonTitleLl1'");
    view = finder.findRequiredView(source, 2131230873, "field 'mJouranlMainSearchView'");
    target.mJouranlMainSearchView = finder.castView(view, 2131230873, "field 'mJouranlMainSearchView'");
    view = finder.findRequiredView(source, 2131230871, "field 'mJouranlMainNotification' and method 'selectJournoteByNoti'");
    target.mJouranlMainNotification = finder.castView(view, 2131230871, "field 'mJouranlMainNotification'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.selectJournoteByNoti();
        }
      });
    view = finder.findRequiredView(source, 2131230869, "field 'mJouranlMainLabel' and method 'selectJournoteByLabel'");
    target.mJouranlMainLabel = finder.castView(view, 2131230869, "field 'mJouranlMainLabel'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.selectJournoteByLabel(p0);
        }
      });
    view = finder.findRequiredView(source, 2131230887, "field 'mMainRvShowJournal'");
    target.mMainRvShowJournal = finder.castView(view, 2131230887, "field 'mMainRvShowJournal'");
    view = finder.findRequiredView(source, 2131230868, "field 'mJournalMainFabAdd' and method 'onClickAddJournote'");
    target.mJournalMainFabAdd = finder.castView(view, 2131230868, "field 'mJournalMainFabAdd'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickAddJournote();
        }
      });
    view = finder.findRequiredView(source, 2131230872, "field 'mJournalMainRlMain'");
    target.mJournalMainRlMain = finder.castView(view, 2131230872, "field 'mJournalMainRlMain'");
    view = finder.findRequiredView(source, 2131230870, "field 'mJournalMainLlMain'");
    target.mJournalMainLlMain = finder.castView(view, 2131230870, "field 'mJournalMainLlMain'");
  }

  @Override public void unbind(T target) {
    target.mCommonIvBack = null;
    target.mCommonTvTitle = null;
    target.mCommonIvMenu = null;
    target.mCommonTitleLl1 = null;
    target.mJouranlMainSearchView = null;
    target.mJouranlMainNotification = null;
    target.mJouranlMainLabel = null;
    target.mMainRvShowJournal = null;
    target.mJournalMainFabAdd = null;
    target.mJournalMainRlMain = null;
    target.mJournalMainLlMain = null;
  }
}
