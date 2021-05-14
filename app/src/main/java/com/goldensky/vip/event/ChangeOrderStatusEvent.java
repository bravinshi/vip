package com.goldensky.vip.event;


public class ChangeOrderStatusEvent {
    private Boolean success;

    public ChangeOrderStatusEvent(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
