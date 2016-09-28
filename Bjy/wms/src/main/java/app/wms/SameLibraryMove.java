package app.wms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SameLibraryMove extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_order_name , tv_slm_name ,tv_slm_unit;
    private EditText et_slm_yuanhuowei ,et_slm_mudihuowei ,et_slm_sku ,et_slm_num;
    private Button but_confirm ,but_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_same_library_move);
        initView();
        tv_order_name.setText("同区移库");

    }

    private void initView() {
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        tv_slm_name = (TextView) findViewById(R.id.tv_slm_name);
        tv_slm_unit = (TextView) findViewById(R.id.tv_slm_unit);
        et_slm_mudihuowei = (EditText) findViewById(R.id.et_slm_mudihuowei);
        et_slm_yuanhuowei = (EditText) findViewById(R.id.et_slm_yuanhuowei);
        et_slm_sku = (EditText) findViewById(R.id.et_slm_sku);
        et_slm_num = (EditText) findViewById(R.id.et_slm_num);

        but_back = (Button) findViewById(R.id.but_back);
        but_confirm = (Button) findViewById(R.id.but_confirm);

        but_confirm.setOnClickListener(this);
        but_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())){
            case R.id.but_back:
                this.finish();
                break;
            case R.id.but_confirm:

                break;
        }

    }
}
