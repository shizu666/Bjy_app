package app.wms;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NoSamelibraryMove extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_nslm_xiajia , tv_nslm_shangjia;
    private LinearLayout ll_nslm_shang ,ll_nslm_xia;
    private Button but_nslm_dsj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_samelibrary_move);
        tv_nslm_xiajia = (TextView) findViewById(R.id.tv_nslm_xiajia);
        tv_nslm_shangjia = (TextView) findViewById(R.id.tv_nslm_shangjia);
        ll_nslm_shang = (LinearLayout) findViewById(R.id.ll_nslm_shang);
        ll_nslm_xia = (LinearLayout) findViewById(R.id.ll_nslm_xia);
        but_nslm_dsj = (Button) findViewById(R.id.but_nslm_dsj);

        tv_nslm_shangjia.setBackgroundColor(Color.parseColor("#cccccc"));

        //移库上下架点击事件
        tv_nslm_xiajia.setOnClickListener(this);
        tv_nslm_shangjia.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_nslm_xiajia://移库下架
                ll_nslm_xia.setVisibility(View.VISIBLE);
                ll_nslm_shang.setVisibility(View.GONE);
                but_nslm_dsj.setVisibility(View.GONE);
                tv_nslm_xiajia.setBackgroundColor(Color.parseColor("#ffffff"));
                tv_nslm_shangjia.setBackgroundColor(Color.parseColor("#cccccc"));
                break;
            case R.id.tv_nslm_shangjia://移库上架
                ll_nslm_xia.setVisibility(View.GONE);
                ll_nslm_shang.setVisibility(View.VISIBLE);
                but_nslm_dsj.setVisibility(View.VISIBLE);
                tv_nslm_xiajia.setBackgroundColor(Color.parseColor("#cccccc"));
                tv_nslm_shangjia.setBackgroundColor(Color.parseColor("#ffffff"));
                break;
        }
    }
}
