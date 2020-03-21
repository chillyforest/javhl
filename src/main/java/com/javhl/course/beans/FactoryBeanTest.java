package com.javhl.course.beans;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

//@Component
public class FactoryBeanTest implements FactoryBean {

    private String type = "a";

    @Override
    public Object getObject() throws Exception {
        if("a".equals(type))
            return new A();
        else
            return new B();
    }

    @Override
    public Class<?> getObjectType() {
        if("a".equals(type))
            return A.class;
        else
            return B.class;
    }

    @Override
    public boolean isSingleton() {

        return true;
    }

    public static void main(String[] args){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("testBeans.xml");

        String[] names = applicationContext.getBeanDefinitionNames();

        Arrays.stream(names).forEach(e->System.out.println(e));

        //此处获取的是类A的实例
        Object realBean = applicationContext.getBean("factoryBeanTest");

        System.out.println(realBean.getClass().getName());

        //此处获取的是FactoryBeanTest的实例
        FactoryBeanTest factoryBeanTest = (FactoryBeanTest) applicationContext.getBean("&factoryBeanTest");

        ((ClassPathXmlApplicationContext) applicationContext).close();
    }
}
