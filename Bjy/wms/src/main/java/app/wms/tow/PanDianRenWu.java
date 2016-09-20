package app.wms.tow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import app.wms.R;

public class PanDianRenWu extends AppCompatActivity {
    private TextView tv_order_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pan_dian_ren_wu);
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        String orderNum = this.getIntent().getStringExtra("order");
        tv_order_name.setText("盘点任务"+"("+orderNum+")");

    }
}
