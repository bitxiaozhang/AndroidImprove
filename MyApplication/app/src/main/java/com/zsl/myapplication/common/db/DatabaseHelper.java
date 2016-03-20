package com.zsl.myapplication.common.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zsl.myapplication.common.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsl on 2/16/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "contactManager";
    private static final String CONTACT_TABLE_NAME = "contacts";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONENUM = "phone_number";
    private static final String KEY_EMAIL = "email";

    public DatabaseHelper(final Context mContext){
        super(mContext,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACT_TABLE = "CREATE TABLE " +CONTACT_TABLE_NAME+
                "("+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"+
                KEY_PHONENUM + " TEXT," + KEY_EMAIL + " TEXT"+")";
        db.execSQL(CREATE_CONTACT_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXITS " + CONTACT_TABLE_NAME);
            onCreate(db);
        }
    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,contact.getName());
        values.put(KEY_PHONENUM,contact.getPhoneNumber());
        values.put(KEY_EMAIL,contact.getEmail());
        db.insert(CONTACT_TABLE_NAME, null, values);
        db.close();
    }

    public Contact findContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CONTACT_TABLE_NAME, new String[]{KEY_ID, KEY_NAME, KEY_PHONENUM, KEY_EMAIL},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Contact contact = new Contact();
        contact.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        contact.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
        contact.setPhoneNumber(cursor.getString(cursor.getColumnIndex(KEY_PHONENUM)));
        contact.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
        cursor.close();
        db.close();

        return contact;
    }

    public List<Contact> FindAll(){
        List<Contact> contactList = new ArrayList<Contact>();
        String selectQuery = "SELECT * FROM " + CONTACT_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                contact.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                contact.setPhoneNumber(cursor.getString(cursor.getColumnIndex(KEY_PHONENUM)));
                contact.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contactList;
    }

    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,contact.getName());
        values.put(KEY_PHONENUM,contact.getPhoneNumber());
        values.put(KEY_EMAIL, contact.getEmail());
        int result = db.update(CONTACT_TABLE_NAME,values,KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
        return result;
    }

    public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CONTACT_TABLE_NAME,KEY_ID + "=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public int getTotalNum(){
        String countQuery = "SELECT * FROM "+CONTACT_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int result = cursor.getCount();
        cursor.close();
        db.close();

        return result;
    }

}
