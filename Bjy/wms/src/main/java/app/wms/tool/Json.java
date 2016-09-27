package app.wms.tool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhou on 2016/9/27.
 */

public class Json {

    //获取json对象
    public static JSONObject getObject(String result) throws JSONException {
        JSONObject jsonObject = new JSONObject(result);
        return jsonObject;
    }

    //获取json数组
    public static JSONArray getArray(String result,String array) throws JSONException {
        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray(array);
        return jsonArray;
    }

}
