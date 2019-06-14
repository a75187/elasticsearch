package org.spring.springboot.jdkproxy;

import org.junit.Test;
import org.spring.springboot.cglibproxy.CglibProxyFactory;
import org.spring.springboot.cglibproxy.CustomerImpl;
import org.springframework.cglib.core.DebuggingClassWriter;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/6/14 11:10
 * @Version: 1.0
 */

public class MyTest {
    @Test
    public void  jdkproxy() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/home/ljh/cglibxx");
        UserDaoMysqlImpl userDaoMysql = new UserDaoMysqlImpl();
        ProxyFactory proxyFactory = new ProxyFactory(userDaoMysql);
        UserDao transactionProxyInstance = (UserDao) proxyFactory.getTransactionProxyInstance();
        transactionProxyInstance.save();
        transactionProxyInstance.delete(1);
    }
    @Test
    public void  cgliproxy() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/home/ljh/cglib");
        CustomerImpl userDaoMysql = new CustomerImpl();
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(userDaoMysql);
        CustomerImpl proxyObj = (CustomerImpl) cglibProxyFactory.getProxyFactory();
        proxyObj.query();

    }



}
