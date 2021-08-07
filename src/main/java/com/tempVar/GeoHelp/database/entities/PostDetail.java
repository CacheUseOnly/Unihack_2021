package com.tempVar.GeoHelp.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class PostDetail implements Serializable {
    @Id
    @Column(name = "post_id")
    private Long post_id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "contact")
    private String contact;
    @Column(name = "contact_type")
    private Integer contact_type;

    public void setPost_id(Long l) {
        post_id = l;
    }

    public void setTitle(String str) {
        title = str;
    }

    public void setContent(String str) {
        content = str;
    }

    public void setContact(String str) {
        contact = str;
    }

    public void setContact_type(Integer type) {
        contact_type = type;
    }

    public Long getPost_id() {
        return post_id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getContact() {
        return contact;
    }

    public Integer getContact_type() {
        return contact_type;
    }
}
