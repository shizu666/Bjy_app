package app.wms.three;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.wms.R;
import app.wms.adapter.BhqdListViewAdapter;
import app.wms.empty.MyQingDan;
import app.wms.view.MyListView;

public class BuHuoQingDan extends AppCompatActivity {
    private TextView tv_order_name;
    private MyQingDan myQingDan;
    private List<MyQingDan> list;
    private ScrollView scrollView;
    private MyListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bu_huo_qing_dan);
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        myListView = (MyListView) findViewById(R.id.mlv_bhqd);
        scrollView = (ScrollView) findViewById(R.id.sv_bhqd);
        scrollView.smoothScrollTo(0,0);
        tv_order_name.setText("补货清单");
        list = new ArrayList<MyQingDan>();
        myQingDan = new MyQingDan();
        myQingDan.setTv_qd_bianhao("商品编号");
        myQingDan.setTv_qd_name("商品名称");
        myQingDan.setTv_qd_shuliang("计划数量");
        myQingDan.setTv_qd_dsj("待上架");
        list.add(myQingDan);
        for (int i =0 ; i < 10 ; i++){
            myQingDan = new MyQingDan();
            myQingDan.setTv_qd_bianhao("1111");
            myQingDan.setTv_qd_name("一个亿");
            myQingDan.setTv_qd_shuliang("100000");
            myQingDan.setTv_qd_dsj("2000");
            list.add(myQingDan);
        }

        BhqdListViewAdapter adapter = new BhqdListViewAdapter(this,list);
        myListView.setAdapter(adapter);


    }
}
