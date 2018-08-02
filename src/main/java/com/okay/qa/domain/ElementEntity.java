package com.okay.qa.domain;

import java.io.Serializable;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2018-04-09:14:03
 * Modify date: 2018-04-09:14:03
 */
public class ElementEntity implements Serializable {

    private String name;
    private TYPE type;
    private String locator;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
