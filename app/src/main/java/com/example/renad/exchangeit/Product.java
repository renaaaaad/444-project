package com.example.renad.exchangeit;

public class Product {



    private String name , discription , category   ;



    public Product(){

    }

    public Product( String n ,String d ,String c  ){
        name = n;
        discription = d;
        category = c;

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
}

