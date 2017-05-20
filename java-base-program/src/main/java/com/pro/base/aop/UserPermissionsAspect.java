package com.pro.base.aop;

import com.pro.base.oauth.UserPermissions;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by zhanghaichao on 2017/5/14.
 * 自定义注解 判断用户权限或者其他用途
 */
//@Aspect 原来使用注解来添加切面后改成xml配置
//@Component
public class UserPermissionsAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    //要用&&链接否则没有注解的地方也将被切入
//    @Pointcut("execution(public * com.base.core.web.*Controller.*(..))&&@annotation(com.base.core.oauth.UserPermissions)")
//    public void permission() {
//    }

    /**
     * 获取用户权限代码进行比对
     *
     * @param joinPoint
     * @throws Throwable
     */
//    @Before("permission()")
    //这里的方法参数也可以用ProceedingJoinPoint 这个的继承是JoinPoint
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        String permissions = "";
        if (method.isAnnotationPresent(UserPermissions.class)) {
            UserPermissions userPermissions = method.getAnnotation(UserPermissions.class);
            permissions = userPermissions.value();
        }

        boolean hasRole = false;
        boolean blackList = false;
        boolean rooter = false;
        try {
            String[] userRole = (String[]) request.getSession().getAttribute("userRole");
            for (String role : userRole) {
                if (role.equals(permissions)) {
                    hasRole = true;
                }
                //最高权限判断  后面放在配置文件里
                if (role.equals("root")) {
                    rooter = true;
                }
                //黑名单判断 后面放在配置文件里
                if (role.equals("blacklist")) {
                    blackList = true;
                }
            }
            //如果黑名单
            if (blackList) {
                this.unauthorized(attributes);
            } else if (rooter || hasRole) {
                logger.info("UserPermission : " + permissions);
            } else {
                this.unauthorized(attributes);
            }
        } catch (Exception e) {
            this.unauthorized(attributes);
        }
    }

    /**
     * 未授权，则跳转到错误页
     *
     * @param sra ServletRequestAttributes
     * @throws Throwable 跳转异常
     */
    private void unauthorized(ServletRequestAttributes sra) throws Throwable {
        HttpServletResponse response = sra.getResponse();
        logger.info("失败");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "没有登录或登录已超时！");

    }
}
