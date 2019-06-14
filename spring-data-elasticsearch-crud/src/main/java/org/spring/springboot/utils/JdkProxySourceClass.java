package org.spring.springboot.utils;

import org.junit.Test;
import org.spring.springboot.jdkproxy.UserDaoMysqlImpl;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/6/14 15:07
 * @Version: 1.0
 */

public class JdkProxySourceClass {
    public static void writeClassToDisk(String path){

        byte[] classFile = ProxyGenerator.generateProxyClass("$proxy4", new Class[]{UserDaoMysqlImpl.class});
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            fos.write(classFile);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testGenerateProxyClass() {
        JdkProxySourceClass.writeClassToDisk("D:/$Proxy4.class");
    }

    }
