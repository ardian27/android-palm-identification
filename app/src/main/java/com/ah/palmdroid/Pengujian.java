package com.ah.palmdroid;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class Pengujian extends AppCompatActivity {


    public int GALLERY = 1, CAMERA = 2, widthAwal, heightAwal;
    public EditText width, height , spread,threshold,url1,url2,url3,url4;



    String allurl;
    public static final String IMAGE_DIRECTORY = "/dificam";

    Button btn_img1,btn_img2,btn_img3,btn_img4;
    ImageView img1,img2,img3,img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengujian);


        btn_img1=(Button)findViewById(R.id.btn_img1);
        btn_img2=(Button)findViewById(R.id.btn_img2);
        btn_img3=(Button)findViewById(R.id.btn_img3);
        btn_img4=(Button)findViewById(R.id.btn_img4);


        img1=(ImageView) findViewById(R.id.img_1);
        img2=(ImageView) findViewById(R.id.img_2);
        img3=(ImageView) findViewById(R.id.img_3);
        img4=(ImageView) findViewById(R.id.img_4);


        url1=(EditText) findViewById(R.id.link1);
        url2=(EditText) findViewById(R.id.link2);
        url3=(EditText) findViewById(R.id.link3);
        url4=(EditText) findViewById(R.id.link4);

        btn_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url1.setText("1");
                showPictureDialog();
            }
        });


        btn_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url2.setText("2");
                showPictureDialog();

            }
        });


        btn_img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url3.setText("3");
                showPictureDialog();
            }
        });


        btn_img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url4.setText("4");
                showPictureDialog();
            }
        });


    }



    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Kembali"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                selesai();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void selesai() {
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY) {


                try {

                    if (Integer.parseInt(url1.getText().toString())==1){
                        Uri contentURI = data.getData();
                        Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                        img1.setImageBitmap(bitmap1);
                        url1.setText("0");
                        Log.i("OKE","Masuk 1");
                    } else  if (Integer.parseInt(url2.getText().toString())==2){
                        Uri contentURI2 = data.getData();
                        Bitmap bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI2);
                        img2.setImageBitmap(bitmap2);
                        url2.setText("0");
                        Log.i("OKE","Masuk 2");
                       // url2.setText(path);
                    } else if (Integer.parseInt(url3.getText().toString())==3){
                        Uri contentURI3 = data.getData();
                        Bitmap bitmap3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI3);
                        img3.setImageBitmap(bitmap3);
                        url3.setText("0");
                        Log.i("OKE","Masuk 3");
                        //url2.setText(path);
                    } else if (Integer.parseInt(url4.getText().toString())==4){
                        Uri contentURI4 = data.getData();
                        Bitmap bitmap4 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI4);
                        img4.setImageBitmap(bitmap4);
                        url4.setText("0");
                        Log.i("OKE","Masuk 4");
                       // url2.setText(path);
                    }else {
                        Log.d("Gagal Get Path ","");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(Pengujian.this, "Get Picture Failed!", Toast.LENGTH_SHORT).show();
                }


        }
    }

}
