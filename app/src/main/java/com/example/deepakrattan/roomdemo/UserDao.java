package com.example.deepakrattan.roomdemo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

//Specify CRUD operations on Database here
@Dao
public interface UserDao {

    @Insert
    long insert(User user);

    @Query("Select * from User")
    List<User> getusers();

    @Update
    int update(User user);

    @Delete
    int delete(User user);

}
