package com.shsxt.crm.enums;

public enum DevResult {
    UNDEV(0), DEING(1), DEV_SUCCESS(2), DEV_FAILED(3);
    private Integer type;

    DevResult(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
