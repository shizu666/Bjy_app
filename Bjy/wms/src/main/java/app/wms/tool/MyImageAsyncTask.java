package app.wms.tool;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * Created by zhou on 2016/9/22.
 */

public class MyImageAsyncTask extends AsyncTask<String,Integer,Bitmap> {


    @Override
    protected Bitmap doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }

}
