package com.javhl.course.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

//@Configuration
public class JavaConfigBeanTest {

    @Bean
    public BeanTest beanTest(){

        BeanTest beanTest = new BeanTest();
        beanTest.setTestField("I am a beanTest");

        return beanTest;
    }

    public static void main(String[] args){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("testBeans.xml");

        String[] names = applicationContext.getBeanDefinitionNames();

        Arrays.stream(names).forEach(e->System.out.println(e));

        BeanTest beanTest = (BeanTest) applicationContext.getBean("beanTest");

        System.out.println(beanTest.getTestField());

        ((ClassPathXmlApplicationContext) applicationContext).close();
    }
}
