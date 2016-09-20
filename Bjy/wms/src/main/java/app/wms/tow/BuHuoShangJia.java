package app.wms.tow;

import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import app.wms.R;

public class BuHuoShangJia extends AppCompatActivity {
    private TextView tv_order_name;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bu_huo_shang_jia);
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        spinner = (Spinner) findViewById(R.id.sp_bhsj);
        String[] strings = this.getResources().getStringArray(R.array.bhsj);
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.adapter_spinner,R.id.tv_spinner,strings);
        spinner.setAdapter(adapter);
        tv_order_name.setText("补货上架");
    }
}
