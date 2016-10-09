package app.wms;

import android.content.DialogInterface;
import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.wms.empty.HttpApi;
import app.wms.empty.OutBoundAffirmRequest;
import app.wms.three.ChuKuQingDan;
import app.wms.tool.HttpUtils;
import app.wms.tool.Json;
import app.wms.tool.Others;

public class StockRemoval extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_order_name ,tv_sr_xianghao ,tv_sr_ordernum ,tv_sr_mudidi;
    private EditText et_sr_chengyunshang ,et_sr_order;
    private Button but_sr_qingdan ,but_sr_confirm ,but_sr_back;
    private List<String> list ;
    private String order ;
    private CustomApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_removal);
        initView();
        tv_order_name.setText("出库");

    }

    private void initView(){
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        tv_sr_xianghao = (TextView) findViewById(R.id.tv_sr_xianghao);
        tv_sr_ordernum = (TextView) findViewById(R.id.tv_sr_ordernum);
        tv_sr_mudidi = (TextView) findViewById(R.id.tv_sr_mudidi);
        but_sr_qingdan = (Button) findViewById(R.id.but_sr_qingdan);
        but_sr_confirm = (Button) findViewById(R.id.but_sr_confirm);
        but_sr_back = (Button) findViewById(R.id.but_sr_back);
        et_sr_chengyunshang = (EditText) findViewById(R.id.et_sr_chengyunshang);
        et_sr_order = (EditText) findViewById(R.id.et_sr_order);

        but_sr_qingdan.setOnClickListener(this);
        but_sr_confirm.setOnClickListener(this);
        but_sr_back.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_sr_qingdan:
                Intent intent = new Intent(this, ChuKuQingDan.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("order", (ArrayList<String>) list);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.but_sr_back:
                this.finish();
                break;
            case R.id.but_sr_confirm:
                String url = HttpApi.Ip+HttpApi.requestHead+HttpApi.affirm;
                order  = et_sr_order.getText().toString();
                String carrier = et_sr_chengyunshang.getText().toString();
                if(!order.isEmpty()&&!carrier.isEmpty()){
                    OutBoundAffirmRequest obar = new OutBoundAffirmRequest();
                    obar.setCarrierNo(carrier);
                    obar.setOrderNo(order);
                    obar.setBaseWarehouseCode(HttpApi.code);
                    Gson gson = Json.getGson();
                    String json = gson.toJson(obar);
                    try {
                        HttpUtils.httpPost(url,json,handler);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Others.showToast(this,"信息不能为空！");
                }
                break;
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1 == 2){
                String result = (String) msg.obj;
                try {
                    JSONObject jo = Json.getObject(result);
                    if(jo.getInt("code")==200){
                        list.add(order);
                        tv_sr_ordernum.setText(String.valueOf(list.size()));
                        et_sr_chengyunshang.setText("");
                        et_sr_order.setText("");
                        Others.showToast(StockRemoval.this,"出库确认成功!");
                    }else{
                        Others.showToast(StockRemoval.this,jo.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();e.printStackTrace();
                }
            }
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        app = (CustomApplication) getApplication();
        if(app.getList() == null){
            list = new ArrayList<String>();
        }else{
            list = app.getList();
        }
        tv_sr_ordernum.setText(String.valueOf(list.size()));

    }
}
