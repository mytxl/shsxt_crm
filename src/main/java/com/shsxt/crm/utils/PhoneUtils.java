package com.shsxt.crm.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneUtils {
    public static boolean phoneTest(String phone) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        boolean isMatch = false;
        if (phone.length() != 11) {
            System.out.println("手机号应为11位数");
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            isMatch = m.matches();
        }
        return isMatch;
    }
}
