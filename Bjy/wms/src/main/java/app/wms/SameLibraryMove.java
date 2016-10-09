package app.wms;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import app.wms.empty.StockLocationRequest;
import app.wms.tool.HttpUtils;
import app.wms.tool.Json;
import app.wms.tool.Others;

public class SameLibraryMove extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_order_name , tv_slm_name ,tv_slm_unit;
    private EditText et_slm_yuanhuowei ,et_slm_mudihuowei ,et_slm_sku ,et_slm_num;
    private Button but_confirm ,but_back;
    private List<MoveTaskProductResponse> listResponse = new ArrayList<MoveTaskProductResponse>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_same_library_move);
        initView();
        tv_order_name.setText("同区移库");

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


    }

    private void initView() {
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        tv_slm_name = (TextView) findViewById(R.id.tv_slm_name);
        tv_slm_unit = (TextView) findViewById(R.id.tv_slm_unit);
        et_slm_mudihuowei = (EditText) findViewById(R.id.et_slm_mudihuowei);
        et_slm_yuanhuowei = (EditText) findViewById(R.id.et_slm_yuanhuowei);
        et_slm_sku = (EditText) findViewById(R.id.et_slm_sku);
        et_slm_num = (EditText) findViewById(R.id.et_slm_num);

        but_back = (Button) findViewById(R.id.but_back);
        but_confirm = (Button) findViewById(R.id.but_confirm);

        but_confirm.setOnClickListener(this);
        but_back.setOnClickListener(this);

    }

    //接受跨区移库任务信息
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
                            mtr.setActualNum(joo.getInt("actualNum"));
                            mtr.setWarehouseCode(j.getString("warehouseCode"));
                            mtr.setOwnerCode(j.getString("ownerCode"));
                            mtr.setOperator(Others.getOperator());
                            mtr.setTaskNo(j.getString("taskNo"));
                            mtr.setBatchNo(joo.getString("batchNo"));
                            listResponse.add(mtr);
                        }
                        updateView();
                    }else{
                        Others.showToast(SameLibraryMove.this,jo.getString("message"));
                        SameLibraryMove.this.finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    private void updateView(){
        tv_slm_name.setText(listResponse.get(0).getProductName());
        tv_slm_unit.setText(listResponse.get(0).getProductUnit());
    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())){
            case R.id.but_back:
                this.finish();
                break;
            case R.id.but_confirm:
                String src = et_slm_yuanhuowei.getText().toString();
                String plan = et_slm_mudihuowei.getText().toString();
                String sku = et_slm_sku.getText().toString();
                String num = et_slm_num.getText().toString();


                StockLocationRequest slr = new StockLocationRequest();
                slr.setOperator(listResponse.get(0).getOperator());
                slr.setLocationCode(src);
                slr.setSku(sku);
                slr.setWarehouseCode(listResponse.get(0).getWarehouseCode());
                slr.setOwnerCode(listResponse.get(0).getOwnerCode());
                slr.setValidNum(Integer.valueOf(num));
                slr.setTaskNo(listResponse.get(0).getTaskNo());
                Gson gson = new Gson();
                String params = gson.toJson(slr);
                String url = HttpApi.Ip+HttpApi.requestHead+HttpApi.diffMoveStockLocationOff;
                try {
                    HttpUtils.httpPost(url,params,handler2);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                StockLocationRequest slr2 = new StockLocationRequest();
                slr2.setOperator(listResponse.get(0).getOperator());
                slr2.setLocationCode(plan);
                slr2.setSku(sku);
                slr2.setWarehouseCode(listResponse.get(0).getWarehouseCode());
                slr2.setOwnerCode(listResponse.get(0).getOwnerCode());
                slr2.setValidNum(Integer.valueOf(num));
                slr2.setTaskNo(listResponse.get(0).getTaskNo());
                slr2.setBatchNo(listResponse.get(0).getBatchNo());
                String params2 = gson.toJson(slr2);
                String url2 = HttpApi.Ip+HttpApi.requestHead+HttpApi.diffMoveStockLocationOn;
                try {
                    HttpUtils.httpPost(url2,params2,handler3);
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
                        /*et_slm_mudihuowei.setText("");
                        et_slm_num.setText("");
                        et_slm_sku.setText("");
                        et_slm_yuanhuowei.setText("");*/
                    }else{
                        Toast.makeText(SameLibraryMove.this,"下架"+jo.getString("message"),Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private Handler handler3 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1 == 2){
                String result = (String) msg.obj;
                try {
                    JSONObject jo = Json.getObject(result);
                    if(jo.getInt("code")==200){
//                        et_slm_mudihuowei.setText("");
//                        et_slm_num.setText("");
//                        et_slm_sku.setText("");
//                        et_slm_yuanhuowei.setText("");
                    }else{
                        Toast.makeText(SameLibraryMove.this,"上架"+jo.getString("message"),Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };


}
