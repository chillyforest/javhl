package com.javhl.course.dynamicproxy;

import com.javhl.course.aop.MethodCallTimeAspect;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description 静态代理类
 */
@ComponentScan(basePackages = { "com.javhl.*"})
public class StaticProxyTest implements ProxyTestInterface {

    public StaticProxyTest(ProxyTest proxyTest){

        this.proxyTest = proxyTest;
    }

    private ProxyTest proxyTest;

    @Override
    public Integer add(Integer a, Integer b) {
        return proxyTest.add(a,b);
    }


    public static void main(String[] args){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("testBeans.xml");

        String[] names = applicationContext.getBeanDefinitionNames();

        System.out.println(names);

        MethodCallTimeAspect aopTest = (MethodCallTimeAspect) applicationContext.getBean("AOPTest");

        ProxyTest proxyTest = new ProxyTest();
        StaticProxyTest staticProxyTest = new StaticProxyTest(proxyTest);

        Integer ret = staticProxyTest.add(1,2);

        System.out.println(ret);
    }

}
