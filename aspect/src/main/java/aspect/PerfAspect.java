package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

public class PerfAspect {
    @Pointcut("@annotation(aspect.MyIterface)")
    public void annotationLogPerformance() {
    }

    @Pointcut("execution(* *.*(..))")
    public void atExecution() {
    }

    @Around(value = "atExecution() && annotationLogPerformance()")
    public void logPerformance(ProceedingJoinPoint joinPoint) {
        System.out.println("Logging performance !");
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println("After running method !");
        }
    }
}