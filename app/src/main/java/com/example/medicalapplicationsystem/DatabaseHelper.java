package com.example.medicalapplicationsystem;

// Assuming you have a DatabaseHelper class to handle SQLite operations

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserInfo.db";
    private static final int DATABASE_VERSION = 3;

    // Table name and column names
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_MOBILE = "mobile";
    private static final String TABLE_EMERGENCY = "emergency";
    private static final String COLUMN_EMERGENCY_PHONE = "phone_number";

    // Create table query
    private static final String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_NAME + " TEXT," +
            COLUMN_EMAIL + " TEXT," +
            COLUMN_PASSWORD + " TEXT," +
            COLUMN_MOBILE + " TEXT" + ")";

    private static final String CREATE_EMERGENCY_TABLE = "CREATE TABLE " + TABLE_EMERGENCY + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_EMERGENCY_PHONE + " TEXT" + ")";

    private static final String TABLE_CHATBOT = "chatbot";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_ANSWER = "answer";

    // Create table queries for users, emergency contacts, and chatbot
    private static final String CREATE_CHATBOT_TABLE = "CREATE TABLE " + TABLE_CHATBOT + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_QUESTION + " TEXT," +
            COLUMN_ANSWER + " TEXT" + ")";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_EMERGENCY_TABLE);
        db.execSQL(CREATE_CHATBOT_TABLE);

    }
    public long addChatbotEntry(String question, String answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION, question);
        values.put(COLUMN_ANSWER, answer);
        return db.insert(TABLE_CHATBOT, null, values);
    }

    @SuppressLint("Range")
    public String getAnswerForQuestion(String question) {
        SQLiteDatabase db = this.getReadableDatabase();
        String answer = null;
        Cursor cursor = db.query(TABLE_CHATBOT, new String[]{COLUMN_ANSWER}, COLUMN_QUESTION + "=?", new String[]{question}, null, null, null);
        if (cursor.moveToFirst()) {
            answer = cursor.getString(cursor.getColumnIndex(COLUMN_ANSWER));
        }
        cursor.close();
        return answer;
    }
    public boolean updateChatbotEntry(String question, String newAnswer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ANSWER, newAnswer);
        int rowsAffected = db.update(TABLE_CHATBOT, values, COLUMN_QUESTION + "=?", new String[]{question});
        return rowsAffected > 0;
    }

    // Method to delete a chatbot entry based on the question from the chatbot table
    public boolean deleteChatbotEntry(String question) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_CHATBOT, COLUMN_QUESTION + "=?", new String[]{question});
        return rowsAffected > 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMERGENCY);
        onCreate(db);
    }

    // Method to add user to database
    public long addUser(String name, String email, String password, String mobile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_MOBILE, mobile);
        return db.insert(TABLE_USERS, null, values);
    }
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_NAME + "=? AND " + COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_USERS, null, selection, selectionArgs, null, null, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
    public long addEmergencyContact(String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMERGENCY_PHONE, phoneNumber);
        return db.insert(TABLE_EMERGENCY, null, values);
    }

    public boolean isEmergencyContactExists() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EMERGENCY, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean updateEmergencyContact(String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMERGENCY_PHONE, phoneNumber);
        int rowsAffected = db.update(TABLE_EMERGENCY, values, null, null);
        return rowsAffected > 0;
    }
    @SuppressLint("Range")
    public String getEmergencyPhoneNumber() {
        SQLiteDatabase db = this.getReadableDatabase();
        String phoneNumber = null;
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_EMERGENCY_PHONE + " FROM " + TABLE_EMERGENCY, null);
        if (cursor.moveToFirst()) {
            phoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_EMERGENCY_PHONE));
        }
        cursor.close();
        return phoneNumber;
    }
}
