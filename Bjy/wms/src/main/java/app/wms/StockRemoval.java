package app.wms;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.wms.three.ChuKuQingDan;

public class StockRemoval extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_order_name;
    private Button but_sr_qingdan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_removal);
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        but_sr_qingdan = (Button) findViewById(R.id.but_sr_qingdan);

        tv_order_name.setText("出库");
        but_sr_qingdan.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_sr_qingdan:
                Intent intent = new Intent(this, ChuKuQingDan.class);
                startActivity(intent);
                break;
        }
    }
}
