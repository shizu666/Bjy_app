package app.wms.tow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import app.wms.R;


public class XiaoTuiYanShou extends AppCompatActivity {
    private Spinner spinner;
    private TextView tv_order_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiao_tui_yan_shou);
        spinner = (Spinner) findViewById(R.id.sp_xtys);
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);

        String[] spinnerList = this.getResources().getStringArray(R.array.posun);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.adapter_spinner,R.id.tv_spinner,spinnerList);
        spinner.setAdapter(adapter);

        String orderNum = this.getIntent().getStringExtra("order");
        tv_order_name.setText("销退验收"+"("+orderNum+")");

    }
}
