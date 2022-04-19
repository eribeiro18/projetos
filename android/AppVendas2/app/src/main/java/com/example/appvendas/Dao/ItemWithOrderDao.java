package com.example.appvendas.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.appvendas.Entity.ItemWithOrder;

import java.util.List;

@Dao
public interface ItemWithOrderDao {

    @Transaction
    @Query("SELECT * FROM item_table")
    LiveData<List<ItemWithOrder>> getItemSummary();
}
