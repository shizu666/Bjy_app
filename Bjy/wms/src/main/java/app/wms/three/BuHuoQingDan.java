package app.wms.three;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.wms.R;
import app.wms.adapter.BhqdListViewAdapter;
import app.wms.empty.HttpApi;
import app.wms.empty.MoveTaskProductResponse;
import app.wms.empty.MoveTaskRequest;
import app.wms.empty.MyQingDan;
import app.wms.tool.HttpUtils;
import app.wms.tool.Json;
import app.wms.tool.Others;
import app.wms.view.MyListView;

public class BuHuoQingDan extends AppCompatActivity {
    private TextView tv_order_name;
    private MyQingDan myQingDan;
    private List<MyQingDan> list = new ArrayList<MyQingDan>();;
    private ScrollView scrollView;
    private MyListView myListView;
    private Button but_bhqd_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bu_huo_qing_dan);
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        but_bhqd_back = (Button) findViewById(R.id.but_bhqd_back);
        myListView = (MyListView) findViewById(R.id.mlv_bhqd);
        scrollView = (ScrollView) findViewById(R.id.sv_bhqd);
        scrollView.smoothScrollTo(0,0);
        String taskNo = getIntent().getStringExtra("order");
        tv_order_name.setText("补货清单"+"("+taskNo+")");
        MoveTaskRequest mtr = new MoveTaskRequest();
        mtr.setTaskNo(taskNo);
        mtr.setOperator(Others.getOperator());
        mtr.setWarehouseCode(HttpApi.code);
        Gson gson = Json.getGson();
        String params = gson.toJson(mtr);
        String url = HttpApi.Ip+HttpApi.requestHead+HttpApi.checkAndGetMoveTask;
        try {
            HttpUtils.httpPost(url,params,handler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        but_bhqd_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuHuoQingDan.this.finish();
            }
        });


    }

    //获取补货任务信息
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1 == 2){
                String result = (String) msg.obj;
                try {
                    myQingDan = new MyQingDan();
                    myQingDan.setTv_qd_bianhao("商品编号");
                    myQingDan.setTv_qd_name("商品名称");
                    myQingDan.setTv_qd_shuliang("计划数量");
                    myQingDan.setTv_qd_dsj("待上架");
                    list.add(myQingDan);
                    JSONObject jo = Json.getObject(result);
                    JSONObject j = jo.getJSONObject("result");
                    JSONArray ja = j.getJSONArray("moveTaskProductResponses");
                    for (int i = 0 ; i < ja.length() ; i++ ){
                        JSONObject joo = ja.getJSONObject(i);
                        myQingDan = new MyQingDan();
                        myQingDan.setTv_qd_bianhao(joo.getString("sku"));
                        myQingDan.setTv_qd_name(joo.getString("productName"));
                        myQingDan.setTv_qd_shuliang(String.valueOf(joo.getInt("planNum")));
                        myQingDan.setTv_qd_dsj(String.valueOf(joo.getInt("planNum")-joo.getInt("actualNum")));
                        list.add(myQingDan);
                    }
                    BhqdListViewAdapter adapter = new BhqdListViewAdapter(BuHuoQingDan.this,list);
                    myListView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    };

}
