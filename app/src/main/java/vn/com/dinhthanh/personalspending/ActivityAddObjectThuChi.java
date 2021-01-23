package vn.com.dinhthanh.personalspending;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ActivityAddObjectThuChi extends AppCompatActivity {

    final Calendar myCalendar = Calendar.getInstance();

    private ArrayList<ObjectThuChi> listObjectThuChi;
    private ThuChiDBHelper db;
    private MyAdapter adapter;

    private Button buttonAddThuChi;
    private EditText editTextTieuDe;
    private EditText editTextMoTa;
    private EditText editTextSoTien;
    private EditText editTextNgayThucHien;
    private RadioButton radio_chiTien;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("THÊM ĐỐI TƯỢNG THU-CHI");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_object_thu_chi);

        buttonAddThuChi = findViewById(R.id.buttonAddThuChi);
        editTextTieuDe = findViewById(R.id.editTextAddTieuDe);
        editTextMoTa = findViewById(R.id.editTextAddMoTa);
        editTextSoTien = findViewById(R.id.editTextAddSoTien);
        editTextNgayThucHien = findViewById(R.id.editTextAddNgayThucHien);
        radio_chiTien = findViewById(R.id.radio_add_chitien);

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

        editTextNgayThucHien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ActivityAddObjectThuChi.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        listObjectThuChi = new ArrayList<ObjectThuChi>();
        adapter = new MyAdapter(ActivityAddObjectThuChi.this, listObjectThuChi);
        db = new ThuChiDBHelper(this);

        buttonAddThuChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tieuDe = editTextTieuDe.getText().toString().trim();
                String moTa = editTextMoTa.getText().toString().trim();
                String ngayThucHien = editTextNgayThucHien.getText().toString().trim();
                String ngay = ngayThucHien.substring(0,1);
                String thang = ngayThucHien.substring(3,4);
                String nam = ngayThucHien.substring(6,9);
                String newNgayThucHien = nam + "/" + thang + "/" + ngay;
                String soTienString = editTextSoTien.getText().toString().trim();
                int soTien = Integer.parseInt(soTienString);
                int doiTuong = 1;
                if(radio_chiTien.isChecked()){
                    doiTuong = -1;
                }

                if (tieuDe.isEmpty()) {
                    Toast.makeText(ActivityAddObjectThuChi.this, "Vui lòng nhập tiêu đề!", Toast.LENGTH_SHORT).show();
                } else if (moTa.isEmpty()) {
                    Toast.makeText(ActivityAddObjectThuChi.this, "Vui lòng nhập mô tả nội dung!", Toast.LENGTH_SHORT).show();
                    } else {
                        if(soTien == 0){
                            Toast.makeText(ActivityAddObjectThuChi.this, "Vui lòng nhập số tiền!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(newNgayThucHien.isEmpty()){
                                Toast.makeText(ActivityAddObjectThuChi.this, "Vui lòng nhập ngày thực hiện!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(ActivityAddObjectThuChi.this, "Vui lòng nhập ngày thực hiện!", Toast.LENGTH_SHORT).show();
                                Log.d("Ngay thuc hien: ",ngayThucHien);
                                Log.d("New ngay: ",newNgayThucHien);
                                ObjectThuChi newObjectThuChi = new ObjectThuChi(doiTuong, tieuDe, moTa, soTien, newNgayThucHien);
                                if (db.themDoiTuongThuChi(newObjectThuChi)) {
                                    listObjectThuChi.add(newObjectThuChi);
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(ActivityAddObjectThuChi.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                                    setTextEmpty();
                                }
                            }
                        }

                }
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editTextNgayThucHien.setText(sdf.format(myCalendar.getTime()));
    }

    private void setTextEmpty(){
        editTextTieuDe.setText("");
        editTextMoTa.setText("");
        editTextSoTien.setText("");
        editTextNgayThucHien.setText("");
        radio_chiTien.setChecked(true);
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


}
