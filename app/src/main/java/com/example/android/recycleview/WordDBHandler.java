package com.example.android.recycleview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 04/05/2018.
 */

public class WordDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_WORD = "db_word";
    private static final String TABLE_WORD = "table_word";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_WORD = "word";

    public WordDBHandler (Context context) {
        super(context, DATABASE_WORD, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_WORD_TABLE = "CREATE TABLE " + TABLE_WORD + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        COLUMN_WORD + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_WORD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_WORD);
        onCreate(sqLiteDatabase);
    }

    // FUNGSI UNTUK TAMBAH DATA WORD
    public void tambahWord(Word word){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_WORD, word.wordItem);

        db.insert(TABLE_WORD, null, values);
        db.close();
    }

    // FUNGSI UNTUK AMBIL 1 DATA WORD
    public Word getWord(int id_word){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WORD, new String[]{COLUMN_ID, COLUMN_WORD},
                COLUMN_ID + "=?", new String[]{String.valueOf(id_word)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Word word = new Word(cursor.getString(1));
        return word;
    }

    // FUNGSI UNTUK AMBIL SEMUA DATA WORD
    public List<Word> getAllWord(){
        List<Word> wordList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_WORD;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Word word = new Word(cursor.getString(1));
                wordList.add(word);
            } while (cursor.moveToNext());
        }
        return wordList;
    }

//    // FUNGSI MENGHITUNG ADA BEBERAPA DATA
//    public int getWordCount(){
//        String countQuery = "SELECT * FROM " + TABLE_WORD;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
//        return cursor.getCount();
//    }

//    // FUNGSI UPDATE DATA WORD
//    public int updateDataWord(Word word) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_WORD, word.wordItem);
//        return db.update(TABLE_WORD, values, COLUMN_WORD + " = ?",
//                new String[]{String.valueOf(word.id)});
//    }


    // FUNGSI HAPUS DATA 1 WORD
    public void hapusDataWord(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORD, COLUMN_ID + " = ?",
                new String[]{String.valueOf(word.id)});
        db.close();
    }
}
