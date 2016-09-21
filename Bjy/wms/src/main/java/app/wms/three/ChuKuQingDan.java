package app.wms.three;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import app.wms.R;
import app.wms.adapter.CkqdListViewAdapter;
import app.wms.empty.MyQingDan;
import app.wms.inter.MyCallback;
import app.wms.view.MyListView;

public class ChuKuQingDan extends AppCompatActivity implements MyCallback {
    private MyListView myListView;
    private List<MyQingDan> list;
    private MyQingDan myQingDan;
    private CkqdListViewAdapter adapter;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_ku_qing_dan);
        scrollView = (ScrollView) findViewById(R.id.sv_ckqd);
        scrollView.smoothScrollTo(0,0);
        list = new ArrayList<MyQingDan>();
        myQingDan = new MyQingDan();
        myQingDan = new MyQingDan();
        myQingDan.setTv_qd_bianhao("序号");
        myQingDan.setTv_qd_dingdanhao("订单号");
        myQingDan.setTv_qd_caozuo("操作");
        list.add(myQingDan);
        for (int i = 0 ;i<10;i++){
            myQingDan = new MyQingDan();
            myQingDan.setTv_qd_bianhao("序号"+i);
            myQingDan.setTv_qd_dingdanhao("订单号i="+i);
            myQingDan.setTv_qd_caozuo("删除"+i);
            list.add(myQingDan);
        }

        myListView = (MyListView) findViewById(R.id.mlv_ckqd);
        adapter = new CkqdListViewAdapter(this,list,this);
        myListView.setAdapter(adapter);


    }

    @Override
    public void itemClick(View view) {

        switch (view.getId()){
            case R.id.tv_qd_ck3:
                int position = (int) view.getTag();
                if(position!=0){
                    ProgressDialog pd = new ProgressDialog(this);
                    pd.setMessage("删除中，请等待...");
                    pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    pd.show();
                    list.remove(position);
                    adapter.notifyDataSetChanged();
                    pd.hide();
                }
        }

    }
}
