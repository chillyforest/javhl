package com.javhl.course.aop;

import com.javhl.course.dynamicproxy.ProxyTestInterface;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Description AOP测试类，统计接口运行时长
 */
//@Component
@Aspect
public class MethodCallTimeAspect {

    @Pointcut("execution(* com.javhl..*.*(..))")
    private void myPointCut(){

    }

    @Around("myPointCut()")
    public Object advice (ProceedingJoinPoint joinPoint) throws Throwable{

        //方法调用开始时间
        long startTime = System.nanoTime();
        Object result = null;
        try {

            result = joinPoint.proceed();

        }finally {

            long timeUsed = System.nanoTime() - startTime;

            StringBuilder sb = new StringBuilder();
            sb.append("class name = [ ").append(joinPoint.getTarget().getClass().getName()).append(" ] ");
            sb.append("methd name = [ ").append(joinPoint.getSignature().getName()).append(" ]")
                    .append(" 耗时: ").append(timeUsed).append(" 纳秒");

            System.out.println(sb.toString());
        }
        return result;
    }

    public static void main(String[] args){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("testBeans.xml");

        String[] names = applicationContext.getBeanDefinitionNames();

        Arrays.stream(names).forEach(e->System.out.println(e));

        ProxyTestInterface proxyTest = (ProxyTestInterface) applicationContext.getBean("proxyTest");

        Integer ret = proxyTest.add(1,2);

        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

}
