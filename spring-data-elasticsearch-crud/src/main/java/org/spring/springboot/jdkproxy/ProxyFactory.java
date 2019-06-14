package org.spring.springboot.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/6/14 11:08
 * @Version: 1.0
 */

public class ProxyFactory {


    private Object obj;


    public ProxyFactory(Object obj) {
        super();
        this.obj = obj;
    }

    public Object getTransactionProxyInstance(){

        Object proxy = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * 三个参数：1、代理对象，2、目标对象的方法，3、目标对象的参数值列表
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("开启事务..."); //执行核心业务之前执行的内容
                if(method.getName().equals("save")){
                    System.out.println("通过redis保存成功");
                }else{
                    method.invoke(obj, args);      //执行目标对象方法，即核心业务
                    System.out.println("关闭事务..."); //执行核心业务之后执行的内容
                }

                return proxy;
            }
        });

        return proxy;
    }
    public Object getLogProxyInstace(){

        Object proxy = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * 三个参数：1、代理对象，2、目标对象的方法，3、目标对象的参数值列表
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("开启日志..."); //执行核心业务之前执行的内容
                method.invoke(obj, args);      //执行目标对象方法，即核心业务
                System.out.println("关闭日志..."); //执行核心业务之后执行的内容
                return proxy;
            }
        });

        return proxy;
    }
}

