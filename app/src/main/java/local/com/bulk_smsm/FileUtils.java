package local.com.bulk_smsm;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.net.URISyntaxException;

public class FileUtils{
    public static String getPath(Uri uri) throws URISyntaxException {
         if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }
}