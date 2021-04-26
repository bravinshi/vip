package com.goldensky.framework.ui.view;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class NoScrollStaggeredGridLayoutManager extends StaggeredGridLayoutManager {
    private boolean canScroll;

    public NoScrollStaggeredGridLayoutManager(int spanCount, int orientation, boolean canScroll) {
        super(spanCount, orientation);
        this.canScroll = canScroll;
    }

    @Override
    public boolean canScrollHorizontally() {
        return canScroll;
    }

    @Override
    public boolean canScrollVertically() {
        return canScroll;
    }
}
