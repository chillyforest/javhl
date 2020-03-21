package com.javhl.course.java8;

import java.util.HashMap;
import java.util.Map;

/**
 * map新接口测试
 */
public class MapTest {

    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        {
            map.forEach((id, val) -> System.out.println(id+"#"+val));
        }

        {
            map.computeIfPresent(3, (num, val) -> val + num);
            map.get(3); // val33
            map.computeIfPresent(9, (num, val) -> null);//此时会删除9对应的value
            map.containsKey(9); // false
            map.computeIfAbsent(23, num -> "val" + num);
            map.containsKey(23); // true
            map.computeIfAbsent(3, num -> "bam");
            map.get(3); // val33
        }

        {
            map.remove(3, "val3");//key和value都存在时，才执行删除
            map.get(3); // val33
            map.remove(3, "val33");
            map.get(3); // null
        }

        {
            map.getOrDefault(42, "not found"); // not found
        }

        {
            //此时map中不存在key:9
            map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
            map.get(9); // val9
            //此时map中存在key:9 = val9
            map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
            map.get(9); // val9concat
        }
    }
}
