package org.xjc.demo.jdk;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Jdk8Test {

    @Test
    public void test() {
        List<String> list = Lists.newArrayList("1", "2", "3");

        List<Integer> rs = list.stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList());

        list.forEach(item -> log.info("数据格式:{}，数据值:{}", item.getClass().getSimpleName(), item));
        rs.forEach(item -> log.info("数据格式:{}，数据值:{}", item.getClass().getSimpleName(), item));
    }

}
