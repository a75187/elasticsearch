package org.spring.springboot.cglibproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/6/14 11:31
 * @Version: 1.0
 */

public class CglibProxyFactory {


    private Object obj;

    public CglibProxyFactory(Object obj) {
        super();
        this.obj = obj;
    }

    public Object getProxyFactory(){
        //Enhancer类是cglib中的一个字节码增强器，它可以方便的为你所要处理的类进行扩展
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());//将目标对象所在的类作为Enhaner类的父类
        enhancer.setCallback(new MethodInterceptor() {
            //通过实现MethodInterceptor实现方法回调
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("事务开启...");
                if(method.getName().equals("query")){
                    System.out.println("通过redis查询");
                }else{
                    method.invoke(obj, args);
                }
                System.out.println("事务结束...");

                return proxy;
            }
        });

        return enhancer.create();//生成目标对象并返回
    }
}
