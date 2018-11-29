package org.xjc.rabbitmq.test;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Created by xjc on 2018-11-28.
 */
public class MouseClickTest {

    public static void main(String[] args) {
        try {
            int i=1;
            System.out.println("start");
            Thread.sleep(1000);
            while (true) {
                if(i>1000){
                    return;
                }
                mouseClick();
                System.out.println("弟"+i+"次点击鼠标");
                i+=1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mouseClick() throws Exception{
        Robot robot = new Robot();
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(100);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}
