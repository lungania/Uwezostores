package com.example.shikiliastores;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FacilityActivity extends AppCompatActivity {

    EditText name,phoneno,location,capacity,price_range,category;
    Button btnFacility,btnPhoto;
    DatabaseReference reff;
    Facility facility;
    ImageView img;
    StorageReference mStorageref;
    public Uri imguri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility);

        name=(EditText)findViewById(R.id.etName);
        phoneno=(EditText)findViewById(R.id.etPhoneNo);
        location=(EditText)findViewById(R.id.etLocation);
        capacity=(EditText)findViewById(R.id.etCapacity);
        price_range=(EditText)findViewById(R.id.etPrice);
        category=(EditText)findViewById(R.id.etCategory);
        img=(ImageView)findViewById(R.id.imgFacility);
        btnFacility=(Button) findViewById(R.id.btnFacility);
        mStorageref= FirebaseStorage.getInstance().getReference("images");
        reff= FirebaseDatabase.getInstance().getReference().child("Facility");
        facility=new Facility();

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Filechooser();

            }
        });

//        btnPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fileuploader();
//
//            }
//        });

        btnFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                facility.setName(name.getText().toString().trim());
                facility.setPhoneNo(phoneno.getText().toString().trim());
                facility.setLocation(location.getText().toString().trim());
                facility.setCapacity(capacity.getText().toString().trim());
                facility.setPrice(price_range.getText().toString().trim());
                facility.setCategory(category.getText().toString().trim());


                byte[] fileBytes = FileHelper.getByteArrayFromFile(FacilityActivity.this, imguri);
                String encodedImage = Base64.encodeToString(fileBytes, Base64.NO_WRAP);
                facility.setImage(encodedImage);

                
                reff.push().setValue(facility).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(FacilityActivity.this, "submitted successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(FacilityActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });


            }
        });
    }

    private String getExtension(Uri uri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return MimeTypeMap.getFileExtensionFromUrl(cr.getType(uri));

    }

//    private void Fileuploader(){
//        StorageReference ref=mStorageref.child(System.currentTimeMillis()+","+getExtension(imguri));
//
//        ref.putFile(imguri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//
//                if(task.isSuccessful()){
//                    Toast.makeText(FacilityActivity.this, "submitted successfully", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(FacilityActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//
//                }
//
//
//            }
//        });
//
//
//    }

    private void Filechooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imguri=data.getData();
            img.setImageURI(imguri);



        }
    }
}
