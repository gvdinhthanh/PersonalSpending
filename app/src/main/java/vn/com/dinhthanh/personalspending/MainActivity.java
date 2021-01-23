package vn.com.dinhthanh.personalspending;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button bt_add_newObjectThuChi;
    private Button bt_view_listThuChi;
    private Button bt_main_view_report_thang;
    private Button bt_main_view_report_nam;
    private Button bt_main_view_report_chung;

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

        bt_main_view_report_thang = findViewById(R.id.bt_main_report_thang);
        bt_main_view_report_thang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityReportThang.class);
                startActivity(intent);
            }
        });
        //ThuChiDBHelper db = new ThuChiDBHelper(this);
        bt_main_view_report_nam = findViewById(R.id.bt_main_report_nam);
        bt_main_view_report_nam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                db.deleteObjectThuChiCuoi();
//                Toast.makeText(MainActivity.this, "Xóa bản ghi cuối!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ActivityReportNam.class);
                startActivity(intent);
            }
        });

        bt_main_view_report_chung = findViewById(R.id.bt_main_report_chung);
        bt_main_view_report_chung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                db.deleteObjectThuChiCuoi();
//                Toast.makeText(MainActivity.this, "Xóa bản ghi cuối!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ActivityReportThuChi.class);
                startActivity(intent);
            }
        });
    }
}