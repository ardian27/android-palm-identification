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
                                takePhotoFromCamera();
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

    private void takePhotoFromCamera() {
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {

                    if (Integer.parseInt(url1.getText().toString())==1){
                        Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                        img1.setImageBitmap(bitmap1);
                    } else  if (Integer.parseInt(url2.getText().toString())==2){
                        Bitmap bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                        img2.setImageBitmap(bitmap2);
                       // url2.setText(path);
                    } else if (Integer.parseInt(url3.getText().toString())==3){
                        Bitmap bitmap3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                        img3.setImageBitmap(bitmap3);
                        //url2.setText(path);
                    } else if (Integer.parseInt(url4.getText().toString())==4){
                        Bitmap bitmap4 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                        img4.setImageBitmap(bitmap4);
                       // url2.setText(path);
                    }else {
                        Log.d("Gagal Get Path ","");
                        finish();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(Pengujian.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
           // imageview1.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(Pengujian.this, "Image Saved!", Toast.LENGTH_SHORT).show();
            widthAwal = thumbnail.getWidth();
            heightAwal = thumbnail.getHeight();
            width.setText("" + widthAwal);
            height.setText("" + heightAwal);
           // width.setEnabled(false);
           // height.setEnabled(false);

        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            String namePict = "dificam" + Calendar.getInstance().getTimeInMillis() + ".jpg";
            File f = new File(wallpaperDirectory, namePict);

            ImageData x = new ImageData();
            //x.setImage(namePict);
             //setImage = namePict;
            String imagePath=""+IMAGE_DIRECTORY+"/"+namePict;
            Bitmap bmm = BitmapFactory.decodeFile(imagePath);
            img1.setImageBitmap(bmm);

            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            String link = f.getPath();
            //url.setText(name);
            if ((getAllurl()=="1")&&(url1.getText().toString()=="1")){
                url1.setText(link);
            } else if ((getAllurl()=="2")&&(url2.getText().toString()=="2")){
                url2.setText(link);
            } else if ((getAllurl()=="3")&&(url2.getText().toString()=="3")){
                url2.setText(link);
            }else if ((getAllurl()=="4")&&(url2.getText().toString()=="4")){
                url2.setText(link);
            }else {
                Log.d("Gagal Get Path ","");
            }
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());


            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }
    public String getAllurl() {
        return allurl;
    }

    public void setAllurl(String allurl) {
        this.allurl = allurl;
    }

}
