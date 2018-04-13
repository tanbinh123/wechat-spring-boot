package com.rookiego.www.wechat.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SequenceHelper {
    private static int jvm_seq = 9000;
    private static DateFormat df = new SimpleDateFormat("yyMMddHHmmssSSS");

    public static synchronized String getNextSequence() {
        StringBuffer sb = new StringBuffer();
        sb.append(df.format(new Date()));
        ++jvm_seq;
        if (jvm_seq >= 10000) {
            jvm_seq = 0;
        }

        if (jvm_seq < 10) {
            sb.append("000");
        } else if (jvm_seq < 100) {
            sb.append("00");
        } else if (jvm_seq < 10) {
            sb.append("0");
        }

        sb.append(jvm_seq);
        return sb.toString();
    }
}
