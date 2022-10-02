package com.bookspot.favourite.service;

import com.bookspot.favourite.excpetion.UserNotFoundException;
import com.bookspot.favourite.model.Book;
import com.bookspot.favourite.model.UserFavourite;
import com.bookspot.favourite.repository.FavouriteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavouriteService {

    @Autowired
    private FavouriteRepo repository;

    private List<Book> books = new ArrayList<>();
    private UserFavourite user = new UserFavourite();

    public List<Book>getAllBooksByUserId(String userId)throws UserNotFoundException{
        if (repository.existsById(userId)) {
            books = repository.findById(userId).get().getBookList();
        }
        else {
            throw new UserNotFoundException("User Not Found!");
        }
        return books;
    }

    public boolean addBook(Book book, String userId) throws UserNotFoundException {
        try {
            if (repository.existsById(userId)) {
                UserFavourite user =  repository.findById(userId).get();
                //books = user.getBookList();
                user.addBook(book);
                repository.save(user);
            }else{
                user.addBook(book);
                user.setUserId(userId);
                repository.save(user);
            }
            return true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean removeBook(String bookId,String userId) throws UserNotFoundException {
        if (repository.existsById(userId)) {
            try {
                books = repository.findById(userId).get().getBookList();
                books.removeIf(book -> book.getId().equals(bookId));
                user.setUserId(userId);
                user.setBookList(books);
                repository.save(user);
                return true;
            }catch (Exception e){
                System.out.println("Error : "+e.getMessage());
            }
            return false;
        }
        else {
            throw new UserNotFoundException("User Doesn't exist");
        }
    }

    public void deleteAllBooks(String UserId)throws UserNotFoundException{
        try {
            books = repository.findById(UserId).get().getBookList();
            books.clear();
            user.setUserId(UserId);
            user.setBookList(books);
            repository.save(user);
        }
        catch(Exception e) {
            System.out.println("error : "+e.getMessage());
        }

    }

}
