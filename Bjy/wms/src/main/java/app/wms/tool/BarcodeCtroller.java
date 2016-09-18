package app.wms.tool;

import android.content.Context;

import com.android.BarcodeJNI;

/**
 * Created by zhou on 2016/9/14.
 */
public class BarcodeCtroller implements BarcodeJNI.ScanCallBack {
    private  Context context;
    private BarcodeJNI barcodeJNI;


    public BarcodeCtroller(Context context){
        this.context = context;
        barcodeJNI = new BarcodeJNI(context);
    }





    @Override
    public void onScanResults(byte[] bytes) {

    }
}
