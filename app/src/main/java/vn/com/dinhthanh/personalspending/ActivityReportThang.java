package vn.com.dinhthanh.personalspending;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ActivityReportThang extends AppCompatActivity {

    private String arrayThang[]={"Chọn tháng","Tháng 01","Tháng 02","Tháng 03","Tháng 04","Tháng 05","Tháng 06",
            "Tháng 07","Tháng 08","Tháng 09","Tháng 10","Tháng 11","Tháng 12"};
    private List<String> listThang = new ArrayList<String>();

    private String thang;
    private Spinner spinnerThang;
    private Button bt_XemReportThang;
    private TextView lb_reportThang_Thu;
    private TextView lb_reportThang_Chi;
    private TextView textView_reportThang_SoTienThu;
    private TextView textView_reportThang_SoTienChi;
    private ThuChiDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("BÁO CÁO THU-CHI TRONG THÁNG");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_report_thang);
        khoiTaoBien();
        db = new ThuChiDBHelper(this);
        bt_XemReportThang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int soTienThu = 0;
                soTienThu = db.getTongSoThuThang(thang);
                String soTienThuS = String.format("%,d",soTienThu);
                Log.d("Thang: ",thang);
                Log.d("So tien Thu: ", soTienThuS);
                lb_reportThang_Thu.setText("Số tiền thu: ");
                textView_reportThang_SoTienThu.setText(soTienThuS + " VND");

                int soTienChi = 0;
                soTienChi = db.getTongSoChi();
                String soTienChiS = String.format("%,d",soTienChi);
                Log.d("So tien Chi: ", soTienChiS);
                lb_reportThang_Chi.setText("Số tiền chi: ");
                textView_reportThang_SoTienChi.setText(soTienChiS + " VND");

                Toast.makeText(ActivityReportThang.this, "Click báo cáo!", Toast.LENGTH_SHORT).show();
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

        bt_XemReportThang = findViewById(R.id.button_xemReportThang);
        lb_reportThang_Thu = findViewById(R.id.lb_reportThang_Thu);
        textView_reportThang_SoTienThu = findViewById(R.id.textView_reportThang_SoTienThu);
        lb_reportThang_Chi = findViewById(R.id.lb_reportThang_Chi);
        textView_reportThang_SoTienChi = findViewById(R.id.textView_reportThang_SoTienChi);

        spinnerThang = (Spinner) findViewById(R.id.spinnerChonThang);
        ArrayAdapter<String> adapterThang=new ArrayAdapter<String>(this,R.layout.spinner_item_ndthanh,arrayThang);
        adapterThang.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerThang.setAdapter(adapterThang);
        spinnerThang.setOnItemSelectedListener(new MyProcessEventThang());

    }

    private class MyProcessEventThang implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 1:
                    thang = "01";
                    break;
                case 2:
                    thang = "02";
                    break;
                case 3:
                    thang = "03";
                    break;
                case 4:
                    thang = "04";
                    break;
                case 5:
                    thang = "05";
                    break;
                case 6:
                    thang = "06";
                    break;
                case 7:
                    thang = "07";
                    break;
                case 8:
                    thang = "08";
                    break;
                case 9:
                    thang = "09";
                    break;
                case 10:
                    thang = "10";
                    break;
                case 11:
                    thang = "11";
                case 12:
                    thang = "12";
                    break;
                default:
                    thang = "";
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            thang = "";
        }
    }
}