package com.example.rplrus25.midsemester12rpl;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rplrus25.midsemester12rpl.database.MahasiswaModel;

import java.util.ArrayList;
import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private ArrayList<MahasiswaModel> mahasiswaModelArrayList;
    Context context;

    public ModelAdapter(Context context, ArrayList<MahasiswaModel> mahasiswaModelArrayList) {
        this.mahasiswaModelArrayList = mahasiswaModelArrayList;
        this.context = context;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
        RecyclerViewHolder rcv = new RecyclerViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        final MahasiswaModel ItemObject= mahasiswaModelArrayList.get(position);
        Glide.with(context)
                .load(ItemObject.getUrl())
                .into(holder.ic_launcher);

        holder.txtname.setText(mahasiswaModelArrayList.get(position).getName());
        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = mahasiswaModelArrayList.get(position).getName();
                final String deskripsi = mahasiswaModelArrayList.get(position).getNim();
                final String gambar = mahasiswaModelArrayList.get(position).getUrl();
                final String id = String.valueOf(mahasiswaModelArrayList.get(position).getId());
                Intent i = new Intent(context.getApplicationContext(),Detail_Name.class);
                i.putExtra("name" , name);
                i.putExtra("deskripsi" , deskripsi);
                i.putExtra("gambar" , gambar);
                i.putExtra("id" , id);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });
        holder.btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, ItemObject.getName() + "\n"+ItemObject.getNim());
                intent.setType("text/plain");
                context.startActivity(intent);
                Toast.makeText(context, "Segera Datang", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mahasiswaModelArrayList.size();
    }


}

