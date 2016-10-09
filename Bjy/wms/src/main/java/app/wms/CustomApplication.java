package app.wms;

import android.app.Application;

import java.util.List;

/**
 * Created by zhou on 2016/10/9.
 */

public class CustomApplication extends Application {

    private List<String> list = null;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
