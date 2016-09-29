package app.wms;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import app.wms.empty.HttpApi;
import app.wms.empty.StockLocationRequest;
import app.wms.tool.DateUtils;
import app.wms.tool.HttpUtils;
import app.wms.tool.Json;

public class InventoryQuery extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_order_name ,tv_iq_name ,tv_iq_num ,tv_iq_unit ,tv_iq_huoweihao,tv_iq_cereateDate ,tv_iq_tiaoshu;
    private EditText et_iq_huowei , et_iq_sku;
    private Button but_iq_up ,but_iq_next ,but_iq_back;
    private List<StockLocationRequest> list = new ArrayList<StockLocationRequest>();
    private String localtionCode = null;
    private String sku = null;
    private boolean localFlag = false ;
    private boolean skuFlag = false;
    private int num = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_query);
        initView();
        tv_order_name.setText("库存查询");

        //监听货位
        et_iq_huowei.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null&&s.length()>=13){
                    localtionCode = s.toString();
                    localFlag = true;
                    if(skuFlag){
                        httpRequest();
                    }
                }else{
                    localFlag = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //监听sku
        et_iq_sku.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null&&s.length()==6){
                    sku = s.toString();
                    skuFlag = true;
                    if(localFlag){
                        httpRequest();
                    }
                }else{
                    skuFlag = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void initView() {
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        tv_iq_name = (TextView) findViewById(R.id.tv_iq_name);
        tv_iq_num = (TextView) findViewById(R.id.tv_iq_nu);
        tv_iq_unit = (TextView) findViewById(R.id.tv_iq_unit);
        tv_iq_huoweihao = (TextView) findViewById(R.id.tv_iq_huoweihao);
        tv_iq_cereateDate = (TextView) findViewById(R.id.tv_iq_cereateDate);
        tv_iq_tiaoshu = (TextView) findViewById(R.id.tv_iq_tiaoshu);
        et_iq_sku = (EditText) findViewById(R.id.et_iq_sku);
        et_iq_huowei = (EditText) findViewById(R.id.et_iq_huowei);
        but_iq_back = (Button) findViewById(R.id.but_iq_back);
        but_iq_next = (Button) findViewById(R.id.but_iq_next);
        but_iq_up = (Button) findViewById(R.id.but_iq_up);

        but_iq_next.setOnClickListener(this);
        but_iq_back.setOnClickListener(this);
        but_iq_up.setOnClickListener(this);

    }

    //post请求数据
    public void httpRequest(){
        String url = HttpApi.Ip+HttpApi.requestHead+HttpApi.getStockLocation;
        StockLocationRequest slr = new StockLocationRequest();
        slr.setLocationCode(localtionCode);
        slr.setSku(sku);
        slr.setWarehouseCode(HttpApi.code);
        Gson gson = new Gson();
        String params = gson.toJson(slr);
        try {
            HttpUtils.httpPost(url,params,handler);
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
                try {
                    JSONArray ja = Json.getArray(result,"result");
                    for(int i = 0 ; i <ja.length() ; i++ ){
                        JSONObject jo = ja.getJSONObject(i);
                        StockLocationRequest slr = new StockLocationRequest();
                        slr.setName(jo.getString("name"));
                        slr.setValidNum(jo.getInt("validNum"));
                        slr.setUnit(jo.getString("unit"));
                        slr.setLocationCode(localtionCode);
                        slr.setProduceDate(DateUtils.getDateToString(jo.getLong("produceDate")));
                        list.add(slr);
                    }
                    but_iq_up.setVisibility(View.VISIBLE);
                    but_iq_next.setVisibility(View.VISIBLE);
                    upView(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public void upView(int i){
        tv_iq_name.setText(list.get(i).getName());
        tv_iq_cereateDate.setText(list.get(i).getProduceDate());
        tv_iq_unit.setText(list.get(i).getUnit());
        tv_iq_num.setText(String.valueOf(list.get(i).getValidNum()));
        tv_iq_huoweihao.setText(list.get(i).getLocationCode());
        tv_iq_tiaoshu.setText((i+1)+"/"+list.size());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_iq_back:
                this.finish();
                break;
            case R.id.but_iq_up:
                if(num!=0){
                    num--;
                    upView(num);
                    but_iq_up.setVisibility(View.VISIBLE);
                    but_iq_next.setVisibility(View.VISIBLE);
                }else{
                    but_iq_up.setVisibility(View.GONE);
                    Toast.makeText(this,"已经是第一条",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.but_iq_next:
                if(num!=list.size()-1){
                    num++;
                    upView(num);
                    but_iq_up.setVisibility(View.VISIBLE);
                    but_iq_next.setVisibility(View.VISIBLE);
                }else{
                    but_iq_next.setVisibility(View.GONE);
                    Toast.makeText(this,"显示完成",Toast.LENGTH_LONG).show();
                }
                break;
        }


    }
}
