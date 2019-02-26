package org.xjc.demo.web.test;

import java.util.Deque;
import java.util.Queue;

/**
 * Created by xjc on 2018-12-13.
 */
public class JDKTest {

    public void testQueue() {
        Queue queue;
        Deque deque;
    }
    static StringBuilder sb = new StringBuilder();
    public static void test(){
        sb.append("str1");
        sb.insert(1, "str");
    }

    public static void main(String[] args) {
        test();
        System.out.println(sb);
    }
}
