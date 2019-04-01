package com.xu.soufang.base;

/**
 * @author xuzhenqin 2019/4/1
 * 房屋状态
 */
public enum HouseStatus {
    /**
     *未审核
     */
    NOT_AUDITED(0),
    /**
     * 审核通过
     */
    PASSES(1),
    /**
     * 已出租
     */
    RENTED(2),
    /**
     * 逻辑删除
     */
    DELETED(3);

    private int value;

    HouseStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
