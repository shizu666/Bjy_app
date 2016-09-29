package app.wms.three;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.wms.NoSamelibraryMove;
import app.wms.R;
import app.wms.adapter.KykqdListViewAdapter;
import app.wms.empty.HttpApi;
import app.wms.empty.MoveTaskProductResponse;
import app.wms.empty.MoveTaskRequest;
import app.wms.empty.MyQingDan;
import app.wms.tool.HttpUtils;
import app.wms.tool.Json;
import app.wms.tool.Others;
import app.wms.view.MyListView;

public class KuaYiKuQingDan extends AppCompatActivity {

    private MyListView myListView;
    private List<MyQingDan> list = new ArrayList<MyQingDan>();;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kua_yi_ku_qing_dan);
        scrollView = (ScrollView) findViewById(R.id.sv_kykqd);
        scrollView.smoothScrollTo(0,0);
        myListView = (MyListView) findViewById(R.id.mlv_kykqd);


        String urlTask = HttpApi.Ip+HttpApi.requestHead+HttpApi.checkMoveTask;
        MoveTaskRequest mtr = new MoveTaskRequest();
        mtr.setWarehouseCode(HttpApi.code);
        mtr.setOperator(Others.getOperator());
        Gson gson = new Gson();
        String params = gson.toJson(mtr);
        try {
            HttpUtils.httpPost(urlTask,params,handler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        KykqdListViewAdapter adapter = new KykqdListViewAdapter(this,list);
        myListView.setAdapter(adapter);

    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //获取移库任务信息
            if(msg.arg1 == 2){
                String result = (String) msg.obj;
                try {
                    JSONObject jo = Json.getObject(result);
                    if(jo.getInt("code")==200){
                        MyQingDan qd = new MyQingDan();
                        qd.setTv_qd_bianhao("商品编号");
                        qd.setTv_qd_name("商品名称");
                        qd.setTv_qd_shuliang("计划数量");
                        qd.setTv_qd_yishangjia("已上架");
                        qd.setTv_qd_dsj("待上架");
                        list.add(qd);
                        JSONObject j = jo.getJSONObject("result");
                        JSONArray ja = j.getJSONArray("moveTaskProductResponses");
                        for (int i = 0 ; i < ja.length() ; i++ ){
                            JSONObject joo = ja.getJSONObject(i);
                            MyQingDan mqd = new MyQingDan();
                            mqd.setTv_qd_bianhao(joo.getString("sku"));
                            mqd.setTv_qd_name(joo.getString("productName"));
                            mqd.setTv_qd_shuliang(String.valueOf(joo.getInt("planNum")));//计划数量
                            mqd.setTv_qd_yishangjia(String.valueOf(joo.getInt("actualNum")));//已上架数量
                            mqd.setTv_qd_dsj(String.valueOf(joo.getInt("planNum")-joo.getInt("actualNum")));//待上架数量
                            list.add(mqd);
                        }
                    }else{
                        Toast.makeText(KuaYiKuQingDan.this,jo.getString("message"),Toast.LENGTH_LONG).show();
                        KuaYiKuQingDan.this.finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    };
}
