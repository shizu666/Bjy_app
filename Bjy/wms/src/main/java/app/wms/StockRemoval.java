package app.wms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import app.wms.three.ChuKuQingDan;

public class StockRemoval extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_order_name ,tv_sr_xianghao ,tv_sr_ordernum ,tv_sr_mudidi;
    private EditText et_sr_chengyunshang ,et_sr_order;
    private Button but_sr_qingdan ,but_sr_confirm ,but_sr_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_removal);
        initView();
        tv_order_name.setText("出库");


    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };




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
                startActivity(intent);
                break;
            case R.id.but_sr_back:
                this.finish();
                break;
            case R.id.but_sr_confirm:

                break;
        }
    }
}
