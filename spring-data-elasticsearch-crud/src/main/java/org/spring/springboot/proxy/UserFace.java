
package org.spring.springboot.proxy;

import org.spring.springboot.domain.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/6/12 16:58
 * @Version: 1.0
 */
public interface UserFace extends ElasticsearchRepository<City, Long> {
    List<City> findByDescriptionAndScore(String description, Integer score);

    List<City> findByDescriptionAndNameAndScore(String description,String name, Integer score);
}
