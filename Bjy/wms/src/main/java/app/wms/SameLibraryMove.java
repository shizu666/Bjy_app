package app.wms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SameLibraryMove extends AppCompatActivity {
    private TextView tv_order_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_same_library_move);
        tv_order_name = (TextView) findViewById(R.id.tv_order_name);
        tv_order_name.setText("同区移库");

    }
}
