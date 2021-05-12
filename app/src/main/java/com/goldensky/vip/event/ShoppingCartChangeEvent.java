package com.goldensky.vip.event;


public class ShoppingCartChangeEvent {
    private Boolean success;

    public ShoppingCartChangeEvent(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
