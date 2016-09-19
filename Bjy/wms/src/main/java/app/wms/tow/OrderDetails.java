package app.wms.tow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.wms.R;

public class OrderDetails extends AppCompatActivity {

    private TextView tv_order_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        List list = new ArrayList();
        list.add("采购上架");
        list.add("调拨上架");
        list.add("返架上架");
        list.add("消退上架");
        list.add("拣货上架");
        list.add("出库确认");
        list.add("补货作业");
        list.add("销退验收");
        list.add("同区移库");
        list.add("跨区移库");
        list.add("盘点作业");
        list.add("库存查询");


        Bundle bundle = this.getIntent().getExtras();
        String order = bundle.getCharSequence("order").toString();
        int index = bundle.getInt("index");
        tv_order_name.setText(list.get(index)+"("+order+")");


    }
}
