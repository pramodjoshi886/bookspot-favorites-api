package com.bookspot.favourite.controller;

import com.bookspot.favourite.excpetion.UserNotFoundException;
import com.bookspot.favourite.model.Book;
import com.bookspot.favourite.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@EnableSwagger2
@RequestMapping("/v1/favourite")
public class FavouriteController {

    @Autowired
    private FavouriteService service;

    @GetMapping()
    public ResponseEntity<?> getAllBooksByUserId(@RequestHeader String userId) {
        System.out.println(userId);
        try {
            List<Book> userBooks = service.getAllBooksByUserId(userId);

            if (userBooks != null) {
                return new ResponseEntity<List<Book>>(userBooks, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("There are no books in favourites", HttpStatus.OK);
            }
        }
        catch(UserNotFoundException e) {
            System.out.println("************Get all books error**********"+e.getLocalizedMessage());
            return new ResponseEntity<String>("User Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<?> addBook(@RequestHeader String userId,@RequestBody Book book){
        System.out.println(userId);
        System.out.println(book.toString());
        if(userId == "null" || userId.isEmpty()){
            return new ResponseEntity<>("userId is null/empty",HttpStatus.BAD_REQUEST);
        }
        try {
            if (service.addBook(book,userId) ){
                return new ResponseEntity<Book>(book, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<String>("Book already exists", HttpStatus.CONFLICT);
            }
        }catch(UserNotFoundException e) {
            System.out.println("************Post books error**********"+e.getLocalizedMessage());
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping()
    public ResponseEntity<String> deleteBook(@RequestHeader String userId,@RequestParam String bookId) {
        System.out.println("userId : "+userId);
        System.out.println("bookId : "+bookId);
        if(userId == "null" || userId.isEmpty()){
            return new ResponseEntity<>("userId is null/empty",HttpStatus.BAD_REQUEST);
        }
        try {
            if (service.removeBook(bookId,userId)) {
                return new ResponseEntity("Delete successful for book id : "+bookId,HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Book Not Found", HttpStatus.NOT_FOUND);
            }
        }catch(UserNotFoundException e) {
            System.out.println("************Delete book by ID error**********"+e.getLocalizedMessage());
            return new ResponseEntity<String>("User Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllBooks(@RequestHeader String userId) {
        System.out.println(userId);
        try {
            service.deleteAllBooks(userId);
            return new ResponseEntity<String>("Successfully deleted all books", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            System.out.println("************Delete all books error**********"+e.getLocalizedMessage());
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
    }

}
