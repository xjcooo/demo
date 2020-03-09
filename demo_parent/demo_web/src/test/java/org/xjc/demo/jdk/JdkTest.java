package org.xjc.demo.jdk;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.*;

public class JdkTest {

    @Test
    public void test(){
        List<EnumType> list = Lists.newArrayList();
        list.add(EnumType.FIRST);
        list.add(EnumType.SECOND);
        list.add(EnumType.THIRD);
        for (EnumType type : list) {
            System.out.println(type);
        }
    }

    @Test
    public void testVector(){
        List<EnumType> list = new Vector<>();
        list.add(EnumType.FIRST);
        list.add(EnumType.SECOND);
        list.add(EnumType.THIRD);
        list.add(EnumType.THIRD);
        list.add(null);
        list.add(null);
        for (EnumType type : list) {
            System.out.println(type);
        }
    }

    @Test
    public void testHashSet(){
        Set<EnumType> set = Sets.newHashSet();
        set.add(null);
        set.add(null);
        set.add(EnumType.SECOND);
        for (EnumType enumType : set) {
            System.out.println(enumType);
        }
    }

    @Test
    public void testMap(){
        Map<String, EnumType> map = Maps.newHashMap();
        map.put("1", EnumType.SECOND);
        map.put(null, EnumType.FIRST);
        map.put(null, EnumType.THIRD);

        for (String key : map.keySet()) {
            System.out.println(key + "  " + map.get(key));
        }
        System.out.println("-------------------");
        map = new Hashtable<>();
        map.put("1", EnumType.SECOND);
//        map.put(null, EnumType.FIRST);

        for (String key : map.keySet()) {
            System.out.println(key + "  " + map.get(key));
        }
    }

    enum EnumType{
        FIRST,SECOND,THIRD;
    }


}
