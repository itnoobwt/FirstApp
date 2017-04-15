package firstapp.system.com.myapplication.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 2017/4/11.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper
{
    private String sql = "create table Book (id integer primary key autoincrement," +
            "author text,price real,pages integer,name text)";
    public MyDatabaseHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory,int vesion){
        super(context,dbName,factory,vesion);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public void insert(String table, String nullColumnHack, ContentValues vaules){
         getWritableDatabase().insert(table,nullColumnHack,vaules);
    }

    /**
     * 更新
     * @param table
     * @param nullColumnHack  条件
     * @param vaules
     * @param whereArgs   条件值
     */
    public void update(String table,String nullColumnHack,ContentValues vaules,String[] whereArgs){
        getWritableDatabase().update(table,vaules,nullColumnHack,whereArgs);
    }

    /**
     * 删除
     * @param table
     * @param whereClause
     * @param whereArgs
     */
    public void delete(String table,String whereClause,String[] whereArgs){
        getWritableDatabase().delete(table,whereClause,whereArgs);
    }
    public void select(){
    }
}
