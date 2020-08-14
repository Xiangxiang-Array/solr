package com.sxt.solr;

import com.sxt.entity.User;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Xiang想
 * @title: Test2
 * @projectName solr
 * @description: TODO
 * @date 2020/7/29  12:22
 */
public class Test2 {
    private static String URL = "http://47.107.189.111:12080/solr/";
    private static SolrServer solrServer = new HttpSolrServer(URL);

    public static void main(String[] args) throws IOException, SolrServerException {
        queryCase();

    }

    public static void add() throws IOException, SolrServerException {
        User u = new User();
        String prefix = "user_";

        String id = prefix+ UUID.randomUUID().toString().substring(4).substring(prefix.length());
        System.out.println(id);
        u.setId(id);
        u.setName("李四");
        u.setAge("40");
        u.setLike(new String[]{"玩游戏"});
        solrServer.addBean(u);
        solrServer.commit();
        id = prefix+ UUID.randomUUID().toString().substring(4).substring(prefix.length());
        u.setId(id);
        u.setName("王明");
        u.setAge("30");
        u.setLike(new String[]{"足球","游戏"});
        solrServer.addBean(u);
        solrServer.commit();
    }

    private static void addAll() throws IOException, SolrServerException {
        for (int i = 100; i <= 140; i++) {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id",i);
            String name = null;
            if (i%2==0){
                name = "手机"+i;
            } else{
                name = "电脑" +i;
            }
            doc.setField("name",name);
            int random =(int) (Math.random()*10000);
            doc.setField("price",random);
            String path = "/img/0"+i+".jpg";
            doc.setField("url",path);
            solrServer.add(doc);
            solrServer.commit();
            System.out.println("添加完成"+i+"\t"+name+"\t"+random+"\t"+path);
        }
    }



    public static void query() throws SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q","user_name:明");
        QueryResponse response = solrServer.query(solrQuery);
        SolrDocumentList solrList = response.getResults();
        long name = solrList.getNumFound();
        System.out.println("条数: \t"+name);
        for (SolrDocument entries : solrList) {
            User u =  solrServer.getBinder().getBean(User.class,entries);
            System.out.println(u.toString());
        }
    }

    public static void queryCase() throws SolrServerException {
        SolrQuery solrQuery = new SolrQuery();

//        solrQuery.set("q","name:手 ");
//        solrQuery.set("q","name:手 AND id:122 ");

        solrQuery.set("q"," price:[5000 TO 9000] ");
        // []  代表  <=   >=    {} 代表 <   >
        solrQuery.addFilterQuery("手机");
        solrQuery.set("start",0);
        solrQuery.set("rows",10);
        solrQuery.set("sort","price desc");
        QueryResponse res = solrServer.query(solrQuery);
        SolrDocumentList results = res.getResults();
        for (SolrDocument result : results) {
            System.out.println("id ：\t"+result.get("id")+"\tname ：\t"+result.get("name")+"\t price ：\t"+result.get("price")+"\turl ：\t\t"+result.get("url"));
        }
    }
}
