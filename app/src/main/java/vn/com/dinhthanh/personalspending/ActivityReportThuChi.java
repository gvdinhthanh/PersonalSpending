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

public class ActivityReportThuChi extends AppCompatActivity {

    private TextView lb_reportThang_Thu;
    private TextView lb_reportThang_Chi;
    private TextView textView_reportThang_SoTienThu;
    private TextView textView_reportThang_SoTienChi;
    private ThuChiDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("BÁO CÁO THU-CHI");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_report_thu_chi);
        khoiTaoBien();
        db = new ThuChiDBHelper(this);
        int soTienThu = 0;
        soTienThu = db.getTongSoThu();
        String soTienThuS = String.format("%,d",soTienThu);
        Log.d("So tien Thu: ", soTienThuS);
        lb_reportThang_Thu.setText("Số tiền thu: ");
        textView_reportThang_SoTienThu.setText(soTienThuS + " VND");

        int soTienChi = 0;
        soTienChi = db.getTongSoChi();
        String soTienChiS = String.format("%,d",soTienChi);
        Log.d("So tien Chi: ", soTienChiS);
        lb_reportThang_Chi.setText("Số tiền chi: ");
        textView_reportThang_SoTienChi.setText(soTienChiS + " VND");

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void khoiTaoBien(){

        lb_reportThang_Thu = findViewById(R.id.lb_reportThang_Thu);
        textView_reportThang_SoTienThu = findViewById(R.id.textView_reportThang_SoTienThu);
        lb_reportThang_Chi = findViewById(R.id.lb_reportThang_Chi);
        textView_reportThang_SoTienChi = findViewById(R.id.textView_reportThang_SoTienChi);
    }
}