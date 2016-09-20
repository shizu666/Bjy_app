package app.wms.three;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import app.wms.R;
import app.wms.adapter.ListViewAdapter;
import app.wms.empty.XfQingDan;
import app.wms.tow.XiaoFanShangJia;

public class XiaoFanQingDan extends AppCompatActivity {
    private ListView listView;
    private List<XfQingDan> list;
    private XfQingDan xfQingDan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiao_fan_qing_dan);

        list = new ArrayList<XfQingDan>();

        xfQingDan = new XfQingDan();
        xfQingDan.setTv_la_bianhao("商品编码");
        xfQingDan.setTv_la_name("商品名称");
        xfQingDan.setTv_la_dsj("待上架");
        xfQingDan.setTv_la_shuliang("计划数量");
        xfQingDan.setTv_la_zhiliang("质量情况");
        list.add(xfQingDan);
        for(int i = 0;i<10;i++){
            xfQingDan = new XfQingDan();
            xfQingDan.setTv_la_bianhao("1111");
            xfQingDan.setTv_la_name("苹果7777777");
            xfQingDan.setTv_la_dsj("2222");
            xfQingDan.setTv_la_shuliang("1111");
            xfQingDan.setTv_la_zhiliang("好");
            list.add(xfQingDan);
        }
//        listView = (ListView) findViewById(R.id.lv_xfqd);
//        ListViewAdapter adapter = new ListViewAdapter(this,list);
//        listView.setAdapter(adapter);


    }

}
