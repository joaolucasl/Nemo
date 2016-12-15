package me.jlucas.nemo.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import me.jlucas.nemo.Utils.DBHelper;

/**
 * Created by hansolo on 14/12/16.
 */
public class Image {
    private long id;
    private String filePath;
    private String category;

    public long getId() {
        return id;
    }

    public Image setId(long id) {
        this.id = id;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public Image setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Image setCategory(String category) {
        this.category = category;
        return this;
    }

    public static List<Image> getAllImages(Context c){
        SQLiteDatabase db = new DBHelper(c).getReadableDatabase();
        Cursor query = db.rawQuery("SELECT * FROM images ORDER BY id DESC", null);
        List<Image> listImages = new ArrayList<>();

        while (query.moveToNext()) {
            Image newImg = new Image().setFilePath(query.getString(1)).setCategory(query.getString(2));
            listImages.add(newImg);
        }

        return listImages;
    }

    public static List<Image> getImagesByCategory(String category, Context c){
        SQLiteDatabase db = new DBHelper(c).getReadableDatabase();
        Cursor query = db.rawQuery("SELECT * FROM images WHERE category LIKE ? ORDER BY id DESC", new String[] {"%" + category + "%"});
        List<Image> listImages = new ArrayList<>();

        while (query.moveToNext()) {
            Image newImg = new Image().setFilePath(query.getString(1)).setCategory(query.getString(2));
            listImages.add(newImg);
        }

        return listImages;
    }
}
