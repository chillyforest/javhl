package com.javhl.course.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class StreamTest {

    public static void main(String[] args) {

        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        {//filter

            stringCollection.stream().filter((s) -> s.startsWith("a"))
                    .forEach(System.out::println); // "aaa2", "aaa1"
        }

        {//sort
            stringCollection.stream().sorted().filter((s) -> s.startsWith("a"))
                    .forEach(System.out::println); // "aaa1", "aaa2"

            System.out.println(stringCollection); // ddd2, aaa2, bbb1, aaa1, bbb3, ccc, bbb2, ddd1

        }

        {//match

            boolean anyStartsWithA = stringCollection.stream().anyMatch((s) -> s.startsWith("a"));
            System.out.println(anyStartsWithA); // true
            boolean allStartsWithA = stringCollection.stream().allMatch((s) -> s.startsWith("a"));
            System.out.println(allStartsWithA); // false
            boolean noneStartsWithZ = stringCollection.stream().noneMatch((s) -> s.startsWith("z"));
            System.out.println(noneStartsWithZ); // true
        }

        {//count

            long startsWithB = stringCollection.stream().filter((s) -> s.startsWith("b")).count();
            System.out.println(startsWithB); // 3
        }

        {//reduce

            Optional<String> reduced = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
            reduced.ifPresent(System.out::println); // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"
        }

        {//parallelStream

            int max = 1000000;
            List<String> values = new ArrayList<>(max);
            for (int i = 0; i < max; i++) {
                UUID uuid = UUID.randomUUID();
                values.add(uuid.toString());
            }

            //串行流
            {
                long t0 = System.nanoTime();
                long count = values.stream().sorted().count();
                System.out.println(count);
                long t1 = System.nanoTime();
                long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
                System.out.println(String.format("sequentialsort took: %d ms", millis));
            }

            //并行流
            {
                long t0 = System.nanoTime();
                long count = values.parallelStream().sorted().count();
                System.out.println(count);
                long t1 = System.nanoTime();
                long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
                System.out.println(String.format("parallelsort took: %d ms", millis));
            }
        }

    }
}
