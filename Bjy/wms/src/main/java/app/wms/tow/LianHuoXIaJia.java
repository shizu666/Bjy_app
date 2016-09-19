package app.wms.tow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import app.wms.R;


public class LianHuoXIaJia extends AppCompatActivity {
    private TextView tv_order_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lian_huo_xia_jia);

        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        String orderNum = getIntent().getStringExtra("order");
        tv_order_name.setText("拣货下架"+"("+orderNum+")");

    }
}
