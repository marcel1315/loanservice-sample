package com.marceldev.domain.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.javaField

@Aspect
@Component
class EncryptAspect(
    private val encryptComponent: EncryptComponent
) {

    @Before("execution(* com.marceldev.domain.repository.*.save(..))")
    fun encryptFields(joinPoint: JoinPoint) {
        val arg = joinPoint.args[0]
        arg?.javaClass?.kotlin?.declaredMemberProperties?.forEach { prop ->
            prop.javaField?.isAccessible = true
            prop.javaField?.annotations?.forEach { annotation ->
                if (annotation.annotationClass == Encrypt::class) {
                    var field = prop.javaField!!
                    var value = field.get(arg)
                    if (value is String) {
                        field.set(arg, encryptComponent.encryptString(value))
                    }
                }
            }
        }
    }

    @AfterReturning(
        pointcut = "execution(* com.marceldev.domain.repository.*.find*(..))",
        returning = "result"
    )
    fun decryptFields(joinPoint: JoinPoint, result: Any?) {
        result?.javaClass?.kotlin?.declaredMemberProperties?.forEach { prop ->
            prop.javaField?.isAccessible = true // 이걸 해줘야 IllegalAccessException 이 나지 않음
            prop.javaField?.let { field ->
                if (field.isAnnotationPresent(Encrypt::class.java)) {
                    val value = field.get(result)
                    if (value is String) {
                        field.set(result, encryptComponent.decryptString(value))
                    }
                }
            }
        }
    }
}