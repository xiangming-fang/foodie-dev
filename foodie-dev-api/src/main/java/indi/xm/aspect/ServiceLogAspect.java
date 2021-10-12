package indi.xm.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.aspect
 * @ClassName: ServiceLogAspect
 * @Author: albert.fang
 * @Description: 日志监控service层运行
 * @Date: 2021/10/12 16:57
 */
@Aspect // 表明这是个切面
@Component // 表明这是个组件
public class ServiceLogAspect {

    public static final Logger log = LoggerFactory.getLogger(ServiceLogAspect.class);

    /**
     * AOP 通知
     * 1、前置通知：在方法调用之前执行。
     * 2、后置通知：在方法正常调用之后执行。// 报异常不走
     * 3、环绕通知：在方法调用之前和之后，都分别可以执行的通知。
     * 4、异常通知：如果在方法调用过程中发生异常，则通知。
     * 5、最终通知：在方法调用之后执行 // 类似 try-catch里的finally
     *
     */

    /**
     * 切面表达式
     * execution 代表所要执行的表达式主体
     * 第一处 * 代表方法返回类型 * 代表所有类型
     * 第二处 包名代表aop监控的类所在的包
     * 第三处 .. 代表该包以及其子包下所有类方法
     * 第四处 * 代表类名， * 代表所有类
     * 第五处 *(..) * 代表类中的方法名，(..) 表示方法中的任何参数
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* indi.xm.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // {某一个类里的}.{某一个方法}
        log.info("====== 开始执行 {}.{} ======"
                ,joinPoint.getTarget().getClass()
                ,joinPoint.getSignature().getName());

        // 记录开始时间
        long begin = System.currentTimeMillis();

        // 执行目标 service
        Object result = joinPoint.proceed();

        // 记录结束时间
        long end = System.currentTimeMillis();

        // 时间差
        long takeTime = end - begin;

        if (takeTime > 3000){
            log.error("====== 执行结束，耗时：{} 毫秒 ======",takeTime);
        }else if (takeTime > 2000){
            log.warn("====== 执行结束，耗时：{} 毫秒 ======",takeTime);
        }else {
            log.info("====== 执行结束，耗时：{} 毫秒 ======",takeTime);
        }

        return result;
    }
}
