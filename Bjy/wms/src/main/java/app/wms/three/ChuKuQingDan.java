package app.wms.three;

import android.app.Application;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import app.wms.CustomApplication;
import app.wms.R;
import app.wms.adapter.CkqdListViewAdapter;
import app.wms.empty.MyQingDan;
import app.wms.inter.MyCallback;
import app.wms.tool.Others;
import app.wms.view.MyListView;

public class ChuKuQingDan extends AppCompatActivity implements MyCallback {
    private MyListView myListView;
    private List<MyQingDan> list;
    private List<String> l;
    private MyQingDan myQingDan;
    private CkqdListViewAdapter adapter;
    private ScrollView scrollView;
    private Button but_ckqd_back;
    private CustomApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_ku_qing_dan);
        app = (CustomApplication) getApplication();
        Bundle bundle = getIntent().getExtras();
        l = bundle.getStringArrayList("order");
        scrollView = (ScrollView) findViewById(R.id.sv_ckqd);
        but_ckqd_back = (Button) findViewById(R.id.but_ckqd_back);
        scrollView.smoothScrollTo(0,0);
        list = new ArrayList<MyQingDan>();
        myQingDan = new MyQingDan();
        myQingDan = new MyQingDan();
        myQingDan.setTv_qd_bianhao("序号");
        myQingDan.setTv_qd_dingdanhao("订单号");
        myQingDan.setTv_qd_caozuo("操作");
        list.add(myQingDan);

       // configAdapter();
        for (int i = 0 ;i<l.size();i++){
            myQingDan = new MyQingDan();
            myQingDan.setTv_qd_bianhao((i+1)+"");
            myQingDan.setTv_qd_dingdanhao(l.get(i));
            myQingDan.setTv_qd_caozuo("删除");
            list.add(myQingDan);
        }

        myListView = (MyListView) findViewById(R.id.mlv_ckqd);
        adapter = new CkqdListViewAdapter(this,list,this);
        myListView.setAdapter(adapter);


        but_ckqd_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChuKuQingDan.this.finish();
            }
        });

    }

    @Override
    public void itemClick(View view) {

        switch (view.getId()){
            case R.id.tv_qd_ck3:
                int position = (int) view.getTag();
                if(position!=0){
                    list.remove(position);
                    l.remove(position-1);
                    app.setList(l);
                    //configAdapter();
                    adapter.notifyDataSetChanged();
                }
        }
    }

    private void configAdapter(){
        for (int i = 0 ;i<l.size();i++){
            myQingDan = new MyQingDan();
            myQingDan.setTv_qd_bianhao((i+1)+"");
            myQingDan.setTv_qd_dingdanhao(l.get(i));
            myQingDan.setTv_qd_caozuo("删除");
            list.add(myQingDan);
        }

        myListView = (MyListView) findViewById(R.id.mlv_ckqd);
        adapter = new CkqdListViewAdapter(this,list,this);
        myListView.setAdapter(adapter);
    }



}
