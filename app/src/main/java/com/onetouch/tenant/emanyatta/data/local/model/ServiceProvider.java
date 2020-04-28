package com.onetouch.tenant.emanyatta.data.local.model;

import java.util.ArrayList;
import java.util.List;

public class ServiceProvider {
    private int id;
    private String name;
    private String contacts;
    private String category;
    private String location;
    private String servicechargedetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {

        List<ServiceCategory> categories = new ArrayList<>();

        for (ServiceCategory serviceCategory : categories) {
            if (!serviceCategory.getCategoryName().equals(category)) {
                ServiceCategory s = new ServiceCategory();
                s.setCategoryName(category);
                categories.add(s);
            }
        }
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getServicechargedetails() {
        return servicechargedetails;
    }

    public void setServicechargedetails(String servicechargedetails) {
        this.servicechargedetails = servicechargedetails;
    }
}
