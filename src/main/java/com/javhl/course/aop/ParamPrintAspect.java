package com.javhl.course.aop;

import com.javhl.course.beans.BeanTest;
import com.javhl.course.dynamicproxy.ProxyTestInterface;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 打印方法运行出入参
 */
@Component
@Aspect
public class ParamPrintAspect {

    /**
     * 注意此处com.javhl.course.beans..*与com.javhl.course.beans.*的区别，前者包含子包，后者不包含
     */
    @Pointcut(" (execution(* com.javhl.course.beans..*.*(..)) || @annotation(com.javhl.course.aop.anno.ParamPrintAnno)) && !@annotation(com.javhl.course.aop.anno.ParamNoPrintAnno) ")
    private void myPointCut(){

    }

    @Around("myPointCut()")
    public Object advice (ProceedingJoinPoint joinPoint) throws Throwable{

        Object[] inParams = joinPoint.getArgs();

        StringBuilder sb = new StringBuilder();
        sb.append("class name = [ ").append(joinPoint.getTarget().getClass().getName()).append(" ]").append("\n");
        sb.append("methd name = [ ").append(joinPoint.getSignature().getName()).append(" ]").append("\n");
        sb.append("inparams = [ ");
        if(inParams!=null && inParams.length>0){

            for(int i=0;i< inParams.length;i++){

                if(i == inParams.length-1){

                    sb.append(inParams[i]);

                }else {

                    sb.append(inParams[i]).append(",");

                }
            }

        }

        sb.append("]").append("\n");

        sb.append("outparams = [ ");

        Object result = null;

        try {

            result = joinPoint.proceed();

            if(null != result){

                sb.append(result.toString());
            }

        }finally {

            sb.append(" ]");

            System.out.println(sb.toString());
        }

        return result;
    }

    public static void main(String[] args){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("testBeans.xml");

        ProxyTestInterface proxyTest = (ProxyTestInterface) applicationContext.getBean("proxyTest");

        Integer ret = proxyTest.add(1,2);

        BeanTest beanTest = (BeanTest) applicationContext.getBean("beanTest");

        beanTest.setTestField("abc");

        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

}
