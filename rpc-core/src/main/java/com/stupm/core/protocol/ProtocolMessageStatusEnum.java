package com.stupm.core.protocol;

import lombok.Getter;

@Getter
public enum ProtocolMessageStatusEnum {

    OK("ok" , 20),
    BAD_REQUEST("badRequest" , 40),
    BAD_RESPONSE("badResponse " , 50);


    private final String text;

    private final int value;

    ProtocolMessageStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public static ProtocolMessageStatusEnum getEnumByValue(int value) {
        for (ProtocolMessageStatusEnum e : ProtocolMessageStatusEnum.values()) {
            if (e.value == value) {
                return e;
            }
        }
        return null;
    }
}
