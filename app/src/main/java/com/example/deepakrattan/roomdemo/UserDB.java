package com.example.deepakrattan.roomdemo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

//Create database UserDB
@Database(entities = {User.class}, version = 1)
public abstract class UserDB extends RoomDatabase {
    abstract UserDao getUserDao();

}
