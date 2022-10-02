package com.bookspot.favourite.repository;

import com.bookspot.favourite.model.UserFavourite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepo extends MongoRepository<UserFavourite,String> {
}
