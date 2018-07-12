package com.sajorahasan.skeleton.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.sajorahasan.skeleton.model.Question;

import io.reactivex.Single;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface AppDao {

    @Query("SELECT * FROM Question")
    Single<Question> getAllData();

//    @Query("SELECT * FROM Table WHERE id=:id")
//    Table getTableById(int id);
//
//    @Query("SELECT * FROM Table where type LIKE :type")
//    List<Table> filterTables(String type);
//
//    @Query("SELECT * FROM Table where added LIKE :TableDate")
//    List<Table> getTodayTables(Date TableDate);
//
//    @Query("SELECT * FROM Table WHERE added BETWEEN :dayst AND :dayet")
//    List<Table> filterTablesByDate(Date dayst, Date dayet);

    @Query("SELECT COUNT(*) from Question")
    int countSize();

    @Insert(onConflict = REPLACE)
    long insert(Question question);
//
//    @Update(onConflict = REPLACE)
//    void updateTable(Table Table);
//
//    @Delete
//    void delete(Table Table);
//
//    @Query("DELETE FROM Table")
//    void deleteAll();

}
