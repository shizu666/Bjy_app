package app.wms.three;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import app.wms.R;
import app.wms.adapter.KykqdListViewAdapter;
import app.wms.empty.MyQingDan;
import app.wms.view.MyListView;

public class KuaYiKuQingDan extends AppCompatActivity {

    private MyListView myListView;
    private List<MyQingDan> list;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kua_yi_ku_qing_dan);
        scrollView = (ScrollView) findViewById(R.id.sv_kykqd);
        scrollView.smoothScrollTo(0,0);
        myListView = (MyListView) findViewById(R.id.mlv_kykqd);
        list = new ArrayList<MyQingDan>();

        MyQingDan qd = new MyQingDan();
        qd.setTv_qd_bianhao("商品编号");
        qd.setTv_qd_name("商品名称");
        qd.setTv_qd_shuliang("计划数量");
        qd.setTv_qd_yishangjia("已上架");
        qd.setTv_qd_dsj("待上架");
        list.add(qd);
        for(int i = 0 ;i <10 ;i++){
            MyQingDan qd2 = new MyQingDan();
            qd2.setTv_qd_bianhao("111");
            qd2.setTv_qd_name("当时的方式");
            qd2.setTv_qd_shuliang("222");
            qd2.setTv_qd_yishangjia("44");
            qd2.setTv_qd_dsj("555");
            list.add(qd2);
        }

        KykqdListViewAdapter adapter = new KykqdListViewAdapter(this,list);
        myListView.setAdapter(adapter);


    }
}
