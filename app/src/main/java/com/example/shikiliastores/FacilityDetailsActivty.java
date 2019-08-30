package com.example.shikiliastores;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

public class FacilityDetailsActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_details_activty);

        getIncomingIntent();
    }
    private void getIncomingIntent(){
        String Image=getIntent().getStringExtra("Image");
        String Name=getIntent().getStringExtra("Name");
        String Phone=getIntent().getStringExtra("Phone");
        String Location=getIntent().getStringExtra("Location");
        String Capacity=getIntent().getStringExtra("Capacity");
        String Price=getIntent().getStringExtra("Price");

        setImage(Image,Name,Phone,Location,Capacity,Price);
        }

        private void setImage(String Image, String Name, String Phone, String Location, String Capacity, String Price){

            TextView name=findViewById(R.id.detailsName);
            name.setText("Name:  "+Name);
            TextView phone=findViewById(R.id.detailsPhone);
            phone.setText("Phone Number:  "+Phone);
            TextView location=findViewById(R.id.detailsLocation);
            location.setText("Location:  "+Location);
            TextView capacity=findViewById(R.id.detailsCapacity);
            capacity.setText("Capacity:  "+Capacity);
            TextView price=findViewById(R.id.detailsPrice);
            price.setText("Price:  "+Price);

            ImageView image=findViewById(R.id.details_img);

            byte[] decodedString= Base64.decode(Image, Base64.DEFAULT);
            Bitmap decodedByte= BitmapFactory.decodeByteArray(decodedString, 0,decodedString.length);
            image.setImageBitmap(decodedByte);
        }





}

