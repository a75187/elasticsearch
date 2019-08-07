package org.com.es.user.service;

import org.com.es.user.model.User;
import org.com.es.user.utils.EsClientUtil;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

import static java.util.Collections.singletonMap;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/8/6 16:10
 * @Version: 1.0
 */
@Service
public class UserService {
    private RestHighLevelClient esDao = EsClientUtil.getCilent();

    public String addDocument(User user) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("userName", user.getUserName());
            builder.timeField("password", new Date());
            builder.field("desc", user.getDesc());
            builder.field("id", UUID.randomUUID().toString());
        }
        builder.endObject();
        IndexRequest indexRequest = new IndexRequest("posts")
                .id(UUID.randomUUID().toString()).source(builder);
        System.out.println(indexRequest.getPipeline());
        IndexResponse indexResponse = esDao.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse);
        return Boolean.TRUE.toString();
    }

    public String del(User user) throws IOException {
        DeleteRequest posts = new DeleteRequest("posts").id(user.getId());
        esDao.delete(posts,RequestOptions.DEFAULT);
        return Boolean.TRUE.toString();
    }

    public String update(User user) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("userName", user.getUserName());
            builder.field("desc", "天选之子");
        }
        builder.endObject();
        UpdateRequest request = new UpdateRequest("posts",user.getId());
        request.doc(builder);
        esDao.update(request,RequestOptions.DEFAULT);
        return Boolean.TRUE.toString();
    }

    public List<User> search(User user) throws IOException {
        SearchRequest searchRequest = new SearchRequest("posts");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

       if(!StringUtils.isEmpty(user.getUserName())){
            searchSourceBuilder.query(QueryBuilders.termQuery("userName.keyword", user.getUserName()));
        }
   if(!StringUtils.isEmpty(user.getDesc())){
       //模糊搜索
            searchSourceBuilder.query(QueryBuilders.wildcardQuery("desc.keyword", "*"+user.getDesc()+"*"));
        }
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(4);
         searchSourceBuilder.sort(new FieldSortBuilder("password").order(SortOrder.ASC));

        searchRequest.source(searchSourceBuilder);
        SearchResponse search = esDao.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        ArrayList<User> list = new ArrayList<>();
        for (SearchHit hit : hits) {
            Map<String, Object> dtoMap = hit.getSourceAsMap();
            User us = new User();
            us.setUserName((String) dtoMap.get("userName"));
            us.setDesc((String) dtoMap.get("desc"));
            us.setPassword((String) dtoMap.get("password"));
            list.add(us);
        }
        return  list;
    }
}
