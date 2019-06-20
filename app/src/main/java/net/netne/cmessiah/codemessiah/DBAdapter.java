package net.netne.cmessiah.codemessiah;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

    private static final String TAG = "DBAdapter";

    public static final String KEY_ROWID = "_id";
    public static final String KEY_TASK = "task";
    public static final String KEY_DATE = "date";

    public static final String[] ALL_KEYS = new String[] {KEY_ROWID,KEY_TASK,KEY_DATE};

    public static final int COL_ROWID = 0;
    public static final int COL_TASK = 1;
    public static final int COL_DATE = 2;

    public static final String DB_NAME = "cmList";
    public static final String TABLE_NAME = "ToDoList";
    public static final int DATABASE_VERSION = 2;

    private static final String CreateDB = "create table " + TABLE_NAME + " (" +KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_TASK+" TEXT NOT NULL, "+KEY_DATE+" TEXT"+");";

    private final Context context;
    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx){
        this.context = ctx;
        myDBHelper = new DatabaseHelper(context);
    }

    public DBAdapter open(){
        db = myDBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        myDBHelper.close();
    }

    public long insertRow(String task, String date){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TASK,task);
        initialValues.put(KEY_DATE, date);

        return db.insert(TABLE_NAME,null, initialValues);
    }


    public boolean deleteRow(long rowId){
        String where = KEY_ROWID +" = "+rowId;
        return db.delete(TABLE_NAME, where, null)!=0;
    }

    public void deleteAll(){
        Cursor c = getAllRows();
        long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
        if(c.moveToFirst()){
            do{
                deleteRow(c.getLong((int)rowId));
            }while(c.moveToNext());
        }
        c.close();
    }

    public Cursor getAllRows(){
        String where = null;
        Cursor c = db.query(true, TABLE_NAME, ALL_KEYS, where, null, null, null, null, null);
        if (c!=null){
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getRow(long rowId){
        String where = KEY_ROWID + "=" +rowId;
        Cursor c = db.query(true, TABLE_NAME, ALL_KEYS, where, null, null, null,null,null);
        if (c!=null){
            c.moveToFirst();
        }
        return c;
    }

    public boolean updateRow(Long rowId, String task, String date){
        String where = KEY_ROWID + "=" + rowId;
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_TASK, task);
        newValues.put(KEY_DATE, date);

        return db.update(TABLE_NAME, newValues, where, null)!=0;
    }

    public static class DatabaseHelper extends SQLiteOpenHelper{
        DatabaseHelper(Context context){
            super(context, DB_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase _db){
            _db.execSQL(CreateDB);
        }
        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion){
            Log.w(TAG, " Upgrading application's database from version "+oldVersion+ " to "+newVersion+",which will destroy all old data!");

            _db.execSQL("Drop TABLE IF EXISTS "+ TABLE_NAME);

            onCreate(_db);
        }
    }

}
