package com.example.rplrus25.midsemester12rpl;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecycleViewAdapterRetro extends RecyclerView.Adapter<RecyclerViewHolder> {
    private ArrayList<ItemObject2> ItemObjectArrayList;
    Context context;

    public RecycleViewAdapterRetro(Context context, ArrayList<Result> ItemObjectArrayList) {
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
        final ItemObject2 ItemObject= ItemObjectArrayList.get(position);
        Glide.with(context)
                .load(only_url.url+ItemObject.getPosterPath())
                .into(holder.ic_launcher);

        holder.txtname.setText(ItemObjectArrayList.get(position).getTitle());
        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = ItemObjectArrayList.get(position).getTitle();
                final String deskripsi = ItemObjectArrayList.get(position).getOverview();
                final String gambar = ItemObjectArrayList.get(position).getPosterPath();
//                final String id = ItemObjectArrayList.get(position).getId();
                Intent i = new Intent(context.getApplicationContext(),Detail_Name.class);
                i.putExtra("name" , name);
                i.putExtra("deskripsi" , deskripsi);
                i.putExtra("gambar" , gambar);
//                i.putExtra("id" , id);
                context.startActivity(i);

            }
        });
        holder.btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,"this is my application");
                intent.setType("text/plain");
                context.startActivity(intent);
                Toast.makeText(context, "Segera Datang", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle("DELETE");
                builder.setCancelable(true);
                builder.setMessage("Confirm that you want to delete this bookmark?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                                ItemObjectArrayList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemChanged(position,ItemObjectArrayList.size());

                            }
                        });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return ItemObjectArrayList.size();
    }
}
