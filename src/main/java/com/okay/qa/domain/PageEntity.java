package com.okay.qa.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2018-04-09:14:19
 * Modify date: 2018-04-09:14:19
 */
public class PageEntity implements Serializable{

    private String author;
    private String date;
    private String page;
    private List<ElementEntity> element;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<ElementEntity> getElement() {
        return element;
    }

    public void setElement(List<ElementEntity> element) {
        this.element = element;
    }
}
