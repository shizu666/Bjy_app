package app.wms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order extends AppCompatActivity {
    private TextView showReceipts ,tvOrderName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        List<String> list = new ArrayList<String>();
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
        showReceipts = (TextView) findViewById(R.id.showReceipts);
        tvOrderName = (TextView) findViewById(R.id.tvOrderName);
        Bundle bundle = this.getIntent().getExtras();
        int index = bundle.getInt("index");
        showReceipts.setText(String.valueOf(index));
        tvOrderName.setText(list.get(index));


    }
}
