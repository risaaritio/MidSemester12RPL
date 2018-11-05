package com.example.rplrus25.midsemester12rpl;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView txtname;
    public ImageView ic_launcher;
    public Button btn_detail;
    public Button btn_share;
    public Button btndelete;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        txtname = (TextView)itemView.findViewById(R.id.txtname);
        ic_launcher = (ImageView) itemView.findViewById(R.id.ic_launcher);
        btn_detail = (Button)itemView.findViewById(R.id.btn_detail);
        btn_share = (Button) itemView.findViewById(R.id.btn_share);
        btndelete = (Button) itemView.findViewById(R.id.btndelete);

    }
}
