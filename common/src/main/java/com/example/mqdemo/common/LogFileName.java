package com.example.mqdemo.common;

import org.apache.commons.lang3.StringUtils;

public enum LogFileName {
    OPERATE("operate");
    private String logFileName;

    LogFileName(String fileName) {
        this.logFileName = fileName;
    }

    public String getLogFileName() {
        return logFileName;
    }


    public static LogFileName getAwardTypeEnum(String value) {
        LogFileName[] arr = values();
        for (LogFileName item : arr) {
            if (null != item && StringUtils.isNotBlank(item.logFileName)) {
                return item;
            }
        }
        return null;
    }

}
