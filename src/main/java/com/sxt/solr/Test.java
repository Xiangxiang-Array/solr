package com.sxt.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

/**
 * @author Xiang想
 * @title: Test
 * @projectName solr
 * @description: TODO
 * @date 2020/7/28  19:26
 */
public class Test {
    private static String URL = "http://47.107.189.111:12080/solr/";
    private static SolrServer solrServer = new HttpSolrServer(URL);

    public static void main(String[] args) throws IOException, SolrServerException {
        del();

    }

    public static void add() throws IOException, SolrServerException {
        SolrInputDocument doc1 = new SolrInputDocument();
        doc1.setField("id","1001");
        doc1.setField("name","iphone6s");
        doc1.setField("price","3000");
        doc1.setField("url","/img/01.jpg");
        solrServer.add(doc1);
        solrServer.commit();
    }

    public static void query() throws SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q","title:张");
        QueryResponse response = solrServer.query(solrQuery);
        SolrDocumentList list = response.getResults();
        long num = list.getNumFound();
        System.out.println("======\t条数："+num+"\t=====");
        System.out.println(list);
    }

    public static void del() throws IOException, SolrServerException {
        solrServer.deleteByQuery("id:user_95a0-45ca-a714-18ed535f3999");
        solrServer.commit();
    }
}
