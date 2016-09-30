package app.wms.tow;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
import app.wms.empty.HttpApi;
import app.wms.empty.MoveTaskProductResponse;
import app.wms.empty.MoveTaskRequest;
import app.wms.empty.StockLocationRequest;
import app.wms.tool.HttpUtils;
import app.wms.tool.Json;
import app.wms.tool.Others;

public class BuHuoXiaJia extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_order_name ,tv_bhxj_arclocal ,tv_bhxj_name ,tv_bhxj_num ,tv_bhxj_unit;
    private EditText et_bhxj_local ,et_bhxj_sku ,et_bhxj_num;
    private Button but_confirm ,but_back;
    private List<MoveTaskProductResponse> listResponse = new ArrayList<MoveTaskProductResponse>();
    private int nu = 0;
    private String tackNo;
    private int num = 0;//获取下架数量
    private int planNum = 0 ,actualOffNum = 0;//获取计划下架数量、已下架数量
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bu_huo_xia_jia);
        initView();
        tackNo = this.getIntent().getStringExtra("order");
        tv_order_name.setText("补货下架"+"("+tackNo+")");

        MoveTaskRequest mtr = new MoveTaskRequest();
        mtr.setTaskNo(tackNo);
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


    }

    //获取补货任务信息
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1 == 2){
                String result = (String) msg.obj;
                try {
                    JSONObject jo = Json.getObject(result);
                    JSONObject j = jo.getJSONObject("result");
                    JSONArray ja = j.getJSONArray("moveTaskProductResponses");
                    for (int i = 0 ; i < ja.length() ; i++ ){
                        JSONObject joo = ja.getJSONObject(i);
                        MoveTaskProductResponse mtr = new MoveTaskProductResponse();
                        mtr.setPlanNum(joo.getInt("planNum"));
                        if(String.valueOf(joo.getInt("actualOffNum"))==null){
                            mtr.setActualOffNum(0);
                        }else{
                            mtr.setActualOffNum(joo.getInt("actualOffNum"));
                        }
                        if(mtr.getPlanNum()==mtr.getActualOffNum()){
                            continue;
                        }
                        mtr.setSrcLocation(joo.getString("srcLocation"));//源货位
                        mtr.setDestLocation(joo.getString("destLocation"));//目标货位
                        mtr.setSku(joo.getString("sku"));
                        mtr.setProductName(joo.getString("productName"));
                        mtr.setProductUnit(joo.getString("productUnit"));
                        mtr.setWarehouseCode(j.getString("warehouseCode"));
                        mtr.setOwnerCode(j.getString("ownerCode"));
                        mtr.setOperator(Others.getOperator());
                        mtr.setTaskNo(j.getString("taskNo"));
                        mtr.setBatchNo(joo.getString("batchNo"));
                        listResponse.add(mtr);
                    }
                    if(listResponse.size()!=0){
                        upView(0);
                    }else{
                        BuHuoXiaJia.this.finish();
                        Intent intent = new Intent(BuHuoXiaJia.this,BuHuoShangJia.class);
                        intent.putExtra("order",tackNo);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    //更新补货下架数据
    public void upView(int i){
        tv_bhxj_name.setText(listResponse.get(i).getProductName());
        tv_bhxj_arclocal.setText(listResponse.get(i).getSrcLocation());
        if(listResponse.get(i).getActualOffNum()==null){
            listResponse.get(i).setActualOffNum(0);
        }
        tv_bhxj_num.setText(listResponse.get(i).getActualOffNum()+"/"+listResponse.get(i).getPlanNum());
        planNum = listResponse.get(i).getPlanNum();
        actualOffNum = listResponse.get(i).getActualOffNum();
        tv_bhxj_unit.setText(listResponse.get(i).getProductUnit());
    }



    private void initView(){
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        tv_bhxj_arclocal = (TextView) findViewById(R.id.tv_bhxj_arclocal);
        tv_bhxj_name = (TextView) findViewById(R.id.tv_bhxj_name);
        tv_bhxj_num = (TextView) findViewById(R.id.tv_bhxj_num);
        tv_bhxj_unit = (TextView) findViewById(R.id.tv_bhxj_unit);
        et_bhxj_local = (EditText) findViewById(R.id.et_bhxj_local);
        et_bhxj_sku = (EditText) findViewById(R.id.et_bhxj_sku);
        et_bhxj_num = (EditText) findViewById(R.id.et_bhxj_num);
        but_back = (Button) findViewById(R.id.but_back);
        but_confirm = (Button) findViewById(R.id.but_confirm);

        but_confirm.setOnClickListener(this);
        but_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_back:
                this.finish();
                break;
            case R.id.but_confirm:

                String local = et_bhxj_local.getText().toString();
                num = Integer.valueOf(et_bhxj_num.getText().toString());
                String sku = et_bhxj_sku.getText().toString();
                StockLocationRequest slr = new StockLocationRequest();
                slr.setBatchNo(listResponse.get(nu).getBatchNo());
                slr.setLocationCode(local);
                slr.setSku(sku);
                slr.setOperator(Others.getOperator());
                slr.setTaskNo(listResponse.get(nu).getTaskNo());
                slr.setWarehouseCode(listResponse.get(nu).getWarehouseCode());
                slr.setOwnerCode(listResponse.get(nu).getOwnerCode());
                slr.setValidNum(Integer.valueOf(num));
                Gson gson = Json.getGson();
                String json = gson.toJson(slr);
                String  url = HttpApi.Ip+HttpApi.requestHead+HttpApi.doReplenishOff;
                try {
                    HttpUtils.httpPost(url,json,handler2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1 == 2){
                String result = (String) msg.obj;
                try {
                    JSONObject jo = Json.getObject(result);
                    if(jo.getInt("code")==200){
                        if((num+actualOffNum)!=planNum){//判断本次下架数量是否等于计划数量
                            et_bhxj_local.setText("");
                            et_bhxj_num.setText("");
                            et_bhxj_sku.setText("");
                            actualOffNum = num+actualOffNum;
                            tv_bhxj_num.setText(actualOffNum+"/"+planNum);
                        }else{
                            if((nu+1)!=listResponse.size()){
                                nu++;
                                upView(nu);
                            }else{
                                BuHuoXiaJia.this.finish();
                                Intent intent = new Intent(BuHuoXiaJia.this,BuHuoShangJia.class);
                                intent.putExtra("order",tackNo);
                                startActivity(intent);
                            }
                        }
                    }else{
                        Toast.makeText(BuHuoXiaJia.this,jo.getString("message"),Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };


}
