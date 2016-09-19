package app.wms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class Order extends AppCompatActivity {
    private TextView et_order_showReceipts ,tv_order_name;
    private Button but_order_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        but_order_back = (Button) findViewById(R.id.but_order_back);
        et_order_showReceipts = (TextView) findViewById(R.id.et_order_showReceipts);
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);

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

        Bundle bundle = this.getIntent().getExtras();
        final int index = bundle.getInt("index");
        tv_order_name.setText(list.get(index));

        //对单据号进行监听，有内容就自动跳转到订单详情页
        et_order_showReceipts.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null){
                    Intent intent = new Intent(Order.this,OrderDetails.class);
                    Bundle bd = new Bundle();
                    bd.putCharSequence("order",s);
                    bd.putInt("index",index);
                    intent.putExtras(bd);
                    startActivity(intent);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //返回
        but_order_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order.this.finish();
            }
        });


    }




}
