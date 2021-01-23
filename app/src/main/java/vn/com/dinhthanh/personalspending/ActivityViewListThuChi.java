package vn.com.dinhthanh.personalspending;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActivityViewListThuChi extends AppCompatActivity {

    private ListView listViewThuChi;
    private Button updateButton;
    private Button deleteButton;
    private int idKhoa;

    private ArrayList<ObjectThuChi> objectThuChi;
    private ThuChiDBHelper db;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("QUẢN LÝ ĐỐI TƯỢNG THU-CHI");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_view_list_thu_chi);

        listViewThuChi = findViewById(R.id.listViewThuChi);
        objectThuChi = new ArrayList<ObjectThuChi>();
        try {
            loadListThuChi();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapter = new MyAdapter(ActivityViewListThuChi.this, objectThuChi);
        listViewThuChi.setAdapter(adapter);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2409 && resultCode == RESULT_OK) {
            try {
                loadListThuChi();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            adapter.notifyDataSetChanged();
        }
    }

    private void loadListThuChi() throws ParseException {
        objectThuChi.clear();
        db = new ThuChiDBHelper(this);
        Cursor cursor = db.getListThuChi();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                int doiTuong = cursor.getInt(1);
                String tieuDe = cursor.getString(2);
                String moTa = cursor.getString(3);
                int soTien = cursor.getInt(4);
                String ngayThucHien = cursor.getString(5);
                ObjectThuChi newObjectThuChi = new ObjectThuChi(id, doiTuong, tieuDe, moTa, soTien, ngayThucHien);
                objectThuChi.add(newObjectThuChi);
            }
        }
    }
}