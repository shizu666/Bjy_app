package app.wms.tow;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.wms.R;
import app.wms.three.XiaoFanQingDan;

public class XiaoFanShangJia extends AppCompatActivity implements View.OnClickListener {
    private Button but_com_dsj , but_com_confirm ,but_com_back ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiao_fan_shang_jia);
        but_com_dsj = (Button) findViewById(R.id.but_com_dsj);
        but_com_confirm = (Button) findViewById(R.id.but_com_confirm);
        but_com_back = (Button) findViewById(R.id.but_com_back);
        but_com_confirm.setOnClickListener(this);
        but_com_dsj.setOnClickListener(this);
        but_com_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.but_com_dsj:
                Intent intent = new Intent(XiaoFanShangJia.this, XiaoFanQingDan.class);
                startActivity(intent);
                break;
            case R.id.but_com_confirm:
                break;
            case R.id.but_com_back:
                XiaoFanShangJia.this.finish();
                break;
        }
    }
}
