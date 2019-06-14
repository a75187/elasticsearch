package org.spring.springboot.jdkproxy;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/6/14 11:07
 * @Version: 1.0
 */

import java.util.Date;

/**
 *
 * @Description
 * @author CCQ
 * @date 2017年6月22日 下午1:16:41
 *
 */
public class UserDaoMysqlImpl implements UserDao {

    public void query(){
        System.out.println("Mysql执行查询...");
    }

    @Override
    public void save() {
        System.out.println("Mysql执行保存...");
    }

    @Override
    public void delete(int id) {
        System.out.println("Mysql执行删除..."+id);
    }

    @Override
    public void update(int id, String name, String pwd, Date date) {
        System.out.println("Mysql执行修改...id:"+id+"name:"+name+"pwd:"+pwd+"date:"+date);
    }

}