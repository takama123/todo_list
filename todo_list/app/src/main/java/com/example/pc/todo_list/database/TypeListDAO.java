package com.example.pc.todo_list.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pc.todo_list.bean.TypeList;

import java.util.ArrayList;
import java.util.List;

import static com.example.pc.todo_list.database.DatabaseHandler.TABLE_TYPE_LIST;


/**
 * Created by Mac on 1/5/17.
 */

public class TypeListDAO {

    Context context;
    DatabaseHandler databaseHandler;

    public TypeListDAO(Context context) {
        this.context = context;
        databaseHandler = new DatabaseHandler(context);
    }

    public void addType(TypeList typeList) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.TypeMission.KEY_NAME, typeList.getKieu_danh_sach());

        // Inserting Row
        db.insert(TABLE_TYPE_LIST, null, values);
        db.close(); // Closing database connection
    }

    public TypeList getTypeListById (int id_list) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        Cursor cursor = db.query(TABLE_TYPE_LIST, new String[] { DatabaseHandler.TypeMission._ID,
                        DatabaseHandler.TypeMission.KEY_NAME }, DatabaseHandler.TypeMission._ID + "=?",
                new String[] { String.valueOf(id_list) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        TypeList typeList = new TypeList(cursor.getInt(0),cursor.getString(1));
        // return contact
        return typeList;
    }

    public List<TypeList> getAllType() {
        List<TypeList> allTypeList = new ArrayList<TypeList>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TYPE_LIST;

        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TypeList typeList = new TypeList(cursor.getInt(0),cursor.getString(1));
                // Adding contact to list
                allTypeList.add(typeList);
            } while (cursor.moveToNext());
        }

        // return contact list
        return allTypeList;
    }

    public int updateTypeList(TypeList typeList) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.TypeMission.KEY_NAME, typeList.getKieu_danh_sach());

        // updating row
        return db.update(TABLE_TYPE_LIST, values, DatabaseHandler.TypeMission._ID + " = ?",
                new String[] { String.valueOf(typeList.getId()) });
    }

    public void deleteTypeList(TypeList typeList) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.delete(TABLE_TYPE_LIST, DatabaseHandler.TypeMission._ID + " = ? ",
                new String[] { String.valueOf(typeList.getId())});
        db.close();
    }
}
