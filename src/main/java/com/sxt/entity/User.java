package com.sxt.entity;

import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Xiang想
 * @title: User
 * @projectName solr
 * @description: TODO
 * @date 2020/7/29  12:20
 */
public class User implements Serializable {

    @Field("id")
    private String id;
    @Field("user_name")
    private String name;
    @Field("user_age")
    private String age;
    @Field("user_like")
    private String[] like;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", like=" + Arrays.toString(like) +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String[] getLike() {
        return like;
    }

    public void setLike(String[] like) {
        this.like = like;
    }
}
