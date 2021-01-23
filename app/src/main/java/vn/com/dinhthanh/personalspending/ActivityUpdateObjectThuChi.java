package vn.com.dinhthanh.personalspending;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ActivityUpdateObjectThuChi extends AppCompatActivity {

    final Calendar myCalendar = Calendar.getInstance();
    private EditText editTextTieuDe;
    private EditText editTextMoTa;
    private EditText editTextSoTien;
    private EditText editTextNgayThucHien;
    private RadioButton radio_update_thutien;
    private RadioButton radio_update_chitien;

    private Button btUpdateThuChi;
    private ThuChiDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("CẬP NHẬT ĐỐI TƯỢNG THU-CHI");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_edit_thu_chi);

        db = new ThuChiDBHelper(this);

        editTextTieuDe = findViewById(R.id.editTextUpdateTieuDe);
        editTextMoTa = findViewById(R.id.editTextUpdateMoTa);
        editTextSoTien = findViewById(R.id.editTextUpdateSoTien);
        editTextNgayThucHien = findViewById(R.id.editTextUpdateNgayThucHien);
        radio_update_chitien = findViewById(R.id.radio_update_chitien);
        radio_update_thutien = findViewById(R.id.radio_update_thutien);
        btUpdateThuChi = findViewById(R.id.buttonUpdateThuChi);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        Intent myIntent = getIntent();
        int id = myIntent.getIntExtra("id", -1);

        int doiTuong = myIntent.getIntExtra("doituong", -1);
        if(doiTuong==-1){
            radio_update_chitien.setChecked(true);
        }
        else{
            radio_update_thutien.setChecked(true);
        }

        String tieuDe = myIntent.getStringExtra("tieude");
        editTextTieuDe.setText(tieuDe);

        String moTa = myIntent.getStringExtra("mota");
        editTextMoTa.setText(moTa);

        int soTien = myIntent.getIntExtra("sotien", -1);
        editTextSoTien.setText(soTien);

        String ngayThucHienS = myIntent.getStringExtra("ngaythuchien");
        Date ngayThucHienD = null;
        try {
            ngayThucHienD = new SimpleDateFormat("dd/MM/yyyy").parse(ngayThucHienS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        editTextNgayThucHien.setText(ngayThucHienD.toString());

        editTextNgayThucHien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ActivityUpdateObjectThuChi.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btUpdateThuChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newDoiTuong = -1;
                if(radio_update_thutien.isChecked()){
                    newDoiTuong = 1;
                }
                String newTieuDe = editTextTieuDe.getText().toString().trim();
                String newMoTa = editTextMoTa.getText().toString().trim();
                String newSoTienS = editTextSoTien.getText().toString().trim();
                int newSoTien = Integer.parseInt(newSoTienS);

                String NgayThucHien = editTextNgayThucHien.getText().toString().trim();
                String newNgayThucHien = NgayThucHien.substring(6,9) + "/" + NgayThucHien.substring(3,4) + "/" + NgayThucHien.substring(0,1);

                Boolean result = db.updateDoiTuongThuChi(new ObjectThuChi(id, newDoiTuong, newTieuDe, newMoTa, newSoTien, newNgayThucHien));
                if (result == true) {
                    Toast.makeText(ActivityUpdateObjectThuChi.this, "Đã cập nhật thành công", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(ActivityUpdateObjectThuChi.this, "Thất bại, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editTextNgayThucHien.setText(sdf.format(myCalendar.getTime()));
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
}