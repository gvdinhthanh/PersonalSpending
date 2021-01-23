package vn.com.dinhthanh.personalspending;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ActivityReportNam extends AppCompatActivity {

    private String arrayNam[]={"Chọn năm","Năm 2020","Năm 2021","Năm 2022"};
    private List<String> listNam = new ArrayList<String>();

    private Spinner spinnerNam;

    private String nam;

    private Button bt_XemReportNam;
    private TextView lb_reportNam_Thu;
    private TextView lb_reportNam_Chi;
    private TextView textView_reportNam_SoTienThu;
    private TextView textView_reportNam_SoTienChi;
    private ThuChiDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("THU-CHI TRONG NĂM");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_report_nam);
        khoiTaoBien();
        db = new ThuChiDBHelper(this);
        bt_XemReportNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nam == ""){
                    Toast.makeText(ActivityReportNam.this, "Chưa chọn năm! Vui lòng chọn Năm!", Toast.LENGTH_SHORT).show();
                }
                else{
                    int soTienThu = 0;
                    soTienThu = db.getTongSoThuNam(nam);
                    String soTienThuS = String.format("%,d",soTienThu);
                    lb_reportNam_Thu.setText("Số tiền thu: ");
                    textView_reportNam_SoTienThu.setText(soTienThuS + " VND");

                    int soTienChi = 0;
                    soTienChi = db.getTongSoChiNam(nam);
                    String soTienChiS = String.format("%,d",soTienChi);
                    lb_reportNam_Chi.setText("Số tiền chi: ");
                    textView_reportNam_SoTienChi.setText(soTienChiS + " VND");
                }
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void khoiTaoBien(){
        bt_XemReportNam = findViewById(R.id.button_xemReportNam);
        lb_reportNam_Thu = findViewById(R.id.lb_reportNam_Thu);
        textView_reportNam_SoTienThu = findViewById(R.id.textView_reportNam_SoTienThu);
        lb_reportNam_Chi = findViewById(R.id.lb_reportNam_Chi);
        textView_reportNam_SoTienChi = findViewById(R.id.textView_reportNam_SoTienChi);

        spinnerNam = (Spinner) findViewById(R.id.spinnerChonTheoNam);
        ArrayAdapter<String> adapterNam=new ArrayAdapter<String>(this,R.layout.spinner_item_ndthanh,arrayNam);
        adapterNam.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerNam.setAdapter(adapterNam);
        spinnerNam.setOnItemSelectedListener(new MyProcessEventNam());

    }

    private class MyProcessEventNam implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 1:
                    nam = "2020";
                    break;
                case 2:
                    nam = "2021";
                    break;
                case 3:
                    nam = "2022";
                    break;

                default:
                    nam = "";
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            nam = "";
        }
    }
}