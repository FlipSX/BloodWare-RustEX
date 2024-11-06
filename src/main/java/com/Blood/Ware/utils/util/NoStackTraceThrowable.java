package com.Blood.Ware.utils.util;


import com.Blood.Ware.BloodWare;
import com.Blood.Ware.utils.Reference;

;



public class NoStackTraceThrowable extends RuntimeException {

    public NoStackTraceThrowable(final String msg) {
        super(msg);
        this.setStackTrace(new StackTraceElement[0]);
    }

    @Override
    public String toString() {
        return "" + BloodWare.version();
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
