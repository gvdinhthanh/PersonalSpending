package vn.com.dinhthanh.personalspending;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<ObjectThuChi> objectThuChi;

    public MyAdapter(Context context, ArrayList<ObjectThuChi> objectThuChi) {
        this.context = context;
        this.objectThuChi = objectThuChi;
    }

    @Override
    public int getCount() {
        return objectThuChi.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_list_view, null);
        }

        TextView textViewTieuDe = convertView.findViewById(R.id.textViewListTieuDe);
        TextView textViewMoTa = convertView.findViewById(R.id.textViewListMoTa);
        TextView textViewSoTien = convertView.findViewById(R.id.textViewListSoTien);
        TextView textViewNgayThucHien = convertView.findViewById(R.id.textViewListNgayThucHien);

        Button buttonUpdate = convertView.findViewById(R.id.button_Edit);
        Button buttonDelete = convertView.findViewById(R.id.button_Delete);

        int doiTuong = objectThuChi.get(position).getDoiTuong();
        textViewTieuDe.setText(objectThuChi.get(position).getTieuDe());
        textViewMoTa.setText(objectThuChi.get(position).getMoTa());
        int soTien = objectThuChi.get(position).getSoTien();

        String SoTienS = String.format("%,d",soTien);

        textViewSoTien.setText(SoTienS);

        if(doiTuong == -1){
            textViewTieuDe.setTextColor(context.getResources().getColor(R.color.maudo));
            textViewSoTien.setTextColor(context.getResources().getColor(R.color.maudo));
        }
        else{
            textViewTieuDe.setTextColor(context.getResources().getColor(R.color.mauxanhnuocbien));
            textViewSoTien.setTextColor(context.getResources().getColor(R.color.mauxanhnuocbien));
        }

        textViewNgayThucHien.setText(objectThuChi.get(position).getNgayThucHien());

        ObjectThuChi selectObjectThuChi = objectThuChi.get(position);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityUpdateObjectThuChi.class);
                intent.putExtra("id", selectObjectThuChi.getId());
                intent.putExtra("doituong", selectObjectThuChi.getDoiTuong());
                intent.putExtra("tieude", selectObjectThuChi.getTieuDe());
                intent.putExtra("mota", selectObjectThuChi.getMoTa());
                intent.putExtra("sotien", selectObjectThuChi.getSoTien());
                intent.putExtra("ngaythuchien", selectObjectThuChi.getNgayThucHien());
                ((Activity) context).startActivityForResult(intent, 2409);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ThuChiDBHelper db = new ThuChiDBHelper(context);
                        Boolean result = db.deleteObjectThuChi(selectObjectThuChi.getId());
                        if (result == true) {
                            Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                            delete(position);
                        } else {
                            Toast.makeText(context, "Thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        return convertView;
    }

    public void delete(int position){
        objectThuChi.remove(position);
        this.notifyDataSetChanged();
    }
}
