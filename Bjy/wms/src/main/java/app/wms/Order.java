package app.wms;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.wms.empty.HttpApi;
import app.wms.empty.MoveTaskProductResponse;
import app.wms.empty.MoveTaskRequest;
import app.wms.tool.HttpUtils;
import app.wms.tool.Json;
import app.wms.tool.Others;
import app.wms.tow.BuHuoXiaJia;
import app.wms.tow.JianHuoXIaJia;
import app.wms.tow.OrderDetails;
import app.wms.tow.PanDianRenWu;
import app.wms.tow.XiaoFanShangJia;
import app.wms.tow.XiaoTuiYanShou;


public class Order extends AppCompatActivity {
    private TextView et_order_showReceipts ,tv_order_name;
    private Button but_order_back;
    private int index;
    private String dingdan ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        but_order_back = (Button) findViewById(R.id.but_order_back);
        et_order_showReceipts = (TextView) findViewById(R.id.et_order_showReceipts);
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);

        List<String> list = new ArrayList<String>();
        list.add("采购上架");
        list.add("调拨上架");
        list.add("返架上架");
        list.add("消退上架");
        list.add("拣货下架");
        list.add("出库确认");
        list.add("补货作业");
        list.add("销退验收");
        list.add("同区移库");
        list.add("跨区移库");
        list.add("盘点作业");
        list.add("库存查询");

        Bundle bundle = this.getIntent().getExtras();
        index = bundle.getInt("index");
        tv_order_name.setText(list.get(index));

        //对单据号进行监听，有内容就自动跳转到订单详情页
        et_order_showReceipts.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null){
                    dingdan = s.toString();
                    if(index==7){
                        Intent intent = new Intent(Order.this,XiaoTuiYanShou.class);
                        intent.putExtra("order",s.toString());
                        startActivity(intent);
                    }else if(index==4&&s.length()==12){
                        String url = HttpApi.Ip+HttpApi.requestHead+HttpApi.getOrderInfo+s+HttpApi.baseWarehouseCode+HttpApi.code;
                        HttpUtils.httpGET(url,handler);

                    }else if(index==10){
                        Intent intent = new Intent(Order.this,PanDianRenWu.class);
                        intent.putExtra("order",s.toString());
                        startActivity(intent);
                    }else if(index==6&&s.length()==14){
                        dingdan = s.toString();
                        MoveTaskRequest mtr = new MoveTaskRequest();
                        mtr.setTaskNo(s.toString());
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
                    }else if(index==2||index==3){
                        Intent intent = new Intent(Order.this,XiaoFanShangJia.class);
                      /*  Bundle bd = new Bundle();
                        bd.putCharSequence("order",s);
                        bd.putInt("index",index);
                        intent.putExtras(bd);*/
                        startActivity(intent);
                    }else if(index==0&&s.length()==14){
                        if(index==0){
                            String url = HttpApi.Ip+HttpApi.requestHead+HttpApi.getPrePurchaseProduct+s+HttpApi.baseWarehouseCode+HttpApi.code;
                            HttpUtils.httpGET(url,handler);
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //返回
        but_order_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order.this.finish();
            }
        });


    }


    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(index == 0){
                if(msg.arg1 == 1){
                    String result = (String) msg.obj;
                    try {
                        JSONObject jsonObject = Json.getObject(result);
                        if(jsonObject.getInt("code")==200){
                            Intent intent = new Intent(Order.this,OrderDetails.class);
                            Bundle bd = new Bundle();
                            bd.putCharSequence("order",dingdan);
                            bd.putInt("index",index);
                            intent.putExtras(bd);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Order.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(index == 4){
                if(msg.arg1 == 1){
                    try {
                        JSONObject jsonObject = Json.getObject((String) msg.obj);
                        if(jsonObject.getInt("code")==200){
                            Intent intent = new Intent(Order.this,JianHuoXIaJia.class);
                            intent.putExtra("order",dingdan);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Order.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(index == 6){
                if(msg.arg1 == 2) {
                    String result = (String) msg.obj;
                    try {
                        JSONObject jo = Json.getObject(result);
                        if (jo.getInt("code") == 200) {
                            Intent intent = new Intent(Order.this, BuHuoXiaJia.class);
                            intent.putExtra("order", dingdan);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Order.this, jo.getString("message"), Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };


}
