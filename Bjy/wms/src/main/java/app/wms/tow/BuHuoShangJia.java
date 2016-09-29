package app.wms.tow;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.speech.SpeechRecognizer;
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

import app.wms.R;
import app.wms.empty.HttpApi;
import app.wms.empty.MoveTaskProductResponse;
import app.wms.empty.MoveTaskRequest;
import app.wms.empty.StockLocationRequest;
import app.wms.tool.HttpUtils;
import app.wms.tool.Json;
import app.wms.tool.Others;

public class BuHuoShangJia extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_order_name ,tv_bhsj_name ,tv_bhsj_num ,tv_bhsj_unit;
    private EditText et_bhsj_local ,et_bhsj_sku ,et_bhsj_num;
    private Button but_com_confirm ,but_com_dsj ,but_com_back;
    private String tackNo;
    private List<MoveTaskProductResponse> listResponse = new ArrayList<MoveTaskProductResponse>();
    private int i = 0;//判断上架产品坐标
    private int num = 0;//获取上架数量
    private int planNum = 0 ,actualNum = 0;//获取计划上架数量、已上架数量
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bu_huo_shang_jia);
        initView();
        tackNo = getIntent().getStringExtra("order");
        tv_order_name.setText("补货上架"+"("+tackNo+")");

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
                Log.i("result",result);
                try {
                    JSONObject jo = Json.getObject(result);
                    JSONObject j = jo.getJSONObject("result");
                    JSONArray ja = j.getJSONArray("moveTaskProductResponses");
                    for (int i = 0 ; i < ja.length() ; i++ ){
                        JSONObject joo = ja.getJSONObject(i);
                        MoveTaskProductResponse mtr = new MoveTaskProductResponse();
                        mtr.setSrcLocation(joo.getString("srcLocation"));//源货位
                        mtr.setDestLocation(joo.getString("destLocation"));//目标货位
                        mtr.setSku(joo.getString("sku"));
                        mtr.setProductName(joo.getString("productName"));
                        mtr.setProductUnit(joo.getString("productUnit"));
                        mtr.setPlanNum(joo.getInt("planNum"));
                        if(String.valueOf(joo.getInt("actualNum"))==null){
                            mtr.setActualNum(0);
                        }else{
                            mtr.setActualNum(joo.getInt("actualNum"));
                        }
                        mtr.setWarehouseCode(j.getString("warehouseCode"));
                        mtr.setOwnerCode(j.getString("ownerCode"));
                        mtr.setOperator(Others.getOperator());
                        mtr.setTaskNo(j.getString("taskNo"));
                        mtr.setBatchNo(joo.getString("batchNo"));
                        listResponse.add(mtr);
                    }
                    upView(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    };


    //更新补货下架数据
    public void upView(int i){
        tv_bhsj_name.setText(listResponse.get(i).getProductName());
        tv_bhsj_num.setText(listResponse.get(i).getActualNum()+"/"+listResponse.get(i).getPlanNum());
        tv_bhsj_unit.setText(listResponse.get(i).getProductUnit());
        actualNum = listResponse.get(i).getActualNum();
        planNum = listResponse.get(i).getPlanNum();
    }


    private void initView() {
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        tv_bhsj_name = (TextView) findViewById(R.id.tv_bhsj_name);
        tv_bhsj_num = (TextView) findViewById(R.id.tv_bhsj_num);
        tv_bhsj_unit = (TextView) findViewById(R.id.tv_bhsj_unit);
        et_bhsj_local = (EditText) findViewById(R.id.et_bhsj_local);
        et_bhsj_sku = (EditText) findViewById(R.id.et_bhsj_sku);
        et_bhsj_num = (EditText) findViewById(R.id.et_bhsj_num);
        but_com_back = (Button) findViewById(R.id.but_com_back);
        but_com_confirm = (Button) findViewById(R.id.but_com_confirm);
        but_com_dsj = (Button) findViewById(R.id.but_com_dsj);

        but_com_back.setOnClickListener(this);
        but_com_confirm.setOnClickListener(this);
        but_com_dsj.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_com_back:
                this.finish();
                break;
            case R.id.but_com_confirm:
                String local = et_bhsj_local.getText().toString();
                String sku = et_bhsj_sku.getText().toString();
                num = Integer.valueOf(et_bhsj_num.getText().toString());
                StockLocationRequest slr = new StockLocationRequest();
                slr.setBatchNo(listResponse.get(i).getBatchNo());
                slr.setLocationCode(local);
                slr.setSku(sku);
                slr.setOperator(Others.getOperator());
                slr.setTaskNo(listResponse.get(i).getTaskNo());
                slr.setWarehouseCode(listResponse.get(i).getWarehouseCode());
                slr.setOwnerCode(listResponse.get(i).getOwnerCode());
                slr.setValidNum(num);
                Gson gson = Json.getGson();
                String json = gson.toJson(slr);
                String  url = HttpApi.Ip+HttpApi.requestHead+HttpApi.doReplenishOff;
                try {
                    HttpUtils.httpPost(url,json,handler2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.but_com_dsj:

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
                        if((num+actualNum)!=planNum){
                            et_bhsj_local.setText("");
                            et_bhsj_sku.setText("");
                            actualNum = num+actualNum;
                            et_bhsj_num.setText(actualNum+"/"+planNum);
                        }else{
                            if((i+1)!=listResponse.size()){
                                i++;
                                upView(i);
                            }else{
                                BuHuoShangJia.this.finish();
                            }
                        }
                    }else{
                        Toast.makeText(BuHuoShangJia.this,jo.getString("message"),Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };


}
