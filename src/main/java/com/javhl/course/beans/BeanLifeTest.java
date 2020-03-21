package com.javhl.course.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Description Bean生命周期测试
 */
//@Component
public class BeanLifeTest implements BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean {
    private String name;

    public BeanLifeTest() {
        System.out.println("第一步：实例化类");
    }

    public void setName(String name) {
        System.out.println("第二步：设置属性");
        this.name = name;
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("第三步：设置bean的名称也就是spring容器中的名称，也就是id值" + s);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("第四步：获取工厂信息ApplicationContext");
    }

    //第五步执行初始化之前执行的方法
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("第六步：属性设置后执行的方法");
    }

    public void init() {
        System.out.println("第七步：执行自己配置的初始化方法");
    }

    //第八步执行初始化之后执行的方法
    public void start() {
        System.out.println("第九步：执行自身的业务方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("第十步：执行spring的销毁方法");
    }

    public void myDestory() {
        System.out.println("第十一步：执行自己配置的销毁方法");
    }

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("testBeans.xml");

        String[] names = applicationContext.getBeanDefinitionNames();

        Arrays.stream(names).forEach(e -> System.out.println(e));

        BeanLifeTest beanLifeTest = (BeanLifeTest) applicationContext.getBean("testBean");

        beanLifeTest.start();

        ((ClassPathXmlApplicationContext) applicationContext).close();

    }

}
