package com.example.a9_19438561_dangnhatkhuong;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class CongViecAdapter extends BaseAdapter {

    private Context context;
    private int idLayout;
    private List<CongViec> list;

    public CongViecAdapter(Context context, int idLayout, List<CongViec> list) {
        this.context = context;
        this.idLayout = idLayout;
        this.list = list;
    }

    @Override
    public int getCount() {
        if(list.size() != 0){
            return  list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(idLayout,viewGroup,false);
        }

        CongViec congViec = list.get(i);

        TextView txtCongViec = view.findViewById(R.id.txtCongViec);
        TextView txtGhiChu = view.findViewById(R.id.txtGhiChu);

        ImageButton btnD = view.findViewById(R.id.btnD);
        ImageButton btnU = view.findViewById(R.id.btnU);

        txtCongViec.setText(congViec.getTenCongViec());
        txtGhiChu.setText(congViec.getGhiChu());

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.getMainActive().delete(congViec.getId());
                MainActivity.getMainActive().updateUI();

            }
        });

        btnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Update.class);
                intent.putExtra("congViec",congViec);
                context.startActivity(intent);
            }
        });


        return view;
    }
}
