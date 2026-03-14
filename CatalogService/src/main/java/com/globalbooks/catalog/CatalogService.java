package com.globalbooks.catalog;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class CatalogService {

    @WebMethod
    public Book getBook(String isbn){

        if(isbn.equals("1111")){
            return new Book("1111","Microservices Architecture",45.0);
        }

        if(isbn.equals("2222")){
            return new Book("2222","SOA Design",50.0);
        }

        return new Book(isbn,"Unknown",0);
    }
}