package app.wms.tow;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import app.wms.R;
import app.wms.empty.HttpApi;
import app.wms.empty.StockInventoryResult;
import app.wms.empty.StockInventoryResultRequest;
import app.wms.empty.StockInventoryTaskResquest;
import app.wms.empty.StockLocationRequest;
import app.wms.tool.HttpUtils;
import app.wms.tool.Json;
import app.wms.tool.Others;

public class PanDianRenWu extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_order_name ,tv_pd_local ,tv_pd_name ,tv_pd_unit;
    private EditText et_pd_local ,et_pd_sku ,et_pd_num;
    private Button but_confirm ,but_back;
    private List<StockInventoryResult> listResult = new ArrayList<StockInventoryResult>();
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pan_dian_ren_wu);
        initView();
        String orderNum = this.getIntent().getStringExtra("order");
        tv_order_name.setText("盘点任务"+"("+orderNum+")");

        StockInventoryTaskResquest sitr = new StockInventoryTaskResquest();
        sitr.setTaskOperator(Others.getOperator());
        sitr.setWarehouseCode(HttpApi.code);
        sitr.setInventoryNo(orderNum);
        Gson gson = Json.getGson();
        String json = gson.toJson(sitr);
        String url = HttpApi.Ip+HttpApi.requestHead+HttpApi.getInventoryTask;
        try {
            HttpUtils.httpPost(url,json,handler);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1 == 2){
                String result = (String) msg.obj;
                Log.i("rs",result);
                try {
                    JSONObject j = Json.getObject(result);
                    if(j.getInt("code")==200){
                        JSONObject jo = j.getJSONObject("result");
                        JSONArray ja = jo.getJSONArray("inventoryResultList");
                        for (int i = 0 ;i < ja.length();i++){
                            JSONObject joo = ja.getJSONObject(i);
                            StockInventoryResult slr = new StockInventoryResult();
                            slr.setSku(joo.getString("sku"));
                            slr.setInventoryNo(joo.getString("inventoryNo"));
                            slr.setInventoryTaskNo(joo.getString("inventoryTaskNo"));
                            slr.setLocationCode(joo.getString("locationCode"));
                            slr.setWarehouseCode(joo.getString("warehouseCode"));
                            slr.setOwnerCode(joo.getString("ownerCode"));
                            slr.setProductName(joo.getString("name"));
                            slr.setProductUnit(joo.getString("unit"));
                            listResult.add(slr);
                        }
                        upView(0);
                    }else{
                        Toast.makeText(PanDianRenWu.this,j.getString("message"),Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private void upView(int i){
        StockInventoryResult slr = listResult.get(i);
        tv_pd_local.setText(slr.getLocationCode());
        tv_pd_name.setText(slr.getProductName());
        tv_pd_unit.setText(slr.getProductUnit());
    }



    private void initView(){
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        tv_pd_local = (TextView) findViewById(R.id.tv_pd_local);
        tv_pd_name = (TextView) findViewById(R.id.tv_pd_name);
        tv_pd_unit = (TextView) findViewById(R.id.tv_pd_unit);
        et_pd_local = (EditText) findViewById(R.id.et_pd_local);
        et_pd_sku = (EditText) findViewById(R.id.et_pd_sku);
        et_pd_num = (EditText) findViewById(R.id.et_pd_num);
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

                String local = et_pd_local.getText().toString();
                String sku = et_pd_sku.getText().toString();
                String num = et_pd_num.getText().toString();
                StockInventoryResultRequest sirr = new StockInventoryResultRequest();
                sirr.setInventoryNo(listResult.get(i).getInventoryNo());
                sirr.setWarehouseCode(listResult.get(i).getWarehouseCode());
                sirr.setLocationCode(local);
                sirr.setOwnerCode(listResult.get(i).getOwnerCode());
                sirr.setSku(sku);
                sirr.setInventoryTaskNo(listResult.get(i).getInventoryTaskNo());
                sirr.setOperator(Others.getOperator());
                sirr.setActualNum(Integer.valueOf(num));
                Gson gson = Json.getGson();
                String json = gson.toJson(sirr);
                String url = HttpApi.Ip+HttpApi.requestHead+HttpApi.addInventoryResult;
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
                        if((i+1)!=listResult.size()){
                            i++;
                            upView(i);
                            et_pd_local.setText("");
                            et_pd_num.setText("");
                            et_pd_sku.setText("");
                        }else{
                            PanDianRenWu.this.finish();
                        }
                    }else{
                        Toast.makeText(PanDianRenWu.this,jo.getString("message"),Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

}
