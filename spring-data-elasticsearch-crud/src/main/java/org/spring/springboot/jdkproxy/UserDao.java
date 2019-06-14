package org.spring.springboot.jdkproxy;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/6/14 11:06
 * @Version: 1.0
 */
import java.util.Date;

/**
 *
 * @Description
 * @author CCQ
 * @date 2017年6月22日 上午9:28:42
 *
 */
public interface UserDao {
    void save();
    void delete(int id);
    void update(int id,String name,String pwd,Date date);
}