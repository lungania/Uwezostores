package com.example.shikiliastores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private List<Facility> mData= new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(List<Facility> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item, parent, false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.facilityName.setText(mData.get(position).getName());
        holder.facilityLocation.setText(mData.get(position).getLocation());
        byte[] decodedString= Base64.decode(mData.get(position).getImage(), Base64.DEFAULT);
        Bitmap decodedByte= BitmapFactory.decodeByteArray(decodedString, 0,decodedString.length);
        holder.image.setImageBitmap(decodedByte);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,FacilityDetailsActivty.class);
                intent.putExtra("Image",mData.get(position).getImage());
                intent.putExtra("Name",mData.get(position).getName());
                intent.putExtra("Phone",mData.get(position).getPhoneNo());
                intent.putExtra("Location",mData.get(position).getLocation());
                intent.putExtra("Capacity",mData.get(position).getCapacity());
                intent.putExtra("Price",mData.get(position).getPrice());
                mContext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView facilityName;
        TextView facilityLocation;
        Context mcontext;
        CardView parentLayout;


        public ViewHolder(@NonNull View itemView ) {
            super(itemView);
            image=itemView.findViewById(R.id.image_id);
            facilityName=itemView.findViewById(R.id.facilityName);
            facilityLocation=itemView.findViewById(R.id.facilityLocation);
            parentLayout=itemView.findViewById(R.id.parentLayout);


        }



    }
}
