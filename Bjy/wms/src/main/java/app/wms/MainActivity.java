package app.wms;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                Intent intent = new Intent(MainActivity.this,OrderDetails.class);
                startActivity(intent);

            }
        });

    }

    private void initView() {
        etName = (EditText) findViewById(R.id.etName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        butChangePassword = (Button) findViewById(R.id.butChangePassword);
        butLanding = (Button) findViewById(R.id.butLanding);
    }



}
