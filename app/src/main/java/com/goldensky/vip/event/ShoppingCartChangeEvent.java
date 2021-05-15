package com.goldensky.vip.event;

public class ShoppingCartChangeEvent {
    private boolean isRefresh;

    public ShoppingCartChangeEvent(boolean isRefresh) {
        this.isRefresh = isRefresh;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }
}
