package org.xjc.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by xjc on 2018-10-25.
 */
public class Test {

    public static void main(String[] args) throws ParseException {
        System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").parse("20181025093000").getTime());
        System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").parse("20181025124000").getTime());
    }
}
