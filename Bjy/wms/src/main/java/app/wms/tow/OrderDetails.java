package app.wms.tow;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import app.wms.empty.Product;
import app.wms.tool.DateUtils;
import app.wms.tool.HttpUtils;

public class OrderDetails extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_order_name , tv_od_name ,tv_od_self ,tv_od_num ,tv_od_unit;
    private EditText et_od_sku ,et_od_createDate ,et_od_youxiao ,et_od_huowei ,et_od_shangNum;
    private CheckBox cb_od_check;
    private Button but_confirm ,but_back;
    private int productIndex;

    private List<Product> listProduct = new ArrayList<Product>();
    private boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        initView();
        List list = new ArrayList();
        list.add("采购上架");
        list.add("调拨上架");
        list.add("返架上架");
        list.add("消退上架");
        list.add("拣货上架");
        list.add("出库确认");
        list.add("补货作业");
        list.add("销退验收");
        list.add("同区移库");
        list.add("跨区移库");
        list.add("盘点作业");
        list.add("库存查询");

        Bundle bundle = this.getIntent().getExtras();
        String order = bundle.getCharSequence("order").toString();
        int index = bundle.getInt("index");
        tv_order_name.setText(list.get(index)+"("+order+")");
        if(index==0){
            String url = HttpApi.Ip+HttpApi.requestHead+HttpApi.getPrePurchaseProduct+order+HttpApi.baseWarehouseCode+HttpApi.code;
            HttpUtils.httpGET(url,handler);
        }else if(index==1){
            cb_od_check.setVisibility(View.GONE);//赠品标识隐藏
        }



    }
    //初始化数据
    private void initView() {
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        tv_od_name = (TextView) findViewById(R.id.tv_od_name);
        tv_od_num = (TextView) findViewById(R.id.tv_od_num);
        tv_od_unit = (TextView) findViewById(R.id.tv_od_unit);
        tv_od_self = (TextView) findViewById(R.id.tv_od_self);
        et_od_createDate = (EditText) findViewById(R.id.et_od_createDate);
        et_od_sku = (EditText) findViewById(R.id.et_od_sku);
        et_od_youxiao = (EditText) findViewById(R.id.et_od_youxiao);
        et_od_huowei = (EditText) findViewById(R.id.et_od_huowei);
        et_od_shangNum = (EditText) findViewById(R.id.et_od_shangNum);
        cb_od_check = (CheckBox) findViewById(R.id.cb_od_check);
        but_back = (Button) findViewById(R.id.but_back);
        but_confirm = (Button) findViewById(R.id.but_confirm);
        but_confirm.setOnClickListener(this);
        but_back.setOnClickListener(this);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1 == 1){
                String result = (String) msg.obj;

                try {
                    JSONObject jsonObject =  new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    for(int i = 0 ; i <jsonArray.length() ; i++){
                        Product product = new Product();
                        JSONObject jo = jsonArray.getJSONObject(i);
                        product.setName(jo.getString("name"));
                        product.setNum(jo.getInt("num"));
                        product.setGiftNum(jo.getInt("giftNum"));
                        product.setSku(jo.getString("sku"));
                        product.setUnit(jo.getString("unit"));
                        product.setSelfLife(jo.getInt("selfLife"));
                        product.setInboundedNum(jo.getInt("inboundedNum"));
                        product.setOwnerCode(jo.getString("ownerCode"));
                        product.setPurchaseOrderNo(jo.getString("purchaseOrderNo"));
                        listProduct.add(product);
                    }

                    et_od_sku.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if(s!=null&&s.length()==6){
                                for(int i = 0; i < listProduct.size() ; i++){
                                    if(listProduct.get(i).getSku().equals(s.toString())){
                                        upView(i);
                                        productIndex = i;
                                        flag = true;
                                        return;
                                    }
                                }
                                if(!flag){
                                    Toast.makeText(OrderDetails.this,"商品编号有误!",Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if(msg.arg1 == 2){
                String result = (String) msg.obj;
                try {
                    JSONObject js = new JSONObject(result);
                    String message = js.getString("message");
                    if("正常".equals(message)){
                        tv_od_name.setText("");
                        tv_od_self.setText("");
                        tv_od_num.setText("0/0");
                        tv_od_unit.setText("");
                        et_od_createDate.setText("");
                        et_od_youxiao.setText("");
                        et_od_shangNum.setText("");
                        et_od_huowei.setText("");
                        et_od_sku.setText("");
                    }else{
                        Toast.makeText(OrderDetails.this,message,Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    //显示商品名称等信息
    private void upView(int i){
        tv_od_name.setText(listProduct.get(i).getName());
        tv_od_self.setText(listProduct.get(i).getSelfLife()+"天");
        int num = listProduct.get(i).getNum()+listProduct.get(i).getGiftNum();
        tv_od_num.setText(listProduct.get(i).getInboundedNum()+"/"+num);
        tv_od_unit.setText(listProduct.get(i).getUnit());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_back:
                OrderDetails.this.finish();
                break;
            case R.id.but_confirm:
                String createDate = et_od_createDate.getText().toString();
                String youxiao = et_od_youxiao.getText().toString();
                String num = et_od_shangNum.getText().toString();
                String huowei = et_od_huowei.getText().toString();
            if(createDate.isEmpty()||youxiao.isEmpty()||num.isEmpty()||huowei.isEmpty()){
                Toast.makeText(OrderDetails.this,"商品信息有误!",Toast.LENGTH_LONG).show();
            }else{
                Product product = new Product();
                product.setWarehouseCode(HttpApi.code);
                product.setOwnerCode(listProduct.get(productIndex).getOwnerCode());
                product.setOperator("110");
                product.setOperateTime(DateUtils.getCurrentDate());
                product.setPurchaseOrderNo(listProduct.get(productIndex).getPurchaseOrderNo());
                product.setSku(listProduct.get(productIndex).getSku());
                product.setLocationCode(huowei);
                product.setNum(Integer.valueOf(num));
                product.setProduceDate(createDate);
                product.setValidUntilDate(youxiao);
                Gson gson = new Gson();
                String params =  gson.toJson(product);

                String url = HttpApi.Ip+HttpApi.requestHead+HttpApi.addInbound;

                try {
                    HttpUtils.httpPost(url,params,handler);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
                break;
        }
    }

}
