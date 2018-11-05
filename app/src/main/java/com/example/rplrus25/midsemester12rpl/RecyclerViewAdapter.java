package com.example.rplrus25.midsemester12rpl;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{
    private ArrayList<ItemObject> ItemObjectArrayList;
    Context context;

    public RecyclerViewAdapter(Context context, ArrayList<ItemObject> ItemObjectArrayList) {
        this.ItemObjectArrayList = ItemObjectArrayList;
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
       final ItemObject ItemObject= ItemObjectArrayList.get(position);
        Glide.with(context)
                .load(ItemObject.getGambar())
                .into(holder.ic_launcher);

        holder.txtname.setText(ItemObjectArrayList.get(position).getName());
        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = ItemObjectArrayList.get(position).getName();
                final String deskripsi = ItemObjectArrayList.get(position).getDeskripsi();
                final String gambar = ItemObjectArrayList.get(position).getGambar();
                final String id = ItemObjectArrayList.get(position).getId();
                Intent i = new Intent(context.getApplicationContext(),Detail_Name.class);
                i.putExtra("name" , name);
                i.putExtra("deskripsi" , deskripsi);
                i.putExtra("gambar" , gambar);
                i.putExtra("id" , id);
                context.startActivity(i);

            }
        });
        holder.btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, ItemObject.getName() + "\n"+ItemObject.getDeskripsi());
                intent.setType("text/plain");
                context.startActivity(intent);
                Toast.makeText(context, "Segera Datang", Toast.LENGTH_SHORT).show();
            }
        });
//        holder.btndelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder= new AlertDialog.Builder(context);
//                builder.setTitle("DELETE");
//                builder.setCancelable(true);
//                builder.setMessage("Confirm that you want to delete this bookmark?")
//                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
//
//                            @Override
//                            public void onClick(DialogInterface dialog, int i) {
//
////                                    deleteNote(position);
//                            }
//                        });
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int i) {
//                        dialog.dismiss();
//                    }
//                });
//                AlertDialog alert = builder.create();
//                alert.show();
//
//            }
//        });
//

    }

    @Override
    public int getItemCount() {
        return ItemObjectArrayList.size();
    }
}
