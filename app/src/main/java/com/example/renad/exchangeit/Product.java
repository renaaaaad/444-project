package com.example.renad.exchangeit;

public class Product {



    private String name , discription , category  ,path ;



    public Product(){

    }

    public Product( String n ,String d ,String c  ,String p){
        name = n;
        discription = d;
        category = c;
        path = p;

    }



    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDiscription() {
        return discription;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

