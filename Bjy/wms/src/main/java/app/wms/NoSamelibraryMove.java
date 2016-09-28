package app.wms;


import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import app.wms.empty.HttpApi;
import app.wms.empty.MoveTaskRequest;
import app.wms.three.KuaYiKuQingDan;
import app.wms.tool.HttpUtils;
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
    private boolean xiaFalg = false;//是否下架
    private boolean shangFlag = false;//是否上架

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

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(xiaFalg){//下架接口数据

            }else if(shangFlag){//上架接口数据

            }else{//获取移库任务信息
                if(msg.arg1 == 2){
                    String result = (String) msg.obj;
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

                }
                if(shangFlag){

                }
                break;
        }
    }






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
    }
}
