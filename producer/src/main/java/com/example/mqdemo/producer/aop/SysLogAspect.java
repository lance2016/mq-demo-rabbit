package com.example.mqdemo.producer.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @program: mqdemo  SysLogAspect
 * @description:
 * @author: flchen
 * @create: 2021-12-02 11:09
 **/

@Aspect
@Slf4j
@Component
public class SysLogAspect {
    /**
     * @Pointcut	描述从那个方法进行切入
     * bean(bean对象名字):为一种切入点表达式(这个表达式中定义了哪个或哪些bean对象的方法要进行功能扩展).
     * 	例如,bean(sysUserServiceImpl)表达式表示名字为sysUserServiceImpl的bean对象中所有方法的集合为切入点,
     *    也就是说这个sysUserServiceImpl对象中的任意方法执行时都要进行功能扩展.
     */
    @Pointcut("execution(* com.example.mqdemo.producer.controller..*(..))")
    public void logPointCut() {}

    /**
     * @Around	用于描述切面中方法，执行的时候会运行指定的切面方法
     * @param jp
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object arround(ProceedingJoinPoint jp) throws Throwable {
        try {
            long t1=System.currentTimeMillis();
            log.info("=======================>start:{}",t1);
            //2.执行目标方法
            Object result=jp.proceed();
            //最终(中间还可以调用本类其它通知或其它切面的通知)会调用目标方法
            //3.记录方法结束执行时间
            long t2=System.currentTimeMillis();
            log.info("=======================>after:{}",t2);
            log.info("=======================>目标方法执行耗时:{}",(t2-t1));
            //4.将正常行为日志信息写入到数据库
            //5.返回目标方法执行结果
            return result;
            //目标方法的返回结果
        }catch (Throwable e){
            log.error("after:{}" , e.getMessage());
            throw e;
        }
    }
}
