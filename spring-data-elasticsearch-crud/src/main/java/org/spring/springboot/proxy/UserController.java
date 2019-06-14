package org.spring.springboot.proxy;

import org.spring.springboot.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/6/12 17:03
 * @Version: 1.0
 */
@RestController
public class UserController {
    @Autowired
    UserFace face;
    @GetMapping(value = "/uu")
    public void  a(){
        List<City> henhao = face.findByDescriptionAndNameAndScore("henhao","深圳" ,19);
        System.out.println("aa");
    }
}
