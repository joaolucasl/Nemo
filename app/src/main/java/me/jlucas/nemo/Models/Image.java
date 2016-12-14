package me.jlucas.nemo.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hansolo on 14/12/16.
 */
public class Image extends RealmObject {
    @PrimaryKey
    private long id;
    private String filePath;
    private Category category;
}
