package aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

// 여러 객체에 공통으로 적용되는 기능 - Aspect
// @Aspect 애노테이션을 적용한 클래스는 Advice와 Pointcut을 함께 제공
@Aspect
public class ExeTimeAspect {

	// @Pointcut은 공통 기능을 적용할 대상을 설정
	// 패키지와 하위 패키지에 위치한 타입의 public 메서드를 Pointcut으로 설정
	@Pointcut("execution(public * chap07 ..*(..))")
	private void publicTarget() {

	}

	// @Around 애노테이션은 Around Advice를 설정
	// publicTarget() 메서드에 정의한 Pointcut에 공통 기능을 적용으로 패키지나 하위패키지에 속한
	// 빈 객체의 public 메서드에 @Around가 붙은 measure() 메서드를 적용
	// measure() 메서드의 ProceedingJoinPoint 타입 파라미터는 프록시 대상 객체의 메서드를 호출할 때
	// 사용하며 preceed() 메서드를 사용해서 실제 대상 객체의 메서드를 호출한다.
	@Around("publicTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.nanoTime();

		try {
			Object result = joinPoint.proceed();
			return result;
		} finally {
			long finish = System.nanoTime();
			Signature sig = joinPoint.getSignature();
			System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n", joinPoint.getTarget().getClass().getSimpleName(),
					sig.getName(), Arrays.toString(joinPoint.getArgs()), (finish - start));
		}

	}

}
