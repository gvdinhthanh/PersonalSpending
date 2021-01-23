package vn.com.dinhthanh.personalspending;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bt_add_newObjectThuChi;
    private Button bt_view_listThuChi;
    private Button bt_main_view_report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        getSupportActionBar().setTitle("QUẢN LÝ THU - CHI CÁ NHÂN");
        setContentView(R.layout.activity_main);

        bt_add_newObjectThuChi = findViewById(R.id.bt_main_add);
        bt_add_newObjectThuChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityAddObjectThuChi.class);
                startActivity(intent);
            }
        });

        bt_view_listThuChi = findViewById(R.id.bt_main_view_list_thu_chi);
        bt_view_listThuChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityViewListThuChi.class);
                startActivity(intent);
            }
        });

        bt_main_view_report = findViewById(R.id.bt_main_report);
        bt_main_view_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityReportThuChi.class);
                startActivity(intent);
            }
        });
    }
}