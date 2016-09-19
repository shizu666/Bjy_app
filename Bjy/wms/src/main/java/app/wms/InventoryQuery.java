package app.wms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InventoryQuery extends AppCompatActivity {
    private TextView tv_order_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_query);
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        tv_order_name.setText("库存查询");

    }
}
