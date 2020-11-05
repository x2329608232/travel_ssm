package com.kfx.controller;

import com.kfx.pojo.SysLog;
import com.kfx.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    private Date visitTime;//开始时间
    private Class aClass;//访问类
    private Method method;//访问的方法

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    @Before("execution(* com.kfx.controller.*.*(..))")
    public  void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        visitTime = new Date();
        aClass= joinPoint.getTarget().getClass();   //具体访问的类
        //获取执行的方法名称
        String methodName =    joinPoint.getSignature().getName(); //获取访问的方法名称
        //获取访问的犯法参数
        Object[] args = joinPoint.getArgs();
        if (args==null || args.length==0){
        method = aClass.getMethod(methodName);//只能获取到无参数的犯法
        }else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i <args.length ; i++) {
                classArgs[i] = args[i].getClass();
            }
            //封装参数
            aClass.getMethod(methodName,classArgs);
        }
    }

    @After("execution(* com.kfx.controller.*.*(..))")
    public void doAfter(){
       long time =  new Date().getTime()-visitTime.getTime();
       //通过java反射 获取操作的url
        String url ="";
        if (aClass !=null && method !=null && aClass!=LogAop.class){
            //获取类上的RequestMapping  注解  获取注解里面的内容
            RequestMapping classAnnotation = (RequestMapping) aClass.getAnnotation(RequestMapping.class);
            if (classAnnotation!=null){
                //获取到类上的requestMapping的value值
                String[] classValue = classAnnotation.value();
                if (!"/sysLog".equals(classValue[0])){
                    //获取到方法上的requestMapping的value值
                    RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                    //取出value值
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0]+methodValue[0];
                    // 获取ip
                    String ip = request.getRemoteAddr();
                    SecurityContext context = SecurityContextHolder.getContext();
                    //获取到当前的操作用户对象
                    User principal= (User) context.getAuthentication().getPrincipal();
                    //获取用户名
                    String username = principal.getUsername();
                    SysLog sysLog = new SysLog();
                    sysLog.setIp(ip);
                    sysLog.setExecutionTime(time);
                    sysLog.setMethod("[类名]"+ aClass.getName()+"[方法名]"+method.getName());//访问的方法
                    sysLog.setUsername(username);
                    sysLog.setUrl(url);
                    sysLog.setVisitTime(visitTime);
                    sysLogService.save(sysLog);
                }
                return;
            }
        }
    }
}
