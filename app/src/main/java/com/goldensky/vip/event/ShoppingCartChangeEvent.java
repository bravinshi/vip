package com.goldensky.vip.event;


public class ShoppingCartChangeEvent {
    private Boolean success;
    private boolean refresh;

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }

    public ShoppingCartChangeEvent(Boolean success, boolean refresh) {
        this.success = success;
        this.refresh = refresh;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
