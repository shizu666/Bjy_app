package app.wms;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import app.wms.empty.HttpApi;
import app.wms.three.BuHuoQingDan;
import app.wms.tool.HttpUtils;
import app.wms.tow.OrderDetails;

public class MainActivity extends AppCompatActivity{

    private EditText etName,etPassword;
    private Button butLanding,butChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化数据
        initView();
        //登陆
        butLanding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PdaIndex.class);
                MainActivity.this.finish();
                startActivity(intent);

            }
        });
        //修改密码
        butChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BuHuoQingDan.class);
                startActivity(intent);
            }
        });

        //String url = "http://192.168.9.136:8080/"+HttpApi.requestHead+HttpApi.getchecklistbyorderno+"PO201609230003"+HttpApi.baseWarehouseCode+"sh001";
        String url = "http://192.168.9.136:8080/"+HttpApi.requestHead+HttpApi.getAllProducts;
        HttpUtils.httpGET(url,handler);



    }

    private void initView() {
        etName = (EditText) findViewById(R.id.etName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        butChangePassword = (Button) findViewById(R.id.butChangePassword);
        butLanding = (Button) findViewById(R.id.butLanding);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 1) {
                String result = (String) msg.obj;
                Log.i("reulst",result);
            }else if(msg.arg1 == 2){
                String result = (String) msg.obj;
                Log.i("result2",result);
            }

        }
    };


}
