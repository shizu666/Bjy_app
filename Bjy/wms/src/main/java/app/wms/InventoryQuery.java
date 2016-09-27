package app.wms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.wms.empty.StockLocationRequest;

public class InventoryQuery extends AppCompatActivity {
    private TextView tv_order_name ,tv_iq_name ,tv_iq_num ,tv_iq_unit ,tv_iq_huoweihao,tv_iq_cereateDate ,tv_iq_tiaoshu;
    private EditText et_iq_huowei , et_iq_guobiaoma;
    private Button but_iq_up ,but_iq_next ,but_iq_back;
    private List<StockLocationRequest> list = new ArrayList<StockLocationRequest>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_query);
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        tv_order_name.setText("库存查询");
        initView();

    }

    private void initView() {
    }
}
