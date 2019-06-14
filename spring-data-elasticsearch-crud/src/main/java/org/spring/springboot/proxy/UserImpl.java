package org.spring.springboot.proxy;

import org.spring.springboot.domain.City;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/6/12 17:02
 * @Version: 1.0
 */

public class UserImpl {
   public List<City> findByDescriptionAndScore(String description, Integer score){
       System.out.println("撒大声地啊是的按时的");
        return new ArrayList<City>();
    }
}
