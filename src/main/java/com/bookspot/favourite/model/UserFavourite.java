package com.bookspot.favourite.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection="userFavourite")
public class UserFavourite {

    @Id
    private String userId;
    private List<Book> bookList;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void addBook(Book book) {
        if(this.bookList ==null) {
            this.bookList = new ArrayList<Book>();
        }
        bookList.add(book);
    }

}
