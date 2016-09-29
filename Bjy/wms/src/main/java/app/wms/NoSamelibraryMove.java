package app.wms;


import android.content.Intent;
import android.graphics.Color;
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
import android.widget.LinearLayout;
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
import app.wms.empty.MoveTaskResponse;
import app.wms.empty.StockLocationRequest;
import app.wms.three.KuaYiKuQingDan;
import app.wms.tool.HttpUtils;
import app.wms.tool.Json;
import app.wms.tool.Others;

public class NoSamelibraryMove extends AppCompatActivity implements View.OnClickListener {
    //移库下架
    private TextView tv_nslm_xiajia , tv_nslm_xiajianame ,tv_nslm_xiajiaunit;
    private EditText et_nslm_xiajiahuowei ,et_nslm_xiajiasku ,et_nslm_xianum;
    //移库上架
    private TextView tv_nslm_shangjia ,tv_nslm_shangjianame ,tv_nslm_shangjianum ,tv_nslm_shangjiaunit;
    private EditText et_nslm_shangjiahuowei ,et_nslm_shangjiasku ,et_nslm_shangjiaplannum;

    private LinearLayout ll_nslm_shang ,ll_nslm_xia;
    private Button but_nslm_dsj , but_nslm_confirm ,but_nslm_back;
    private boolean xiaFalg = true;//是否下架
    private boolean shangFlag = false;//是否上架
    private List<MoveTaskProductResponse> listResponse = new ArrayList<MoveTaskProductResponse>();
    private String xialocal , xiasku ,shanglocal ,shangsku;
    private boolean xiaLocalFlag = false , xiaSkuFlag = false , shangLocalFlag = false, shangSkuFlag = false;
    private int productIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_samelibrary_move);
        initView();

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

        //监听下架内容
        et_nslm_xiajiahuowei.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null&&s.length()>=13){
                    xialocal = s.toString();
                    xiaLocalFlag = true;
                    if(xiaSkuFlag){
                        checkLocalSku("xia","");
                    }
                }else{
                    xiaLocalFlag = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_nslm_xiajiasku.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null&&s.length()==6){
                    xiasku = s.toString();
                    xiaSkuFlag = true;
                    if(xiaLocalFlag){
                        checkLocalSku("xia","");
                    }
                }else {
                    xiaSkuFlag = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //监听上架内容

        et_nslm_shangjiahuowei.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null&&s.length()>=13){
                    shanglocal = s.toString();
                    shangLocalFlag = true;
                    if(shangSkuFlag){
                        checkLocalSku("","shang");
                    }
                }else{
                    shangLocalFlag = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_nslm_shangjiasku.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null&&s.length()==6){
                    shangsku = s.toString();
                    shangSkuFlag = true;
                    if(shangLocalFlag){
                        checkLocalSku("","shang");
                    }
                }else{
                    shangSkuFlag = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    //判断上架还是下架
    public void checkLocalSku(String xia , String shang){
        if(xia.equals("xia")){
            boolean xiaflag = false;
            for(int i = 0 ; i <listResponse.size() ; i++){
                if(xialocal.equals(listResponse.get(i).getSrcLocation())&&xiasku.equals(listResponse.get(i).getSku())){
                    productIndex = i;
                    xiaflag = true;
                    upXiaView(i);
                    return;
                }
            }
            if(!xiaflag){
                Toast.makeText(this,"下架信息有误！",Toast.LENGTH_LONG).show();
            }
        }
        if(shang.equals("shang")){
            boolean shangflag = false;
            for (int i = 0 ; i < listResponse.size() ; i++){
                if(shanglocal.equals(listResponse.get(i).getDestLocation())&&shangsku.equals(listResponse.get(i).getSku())){
                    productIndex = i;
                    shangflag = true;
                    upShangView(i);
                    return;
                }
            }
            if(!shangflag){
                Toast.makeText(this,"上架信息有误！",Toast.LENGTH_LONG).show();
            }
        }

    }

    //更新下架信息
    private void upXiaView(int i) {
        tv_nslm_xiajianame.setText(listResponse.get(i).getProductName());
        tv_nslm_xiajiaunit.setText(listResponse.get(i).getProductUnit());
    }
    //更新上架信息
    private void upShangView(int i) {
        tv_nslm_shangjianame.setText(listResponse.get(i).getProductName());
        tv_nslm_shangjiaunit.setText(listResponse.get(i).getProductUnit());
        tv_nslm_shangjianum.setText(listResponse.get(i).getActualNum()+"/"+listResponse.get(i).getPlanNum());
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
                        }else{
                            Toast.makeText(NoSamelibraryMove.this,jo.getString("message"),Toast.LENGTH_LONG).show();
                            NoSamelibraryMove.this.finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
        }
    };




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_nslm_xiajia://移库下架
                ll_nslm_xia.setVisibility(View.VISIBLE);
                ll_nslm_shang.setVisibility(View.GONE);
                but_nslm_dsj.setVisibility(View.GONE);
                tv_nslm_xiajia.setBackgroundColor(Color.parseColor("#ffffff"));
                tv_nslm_shangjia.setBackgroundColor(Color.parseColor("#cccccc"));
                xiaFalg = true;
                shangFlag = false;
                break;
            case R.id.tv_nslm_shangjia://移库上架
                ll_nslm_xia.setVisibility(View.GONE);
                ll_nslm_shang.setVisibility(View.VISIBLE);
                but_nslm_dsj.setVisibility(View.VISIBLE);
                tv_nslm_xiajia.setBackgroundColor(Color.parseColor("#cccccc"));
                tv_nslm_shangjia.setBackgroundColor(Color.parseColor("#ffffff"));
                shangFlag = true;
                xiaFalg = false;
                break;
            case R.id.but_nslm_dsj://移库待上架清单
                Intent intent = new Intent(this, KuaYiKuQingDan.class);
                startActivity(intent);
                break;
            case R.id.but_nslm_back:
                this.finish();
                break;
            case R.id.but_nslm_confirm:
                if(xiaFalg){
                    StockLocationRequest slr = new StockLocationRequest();
                    slr.setOperator(listResponse.get(productIndex).getOperator());
                    slr.setLocationCode(listResponse.get(productIndex).getSrcLocation());
                    slr.setSku(listResponse.get(productIndex).getSku());
                    slr.setWarehouseCode(listResponse.get(productIndex).getWarehouseCode());
                    slr.setOwnerCode(listResponse.get(productIndex).getOwnerCode());
                    slr.setValidNum(Integer.valueOf(et_nslm_xianum.getText().toString()));
                    slr.setTaskNo(listResponse.get(productIndex).getTaskNo());
                    Gson gson = new Gson();
                    String params = gson.toJson(slr);
                    String urlxia = HttpApi.Ip+HttpApi.requestHead+HttpApi.diffMoveStockLocationOff;
                    try {
                        HttpUtils.httpPost(urlxia,params,handler2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(shangFlag){
                    StockLocationRequest slr = new StockLocationRequest();
                    slr.setOperator(listResponse.get(productIndex).getOperator());
                    slr.setLocationCode(listResponse.get(productIndex).getDestLocation());
                    slr.setSku(listResponse.get(productIndex).getSku());
                    slr.setWarehouseCode(listResponse.get(productIndex).getWarehouseCode());
                    slr.setOwnerCode(listResponse.get(productIndex).getOwnerCode());
                    slr.setValidNum(Integer.valueOf(et_nslm_shangjiaplannum.getText().toString()));
                    slr.setTaskNo(listResponse.get(productIndex).getTaskNo());
                    slr.setBatchNo(listResponse.get(productIndex).getBatchNo());
                    Gson gson = new Gson();
                    String params = gson.toJson(slr);
                    String urlxia = HttpApi.Ip+HttpApi.requestHead+HttpApi.diffMoveStockLocationOn;
                    try {
                        HttpUtils.httpPost(urlxia,params,handler2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }


    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(xiaFalg){
                if(msg.arg1 == 2){
                    String result = (String) msg.obj;
                    try {
                        JSONObject jo = Json.getObject(result);
                        if(jo.getInt("code")==200){
                            tv_nslm_xiajianame.setText("");
                            et_nslm_xiajiasku.setText("");
                            et_nslm_xiajiahuowei.setText("");
                            et_nslm_xianum.setText("");
                        }else{
                            Toast.makeText(NoSamelibraryMove.this,jo.getString("message"),Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(shangFlag){
                if(msg.arg1 == 2){
                    String result = (String) msg.obj;
                    try {
                        JSONObject jo = Json.getObject(result);
                        if(jo.getInt("code")==200){
                            tv_nslm_shangjianame.setText("");
                            et_nslm_shangjiaplannum.setText("");
                            et_nslm_shangjiasku.setText("");
                            et_nslm_shangjiahuowei.setText("");
                            tv_nslm_shangjianum.setText("0/0");
                        }else{
                            Toast.makeText(NoSamelibraryMove.this,jo.getString("message"),Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };




    //初始化
    private void initView(){
        //下架
        tv_nslm_xiajia = (TextView) findViewById(R.id.tv_nslm_xiajia);
        tv_nslm_xiajianame = (TextView) findViewById(R.id.tv_nslm_xiajianame);
        tv_nslm_xiajiaunit = (TextView) findViewById(R.id.tv_nslm_xiajiaunit);
        et_nslm_xiajiahuowei = (EditText) findViewById(R.id.et_nslm_xiajiahuowei);
        et_nslm_xiajiasku = (EditText) findViewById(R.id.et_nslm_xiajiasku);
        et_nslm_xianum = (EditText) findViewById(R.id.et_nslm_xianum);
        //上架
        tv_nslm_shangjia = (TextView) findViewById(R.id.tv_nslm_shangjia);
        tv_nslm_shangjianame = (TextView) findViewById(R.id.tv_nslm_shangjianame);
        tv_nslm_shangjianum = (TextView) findViewById(R.id.tv_nslm_shangjianum);
        tv_nslm_shangjiaunit = (TextView) findViewById(R.id.tv_nslm_shangjiaunit);
        et_nslm_shangjiahuowei = (EditText) findViewById(R.id.et_nslm_shangjiahuowei);
        et_nslm_shangjiasku = (EditText) findViewById(R.id.et_nslm_shangjiasku);
        et_nslm_shangjiaplannum = (EditText) findViewById(R.id.et_nslm_shangjiaplannum);

        ll_nslm_shang = (LinearLayout) findViewById(R.id.ll_nslm_shang);
        ll_nslm_xia = (LinearLayout) findViewById(R.id.ll_nslm_xia);
        but_nslm_dsj = (Button) findViewById(R.id.but_nslm_dsj);
        but_nslm_confirm = (Button) findViewById(R.id.but_nslm_confirm);
        but_nslm_back = (Button) findViewById(R.id.but_nslm_back);

        tv_nslm_shangjia.setBackgroundColor(Color.parseColor("#cccccc"));

        //移库上下架点击事件
        tv_nslm_xiajia.setOnClickListener(this);
        tv_nslm_shangjia.setOnClickListener(this);
        but_nslm_dsj.setOnClickListener(this);
        but_nslm_back.setOnClickListener(this);
        but_nslm_confirm.setOnClickListener(this);
    }
}
