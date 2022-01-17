package com.aysegulapc.fourthhomework.gen.enums;

public enum EnumDebtType {
    GECIKME_ZAMMI("GECIKME_ZAMMI"),
    NORMAL("NORMAL")
    ;

    private String type;

    EnumDebtType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "EnumDebtType{" +
                "type='" + type + '\'' +
                '}';
    }
}
