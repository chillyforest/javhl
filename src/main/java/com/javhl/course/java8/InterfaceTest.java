package com.javhl.course.java8;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class InterfaceTest {

    /**
     * 默认接口测试
     */
    public static void defaultInterfaceTest() {

        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        System.out.println(formula.calculate(100)); // 100.0
        System.out.println(formula.sqrt(16)); // 4.0
    }

    public static void lamdaTest() {

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        //传统方法
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        //lamda方式，升序排列
        Collections.sort(names, (String a, String b) -> {
            return a.compareTo(b);
        });

        names.forEach(s -> {
            System.out.println(s + " ");
        });

    }

    public static void lamdaScopeTest() {

        int num = 1;
        Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
        System.out.println(stringConverter.convert(2)); // 3

        //在lamda表达式中用到的，局部变量为隐式final类型不允许后续修改；但是成员变量和静态变量可以修改
        //num++;

        //lamda表达式中无法访问到接口中的default方法
//        Formula formula = (a) -> sqrt( a * 100);
    }

    /**
     * 方法和构造函数引用测试
     */
    public static void methodAndConstructRefTest() {

        { //常规应用
            Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
            Integer converted = converter.convert("123");
            System.out.println(converted); // 123
        }

        { //方法和构造函数引用
            Converter<String, Integer> converter = Integer::valueOf;
            Integer converted = converter.convert("123");
            System.out.println(converted); // 123
        }

        { //自定义类,Person::new代表Person类的构造函数，此处会自动做类型匹配
            PersonFactory<Person> personFactory = Person::new;
            Person person = personFactory.create("Peter", "Parker");
            System.out.println(person.firstName + " " + person.lastName);
        }

    }

    public static void newInterfaceTest() {

        { //predicate接口测试,
            Predicate<String> predicate = (s) -> s.length() > 0;
            predicate.test("foo"); // true
            predicate.negate().test("foo"); // false
            Predicate<Boolean> nonNull = Objects::nonNull;
            Predicate<Boolean> isNull = Objects::isNull;
            Predicate<String> isEmpty = String::isEmpty;
            Predicate<String> isNotEmpty = isEmpty.negate();
        }

        {//function接口

            Function<String, Integer> toInteger = Integer::valueOf;

            toInteger.apply("123");//123

            Function<String, String> backToString = toInteger.andThen(String::valueOf);
            backToString.apply("123"); // "123"
        }

        {//suply测试

            Supplier<Person> personSupplier = Person::new;
            personSupplier.get(); // new Person
        }

        { //consumer测试

            Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
            greeter.accept(new Person("Luke", "Skywalker"));
        }

        { //Comparator测试

            Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
            Person p1 = new Person("John", "Doe");
            Person p2 = new Person("Alice", "Wonderland");
            comparator.compare(p1, p2); // > 0
            comparator.reversed().compare(p1, p2); // < 0
        }

        { //Optional

            Optional<String> optional = Optional.of("bam");
            optional.isPresent(); // true
            optional.get(); // "bam"
            optional.orElse("fallback"); // "bam"
            optional.ifPresent((s) -> System.out.println(s.charAt(0))); // "b"
        }
    }

    interface Formula {

        double calculate(int a);

        //默认实现
        default double sqrt(int a) {
            return Math.sqrt(a);
        }
    }

    /**
     * 函数式接口,只能有一个接口，否则编译报错
     *
     * @param <F>
     * @param <T>
     */
    @FunctionalInterface
    interface Converter<F, T> {

        T convert(F from);

//        T convent2(F from);
    }

    static class Person {
        String firstName;
        String lastName;

        Person() {
        }

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }


    public static void main(String[] args) {

//        defaultInterfaceTest();

//        lamdaTest();

//        methodAndConstructRefTest();

//        lamdaScopeTest();

        newInterfaceTest();
    }
}
