package com.itheima.test;

import com.itheima.pojo.TbItem;
import org.apache.solr.client.solrj.SolrServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-solr.xml")
public class TestTemplate {

    @Autowired
    private SolrTemplate solrTemplate;

    //查询任何所有记录
    @Test
    public void testFindAll() {

      /*  System.out.println("searchService..");

        Map map = new HashMap();
*/
        Query query = new SimpleQuery("*:*");

        Criteria criteria = new Criteria("item_keywords").is("三星");

        query.addCriteria(criteria);

        ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);

        // map.put("rows",page.getContent());

        List<TbItem> content = page.getContent();
        for (TbItem item : content) {

            System.out.println(item);
        }
    }

    //增加
    @Test
    public void testAdd() {

        TbItem item = new TbItem();

        item.setId(1L);
        item.setBrand("华为"); // item_brand
        item.setCategory("手机"); // item_category
        item.setGoodsId(1L);
        item.setSeller("华为2号专卖店");
        item.setTitle("华为Mate9");
        item.setPrice(new BigDecimal(2000));

        solrTemplate.saveBean(item);
        solrTemplate.commit();//提交事务
    }

    //根据主键查询数据
    @Test
    public void findById() {

        TbItem item = solrTemplate.getById(1L, TbItem.class);

        System.out.println(item);
    }

    //删除数据
    @Test
    public void deleteById() {

        solrTemplate.deleteById("1");

        solrTemplate.commit();
    }

    //添加多条数据
    @Test
    public void testAddList() {

        List<TbItem> list = new ArrayList();

        for (int i = 0; i < 100; i++) {

            TbItem item = new TbItem();
            item.setId(i + 1L);
            item.setBrand("华为");
            item.setCategory("手机");
            item.setGoodsId(1L);
            item.setSeller("华为2号专卖店");
            item.setTitle("华为Mate" + i);
            item.setPrice(new BigDecimal(2000 + i));
            list.add(item);
        }

        solrTemplate.saveBeans(list);
        solrTemplate.commit();
    }

    //分页
    @Test
    public void testPageQuery() {
        Query query = new SimpleQuery("*:*");//条件为所有


        query.setOffset(20);//开始索引
        query.setRows(50);//每页记录数

        ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);

        for (TbItem item : page.getContent()) {

            System.out.println(item.getTitle() + ":" + item.getPrice() + ":" + item.getBrand());
        }

        System.out.println("总记录数" + page.getTotalElements());
        System.out.println("总页数" + page.getTotalPages());
    }

    //包含条件的分页查询
    @Test
    public void testPageQueryMutil() {

        Query query = new SimpleQuery("*:*");//条件为所有
        Criteria criteria = new Criteria("item_category").contains("手机");
        criteria = criteria.and("item_brand").contains("2");

        query.addCriteria(criteria);

      /*  query.setOffset(20);//开始索引
        query.setRows(50);//每页记录数*/

        ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);

        for (TbItem item : page.getContent()) {

            System.out.println(item.getTitle() + ":" + item.getPrice() + ":" + item.getBrand());
        }

        System.out.println("总记录数" + page.getTotalElements());
        System.out.println("总页数" + page.getTotalPages());
    }

    //删除所有记录
    @Test
    public void testDeleteAll() {
        Query query = new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
    }
}
