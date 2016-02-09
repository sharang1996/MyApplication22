package com.test.sharang.myapplication2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sharang on 1/8/16.
 */
public class HotOrNot {

    public  static final String KEY_ROWID ="_id";
    public  static final String KEY_NAME ="persons_name";
    public  static final String KEY_HOTNESS ="persons_hotness";

    private static final String DATABASE_NAME ="HotOrNotdb";
    private static final String TABLE_NAME ="peopleTable";
    private static final int DATABASE_VERSION =1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public HotOrNot(Context c){
        ourContext=c;
    }


    private static class DbHelper extends SQLiteOpenHelper{


        public DbHelper(Context context) {
            super(context, DATABASE_NAME,null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+
                            KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                            KEY_NAME+" TEXT NOT NULL, "+
                            KEY_HOTNESS+" TEXT NOT NULL);"
            );

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);

        }
    }

    public HotOrNot open()throws SQLException{
        ourHelper=new DbHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;

    }

   public long createEntry(String name, String hotness) {

        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_HOTNESS,hotness);
      return ourDatabase.insert(TABLE_NAME,null,cv);
   }



    public String getData() {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String columns[]=new String[]{KEY_ROWID,KEY_NAME,KEY_HOTNESS};
        Cursor C=ourDatabase.query(TABLE_NAME,columns,null,null,null,null,null);
        String result= "";
        int irowid=C.getColumnIndex(KEY_ROWID);
        int inameid=C.getColumnIndex(KEY_NAME);
        int ihotnessid=C.getColumnIndex(KEY_HOTNESS);
       for(C.moveToFirst();!C.isAfterLast();C.moveToNext()){

           result+=C.getString(irowid)+"                             "+
                   C.getString(inameid)+"                             "+
                   C.getString(ihotnessid)+"\n";
       }

        return result;
    }

    public String getname(Long l) throws SQLException{

        String result;
        String columns[]=new String[]{KEY_ROWID,KEY_NAME,KEY_HOTNESS};
        Cursor c=ourDatabase.query(TABLE_NAME, columns, KEY_ROWID + "=" + l, null, null, null, null);
        if(c!=null) {
            c.moveToFirst();
            result = c.getString(c.getColumnIndex(KEY_NAME));  //or 1 instead of c.getColumnIndex(KEY_NAME)
            return result;
        }
        return null;
    }

    public String gethotness(Long l) throws SQLException{
        String result;
        String columns[]=new String[]{KEY_ROWID,KEY_NAME,KEY_HOTNESS};
        Cursor c=ourDatabase.query(TABLE_NAME,columns,KEY_ROWID+"="+l,null,null,null,null);
        if(c!=null) {
            c.moveToFirst();
            result = c.getString(c.getColumnIndex(KEY_HOTNESS));  //or 2 instead of c.getColumnIndex(KEY_NAME)
            return result;
        }
        return null;
    }

    public void edit_entry(Long l2, String name, String hotness) throws SQLException{

        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_HOTNESS,hotness);
        ourDatabase.update(TABLE_NAME, cv, KEY_ROWID + "=" + l2, null);

    }

    public void delete(Long l3) throws SQLException{
        ourDatabase.delete(TABLE_NAME,KEY_ROWID+"="+l3,null);
    }


    public void close(){
        ourHelper.close();
    }

}
