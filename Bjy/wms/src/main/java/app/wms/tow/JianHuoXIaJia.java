package app.wms.tow;

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

import app.wms.R;
import app.wms.empty.CustOrderProductResponse;
import app.wms.empty.HttpApi;
import app.wms.tool.HttpUtils;
import app.wms.tool.Json;


public class JianHuoXIaJia extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_order_name ,tv_jhxj_hao ,tv_jhxj_huowei ,tv_jhxj_yingnum ,tv_jhxj_sku ,tv_jhxj_yixianum,tv_jhxj_unit;
    private EditText et_jhxj_shixiahuowei ,et_jhxj_shixianum;
    public Button but_confirm ,but_back;
    private List<CustOrderProductResponse> list = new ArrayList<CustOrderProductResponse>();
    private int productIndex;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lian_huo_xia_jia);

        initView();
        String orderNum = getIntent().getStringExtra("order");
        tv_order_name.setText("拣货下架"+"("+orderNum+")");

        String url = HttpApi.Ip+HttpApi.requestHead+HttpApi.getOrderInfo+orderNum+HttpApi.baseWarehouseCode+HttpApi.code;
        HttpUtils.httpGET(url,handler);


        et_jhxj_shixiahuowei.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null&&s.length()>=13){
                    for (int i = 0 ;i < list.size() ; i++){
                        if(list.get(i).getLocationCode().equals(s.toString())){
                            upView(i);
                            productIndex = i;
                            flag = true;
                            return;
                        }
                    }
                    if(!flag){
                        Toast.makeText(JianHuoXIaJia.this,"货位号有误！",Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        
    }

    private void initView() {
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        tv_jhxj_hao = (TextView) findViewById(R.id.tv_jhxj_hao);
        tv_jhxj_huowei = (TextView) findViewById(R.id.tv_jhxj_huowei);
        tv_jhxj_yingnum = (TextView) findViewById(R.id.tv_jhxj_yingnum);
        tv_jhxj_sku = (TextView) findViewById(R.id.tv_jhxj_sku);
        tv_jhxj_yixianum = (TextView) findViewById(R.id.tv_jhxj_yixianum);
        tv_jhxj_unit = (TextView) findViewById(R.id.tv_jhxj_unit);
        et_jhxj_shixiahuowei = (EditText) findViewById(R.id.et_jhxj_shixiahuowei);
        et_jhxj_shixianum = (EditText) findViewById(R.id.et_jhxj_shixianum);
        but_back = (Button) findViewById(R.id.but_back);
        but_confirm = (Button) findViewById(R.id.but_confirm);
        but_back.setOnClickListener(this);
        but_confirm.setOnClickListener(this);

    }

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1 == 1){
                String result = (String) msg.obj;
                try {
                    JSONArray ja = Json.getArray(result,"result");
                    for(int i = 0 ; i<ja.length() ; i++){
                        JSONObject js = ja.getJSONObject(i);
                        CustOrderProductResponse copr = new CustOrderProductResponse();
                        copr.setSku(js.getString("sku"));
                        copr.setLocationCode(js.getString("locationCode"));
                        copr.setUnit(js.getString("unit"));
                        copr.setName(js.getString("name"));
                        copr.setPlanNum(js.getInt("num"));
                        copr.setOffShelfNum(js.getString("offShelfNum"));
                        copr.setOutboundNo(js.getString("orderNo"));
                        copr.setWarehouseCode(js.getString("warehouseCode"));
                        list.add(copr);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(msg.arg1 == 2){
                String result = (String) msg.obj;
                try {
                    JSONObject jo = Json.getObject(result);
                    if(jo.getInt("code")==200){
                        tv_jhxj_unit.setText("袋");
                        tv_jhxj_yingnum.setText("");
                        tv_jhxj_hao.setText("");
                        tv_jhxj_yixianum.setText("0/0");
                        tv_jhxj_sku.setText("");
                        tv_jhxj_huowei.setText("");
                        et_jhxj_shixianum.setText("");
                        et_jhxj_shixiahuowei.setText("");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }
    };

    private void upView(int i){
        String hao = list.get(i).getLocationCode().split("-")[0]+"-"+list.get(i).getLocationCode().split("-")[1];
        tv_jhxj_hao.setText(hao);
        tv_jhxj_huowei.setText(list.get(i).getLocationCode());
        tv_jhxj_sku.setText(list.get(i).getName());
        tv_jhxj_unit.setText(list.get(i).getUnit());
        tv_jhxj_yixianum.setText(list.get(i).getOffShelfNum()+"/"+list.get(i).getPlanNum());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_back:
                this.finish();
                break;
            case R.id.but_confirm:
                if(et_jhxj_shixianum.getText().toString().isEmpty()){
                    Toast.makeText(this,"数量为空！",Toast.LENGTH_LONG).show();
                }else{
                    String num = et_jhxj_shixianum.getText().toString();
                    CustOrderProductResponse co = new CustOrderProductResponse();
                    co.setLocationCode(list.get(productIndex).getLocationCode());
                    co.setWarehouseCode(list.get(productIndex).getWarehouseCode());
                    co.setSku(list.get(productIndex).getSku());
                    co.setActualNum(Integer.valueOf(num));
                    co.setOutboundNo(list.get(productIndex).getOutboundNo());
                    co.setOperator("20160928");
                    co.setPlanNum(list.get(productIndex).getPlanNum());
                    Gson gson = new Gson();
                    String params = gson.toJson(co);

                    String ur = HttpApi.Ip+HttpApi.requestHead+HttpApi.doSubmit;
                    try {
                        HttpUtils.httpPost(ur,params,handler);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                break;
        }

    }
}
