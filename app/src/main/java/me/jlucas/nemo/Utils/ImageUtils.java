package me.jlucas.nemo.Utils;

import android.net.Uri;
import android.os.Environment;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.IOException;


/**
 * Created by hansolo on 14/12/16.
 */
public class ImageUtils {
    public static Uri newImageURI() {
        String filePath;
        String folderName = "NemoImages/";
        String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + folderName;
        String currentTime = String.valueOf(System.currentTimeMillis());
        String imageName;

        try {
            imageName = new String(Hex.encodeHex(DigestUtils.sha1(currentTime)));
        } catch(Exception e) {
            imageName = currentTime;
        }

        filePath = dir + imageName + ".jpg";

        new File(dir).mkdirs();
        File newfile = new File(filePath);

        try {
            newfile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Uri.fromFile(newfile);
    }
}
