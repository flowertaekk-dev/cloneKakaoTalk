package com.example.clonekakaotalk.utils.defs;

/**
 * Provide information of Activity transportation
 */
public enum Transport {
    FROM_CHATTING_ROOM(true),
    ;

    private boolean value;
    Transport(boolean value) {
        this.value = value;
    }

    public boolean value() {
        return this.value;
    }
}
