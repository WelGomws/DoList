package com.DoList.DoList.task_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import com.DoList.DoList.Task;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAcess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAcess instance;
    public final String TABLE;
    public static final String _ID = "_id";
    public static final String _ID_TASK_COMPLETE = "_id_tasks_complete";
    public static final String TASKS = "tasks";
    String[] parametros;

    public DatabaseAcess(Context context, String TABLE){
        this.openHelper = new BancoController(context);
        this.TABLE = TABLE;
    }

    public static DatabaseAcess getInstance(Context context, String TABLE){

        if (instance == null){
            instance = new DatabaseAcess(context, TABLE);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close(){
        if(database != null){
            this.database.close();
        }
    }

    public List<Task> returnAllTask() {
        open();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE);
        Cursor cursor = queryBuilder.query(database, new String[]{_ID, TASKS, _ID_TASK_COMPLETE}, null, null, null, null, null);

        List<Task> result = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                Task task = new Task();
                task._id = cursor.getInt((cursor.getColumnIndex(_ID)));
                task.task = cursor.getString((cursor.getColumnIndex(TASKS)));
                task._id_task_complete = cursor.getInt((cursor.getColumnIndex(_ID_TASK_COMPLETE)));

                result.add(task);
            }while (cursor.moveToNext());
            cursor.close();
            close();
            return result;
        } else {
            cursor.close();
            close();
            return null;
        }
    }

    public void insertTable(String task){
        ContentValues values = new ContentValues();
        values.put(TASKS, task);
        database.insertOrThrow(TABLE, null, values);
        database.close();
    }

    public void deleteTask(int id){
        parametros = new String[1];
        parametros[0] = String.valueOf(id);
        database.delete(TABLE, "_id = ?", parametros);
    }

}
