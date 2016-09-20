package app.wms.tow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import app.wms.R;

public class BuHuoXiaJia extends AppCompatActivity {
    private Spinner spinner;
    private TextView tv_order_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bu_huo_xia_jia);
        spinner = (Spinner) findViewById(R.id.sp_bhxj);
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        String[] strings = this.getResources().getStringArray(R.array.bhxj);
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.adapter_spinner,R.id.tv_spinner,strings);
        spinner.setAdapter(adapter);
        tv_order_name.setText("补货下架");
    }
}
