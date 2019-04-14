package com.ah.palmdroid;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
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

import com.ah.palmdroid.API.CenterRandom;
import com.ah.palmdroid.API.MinMax;
import com.ah.palmdroid.API.NilaiW;
import com.ah.palmdroid.API.ThresholdSpread;
import com.ah.palmdroid.DB.MyAppDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pengujian extends AppCompatActivity {


    public int GALLERY = 1, CAMERA = 2, widthAwal, heightAwal;
    public EditText width, height , spread,threshold,url1,url2,url3,url4;



    String allurl;
    public static final String IMAGE_DIRECTORY = "/dificam";

    public static MyAppDatabase myAppDatabaseNW,myAppDatabaseCR,myAppDatabaseTS,myAppDatabaseMM;

    Button btn_img1,btn_img2,btn_img3,btn_img4,btn_identification,btn_reload,btn_clear;
    ImageView img1,img2,img3,img4;
    int widthimage ,heigthimage;

    int [][] arr1,arg1,arb1 = new int[widthimage][heigthimage];
    int [][] arr2,arg2,arb2 = new int[widthimage][heigthimage];
    int [][] arr3,arg3,arb3 = new int[widthimage][heigthimage];
    int [][] arr4,arg4,arb4 = new int[widthimage][heigthimage];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengujian);

        myAppDatabaseNW = Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"nilaiwdb").allowMainThreadQueries().build();
        myAppDatabaseCR = Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"centerrandomdb").allowMainThreadQueries().build();
        myAppDatabaseMM = Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"minmaxdb").allowMainThreadQueries().build();
        myAppDatabaseTS = Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"thresholdspreaddb").allowMainThreadQueries().build();


        btn_img1=(Button)findViewById(R.id.btn_img1);
        btn_img2=(Button)findViewById(R.id.btn_img2);
        btn_img3=(Button)findViewById(R.id.btn_img3);
        btn_img4=(Button)findViewById(R.id.btn_img4);
        btn_clear=(Button)findViewById(R.id.btn_clear);
        btn_reload=(Button)findViewById(R.id.btn_reload);


        btn_identification=(Button)findViewById(R.id.btn_startpengujian);


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


                /*clearData();
                List<com.ah.palmdroid.DB.CenterRandom> centerRandoms = Pengujian.myAppDatabaseCR.myDao().getcenterRandom();
                for (com.ah.palmdroid.DB.CenterRandom cr : centerRandoms){
                    Log.d("h",cr.getH());
                    Log.d("s",cr.getS());
                    Log.d("v",cr.getV());
                }

                Log.d("Jumlah L",""+centerRandoms.size());


                Pengujian.myAppDatabaseCR.myDao().deletecenterRandom();
                List<com.ah.palmdroid.DB.CenterRandom> centerRandoms2 = Pengujian.myAppDatabaseCR.myDao().getcenterRandom();
                Log.d("Jumlah B",""+centerRandoms2.size());

*/

            }
        });

        btn_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadData();
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });

        btn_identification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHSVforPengujian();
                //Log.i("OK","Masuk Pengujian");
                //reloadData();
                /*Retrofit retrofit2 = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL2)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api apiCenterRandom = retrofit2.create(Api.class);
                Call<List<CenterRandom>> call2 = apiCenterRandom.getCenterRandom();
                call2.enqueue(new Callback<List<CenterRandom>>() {
                    @Override
                    public void onResponse(Call<List<CenterRandom>> call, Response<List<CenterRandom>> response) {

                        List<CenterRandom> centerRandoms = response.body();
                        int a=0;
                        for (CenterRandom cr : centerRandoms){
                            Log.d("H",cr.h);
                            Log.d("S",cr.s);
                            Log.d("V",cr.v);
                            Log.d("Targer",cr.Target);

                            com.ah.palmdroid.DB.CenterRandom centerRandom = new  com.ah.palmdroid.DB.CenterRandom();
                            centerRandom.setId(a);
                            centerRandom.setH(cr.h);
                            centerRandom.setS(cr.s);
                            centerRandom.setV(cr.v);
                            a++;

                            Pengujian.myAppDatabaseCR.myDao().addCenterRandom(centerRandom);
                            //Toast.makeText(getApplicationContext(),"Berhasil Insert nilai W",Toast.LENGTH_LONG).show();
                            Log.d("OK CR INSERT","="+a);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CenterRandom>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Gagal Get Nilai Center Random"+ t.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });
*/
            }
        });
    }

    public void clearData(){
        Pengujian.myAppDatabaseNW.myDao().deletenilaiW();
        Pengujian.myAppDatabaseCR.myDao().deletecenterRandom();
        Pengujian.myAppDatabaseMM.myDao().deleteminMax();
        Pengujian.myAppDatabaseTS.myDao().deletethresholdSpread();

        Toast.makeText(getApplicationContext(),"Data Center telah dikosongkan",Toast.LENGTH_LONG).show();
    }

    public void reloadData(){

        final double v_threshold , v_spread;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofit retrofit3 = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL3)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Retrofit retrofit4 = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL4)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Api apiNilaiW = retrofit.create(Api.class);
        Api apiCenterRandom = retrofit2.create(Api.class);
        Api apiMinMax = retrofit3.create(Api.class);
        Api apiThresholdSpread = retrofit4.create(Api.class);

        Call<List<NilaiW>> call = apiNilaiW.getNilaiW();
        Call<List<CenterRandom>> call2 = apiCenterRandom.getCenterRandom();
        Call<List<MinMax>> call3 = apiMinMax.getMinMax();
        Call<List<ThresholdSpread>> call4 = apiThresholdSpread.getThresholdSpread();

        call.enqueue(new Callback<List<NilaiW>>() {
            @Override
            public void onResponse(Call<List<NilaiW>> call, Response<List<NilaiW>> response) {

                List<NilaiW> nilaiw = response.body();

                int a=0;
                for (NilaiW nw : nilaiw){
                    Log.d("id_nilai",nw.id_w);
                    Log.d("Y0",nw.y0);
                    Log.d("Y1",nw.y1);

                    com.ah.palmdroid.DB.NilaiW nilaiW = new  com.ah.palmdroid.DB.NilaiW();
                    nilaiW.setId(a);
                    nilaiW.setY0(nw.y0);
                    nilaiW.setY1(nw.y1);
                    Log.d("OK NW INSERT","="+a);
                    a++;

                    Pengujian.myAppDatabaseNW.myDao().addNilaiW(nilaiW);

                }
            }

            @Override
            public void onFailure(Call<List<NilaiW>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Gagal Get Nilai W"+ t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        call2.enqueue(new Callback<List<CenterRandom>>() {
            @Override
            public void onResponse(Call<List<CenterRandom>> call, Response<List<CenterRandom>> response) {

                List<CenterRandom> centerRandoms = response.body();
                int a=0;
                for (CenterRandom cr : centerRandoms){
                    Log.d("H",cr.h);
                    Log.d("S",cr.s);
                    Log.d("V",cr.v);
                    Log.d("Targer",cr.Target);


                    com.ah.palmdroid.DB.CenterRandom centerRandom = new  com.ah.palmdroid.DB.CenterRandom();
                    centerRandom.setId(a);
                    centerRandom.setH(cr.h);
                    centerRandom.setS(cr.s);
                    centerRandom.setV(cr.v);
                    centerRandom.setTarget(cr.Target);
                    a++;

                    Pengujian.myAppDatabaseCR.myDao().addCenterRandom(centerRandom);
                    //Toast.makeText(getApplicationContext(),"Berhasil Insert nilai W",Toast.LENGTH_LONG).show();
                    Log.d("OK CR INSERT","="+a);

                }
            }

            @Override
            public void onFailure(Call<List<CenterRandom>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Gagal Get Nilai Center Random"+ t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        call3.enqueue(new Callback<List<MinMax>>() {
            @Override
            public void onResponse(Call<List<MinMax>> call, Response<List<MinMax>> response) {

                List<MinMax> minMaxs = response.body();
                int a=0;
                for (MinMax mm : minMaxs){
                    Log.d("minH",mm.min_h);
                    Log.d("maxH",mm.max_h);
                    Log.d("minS",mm.min_s);
                    Log.d("maxS",mm.max_s);
                    Log.d("minV",mm.min_v);
                    Log.d("maxV",mm.max_v);

                    com.ah.palmdroid.DB.MinMax minMax = new  com.ah.palmdroid.DB.MinMax();
                    minMax.setId(a);
                    minMax.setMin_h(mm.min_h);
                    minMax.setMin_s(mm.min_s);
                    minMax.setMin_v(mm.min_v);
                    minMax.setMax_h(mm.max_h);
                    minMax.setMax_s(mm.max_s);
                    minMax.setMax_v(mm.max_v);

                    a++;

                    Pengujian.myAppDatabaseMM.myDao().addMinMax(minMax);
                    //Toast.makeText(getApplicationContext(),"Berhasil Insert nilai W",Toast.LENGTH_LONG).show();
                    Log.d("OK MM INSERT","="+a);

                }
            }

            @Override
            public void onFailure(Call<List<MinMax>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Gagal Get Nilai Min Max"+ t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });


        call4.enqueue(new Callback<List<ThresholdSpread>>() {
            @Override
            public void onResponse(Call<List<ThresholdSpread>> call, Response<List<ThresholdSpread>> response) {

                List<ThresholdSpread> thresholdSpreads = response.body();
                int a=0;
                for (ThresholdSpread ts : thresholdSpreads){
                    Log.d("Threshold",ts.threshold);
                    Log.d("Spread",ts.spread);


                    com.ah.palmdroid.DB.ThresholdSpread thresholdSpread = new  com.ah.palmdroid.DB.ThresholdSpread();
                    thresholdSpread.setId(a);
                    thresholdSpread.setThreshold(ts.threshold);
                    thresholdSpread.setSpread(ts.spread);
                    a++;

                    Pengujian.myAppDatabaseTS.myDao().addThresholdSpread(thresholdSpread);
                    //Toast.makeText(getApplicationContext(),"Berhasil Insert nilai W",Toast.LENGTH_LONG).show();
                    Log.d("OK TS INSERT","="+a);
                }

                }
            @Override
            public void onFailure(Call<List<ThresholdSpread>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Gagal Get Nilai Threshold dan Spread"+ t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

/*
        call.enqueue(new Callback<List<NilaiW>>() {
            @Override
            public void onResponse(Call<List<NilaiW>> call, Response<List<NilaiW>> response) {

                List<NilaiW> nilaiw = response.body();

                int a=0;
                for (NilaiW nw : nilaiw){
                    Log.d("id_nilai",nw.id_w);
                    Log.d("Y0",nw.y0);
                    Log.d("Y1",nw.y1);

                    com.ah.palmdroid.DB.NilaiW nilaiW = new  com.ah.palmdroid.DB.NilaiW();
                    nilaiW.setId(a);
                    nilaiW.setY0(nw.id_w);
                    nilaiW.setY1(nw.y1);
                    a++;

                    Pengujian.myAppDatabaseNW.myDao().addNilaiW(nilaiW);
                    //Toast.makeText(getApplicationContext(),"Berhasil Insert nilai W",Toast.LENGTH_LONG).show();
                    Log.d("OK INSERT","="+a);
                }



            }

            @Override
            public void onFailure(Call<List<NilaiW>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Gagal Get Nilai W"+ t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

*/

        Toast.makeText(getApplicationContext(),"Data Telah diupdate",Toast.LENGTH_LONG).show();
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
                        setWidthimage(bitmap1.getWidth());
                        setHeigthimage(bitmap1.getHeight());
                        setArr1(getPixelRfromBitmap(bitmap1));
                        setArg1(getPixelGfromBitmap(bitmap1));
                        setArb1(getPixelBfromBitmap(bitmap1));
                        url1.setText("0");
                        Log.i("OKE","Masuk 1");
                    } else  if (Integer.parseInt(url2.getText().toString())==2){
                        Uri contentURI2 = data.getData();
                        Bitmap bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI2);
                        img2.setImageBitmap(bitmap2);
                        setArr2(getPixelRfromBitmap(bitmap2));
                        setArg2(getPixelGfromBitmap(bitmap2));
                        setArb2(getPixelBfromBitmap(bitmap2));
                        url2.setText("0");
                        Log.i("OKE","Masuk 2");
                       // url2.setText(path);
                    } else if (Integer.parseInt(url3.getText().toString())==3){
                        Uri contentURI3 = data.getData();
                        Bitmap bitmap3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI3);
                        img3.setImageBitmap(bitmap3);
                        setArr3(getPixelRfromBitmap(bitmap3));
                        setArg3(getPixelGfromBitmap(bitmap3));
                        setArb3(getPixelBfromBitmap(bitmap3));
                        url3.setText("0");
                        Log.i("OKE","Masuk 3");
                        //url2.setText(path);
                    } else if (Integer.parseInt(url4.getText().toString())==4){
                        Uri contentURI4 = data.getData();
                        Bitmap bitmap4 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI4);
                        img4.setImageBitmap(bitmap4);
                        setArr4(getPixelRfromBitmap(bitmap4));
                        setArg4(getPixelGfromBitmap(bitmap4));
                        setArb4(getPixelBfromBitmap(bitmap4));
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

    public int [][] getPixelRfromBitmap(Bitmap img){

        int [][] pixelsR = new int[300][300];
        for (int x=0; x<300; x++){
            for (int y=0; y<300; y++){
                int pixel = img.getPixel(x,y);
                int R = Color.red(pixel);
                pixelsR[x][y]=R;
            }
        }
        return pixelsR;
    }

    public int [][] getPixelGfromBitmap(Bitmap img){

        int [][] pixelsG = new int[300][300];
        for (int x=0; x<300; x++){
            for (int y=0; y<300; y++){
                int pixel = img.getPixel(x,y);
                int G =  (pixel>>8) & 0xff;
                pixelsG[x][y]=G;
            }
        }

        return pixelsG;
    }


    public int [][] getPixelBfromBitmap(Bitmap img){

        int [][] pixelsB = new int[300][300];
        for (int x=0; x<300; x++){
            for (int y=0; y<300; y++){
                int pixel = img.getPixel(x,y);
                int B = pixel & 0xff;
                pixelsB[x][y]=B;
            }
        }

        return pixelsB;
    }

    public void getHSVforPengujian(){

        Log.d("wImage=",""+getWidthimage());
        Log.d("HImage=",""+getHeigthimage());
        FunctionSawit fs = new FunctionSawit();
        int width=getWidthimage(),  height=getHeigthimage(  );

        double sumR [][]= new double[width][height];
        double sumG [][]= new double[width][height];
        double sumB [][]= new double[width][height];

        int [][]RR1 = getArr1();
        int [][]RR2 = getArr2();
        int [][]RR3 = getArr3();
        int [][]RR4 = getArr4();

        int [][]GG1 = getArg1();
        int [][]GG2 = getArg2();
        int [][]GG3 = getArg3();
        int [][]GG4 = getArg4();

        int [][]BB1 = getArb1();
        int [][]BB2 = getArb2();
        int [][]BB3 = getArb3();
        int [][]BB4 = getArb4();


        Log.d("1RR299",""+RR1[299][299]);
        Log.d("2RR299",""+RR2[299][299]);
        Log.d("3RR299",""+RR3[299][299]);
        Log.d("4RR299",""+RR4[299][299]);



        //JUMLAHKAN DAN BAGI 4
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double sr;
                double sg;
                double sb;

                double sumr;
                double sumg;
                double sumb;

                if ((RR1[i][j]==0)&&(RR2[i][j]==0)&&(RR3[i][j]==0)&&(RR4[i][j]==0)){
                    sumr=0;
                }else {
                    sumr=fs.pembulatan4Angka((RR1[i][j]+RR2[i][j]+RR3[i][j]+RR4[i][j])/4);
                }

                if ((GG1[i][j]==0)&&(GG2[i][j]==0)&&(GG3[i][j]==0)&&(GG4[i][j]==0)){
                    sumg=0;
                }else {
                    sumg=fs.pembulatan4Angka((GG1[i][j]+GG2[i][j])+(GG3[i][j]+GG4[i][j])/4);
                }

                if ((BB1[i][j]==0)&&(BB2[i][j]==0)&&(BB3[i][j]==0)&&(BB4[i][j]==0)){
                    sumb=0;
                }else {
                    sumb=fs.pembulatan4Angka((BB1[i][j]+BB2[i][j])+(BB3[i][j]+BB4[i][j])/4);
                }

                sumR[i][j] = sumr;
                sumB[i][j] = sumg;
                sumB[i][j] = sumb;

            }
        }


        Log.d("sumR299",""+sumR[299][299]);
        Log.d("sumG299",""+sumG[299][299]);
        Log.d("sumB299",""+sumB[299][299]);


        //NORMALISASI NILAI RGB
        double r [][] = new double[width][height];
        double g [][] = new double[width][height];
        double b [][] = new double[width][height];


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                if((sumR[i][j]==0.0)){
                    r[i][j]=0;
                }else {
                    r[i][j]=fs.pembulatan4Angka(((sumR[i][j])/(sumR[i][j]+sumG[i][j]+sumB[i][j])));
                }

                if((sumG[i][j]==0.0)){
                    g[i][j]=0;
                }else {
                    g[i][j]=fs.pembulatan4Angka(((sumG[i][j])/(sumR[i][j]+sumG[i][j]+sumB[i][j])));
                }

                if((sumB[i][j]==0.0)){
                    b[i][j]=0;
                }else {
                    b[i][j]=fs.pembulatan4Angka(((sumB[i][j])/(sumR[i][j]+sumG[i][j]+sumB[i][j])));
                }

            }
        }

        //printGridDouble(r);

        //RGB TO HSV
        double v [][] = new double[width][height];
        double s [][] = new double[width][height];
        double h [][] = new double[width][height];


        double v_min [][] = new double[width][height];

        double x [] = new double [width*height];

        //HITUNG NILAI V
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double [] tab = {r[i][j],g[i][j],b[i][j]};
                if ((r[i][j])==0){

                }
                v[i][j]= fs.getMaxValue(tab);

                v_min[i][j]= fs.getMinValue(tab);

                //Hitung Nilai S
                if (v[i][j] == 0){
                    s[i][j] = 0;
                }else{
                    s[i][j] = fs.pembulatan4Angka(1- (v_min[i][j]/v[i][j]));
                }

                //hitung nilai H
                if(s[i][j]==0){
                    h[i][j] = 0;
                }
                else if( v[i][j] == r[i][j]){
                    h[i][j] = fs.pembulatan4Angka(60 * (0+((g[i][j]-b[i][j])/(s[i][j]*v[i][j]))));
                }else if (v[i][j] == g[i][j]){
                    h[i][j] = fs.pembulatan4Angka(60 * (2+((b[i][j]-r[i][j])/(s[i][j]*v[i][j]))));
                } else if (v[i][j] == b[i][j]){
                    h[i][j] = fs.pembulatan4Angka(60 * (4+((r[i][j]-g[i][j])/(s[i][j]*v[i][j]))));
                }else {
                    h[i][j] = 0;
                }
            }
        }
        double [] sss= new double [3];
        sss[0]=0.0;
        sss[1]=0.1;
        sss[2]=0.0;
        Log.d("r299",""+r[299][299]);
        Log.d("g299",""+g[299][299]);
        Log.d("b299",""+b[299][299]);


        //MENCARI NILAI MEAN H
        double sumH = 0;
        double sumS = 0;
        double sumV = 0;



        for (int i = 0; i < width; i++) {

            for (int j = 0; j < height; j++) {
                sumH = fs.pembulatan4Angka(sumH+h[i][j]);
                sumS = fs.pembulatan4Angka(sumS+s[i][j]);
                sumV = fs.pembulatan4Angka(sumV+v[i][j]);
            }
        }


        //printGridDouble(sumH);
        //System.out.println("sumH="+sumH);
        //System.out.println("sumS="+sumS);
        //System.out.println("sumV="+sumV);

        double finalH= fs.pembulatan4Angka(sumH/(width*height));
        double finalS= fs.pembulatan4Angka(sumS/(width*height));
        double finalV= fs.pembulatan4Angka(sumV/(width*height));

        //pengujian(finalH,finalS,finalV);


        Log.d("finalH",""+finalH);
        Log.d("finalS",""+finalS);
        Log.d("finalV",""+finalV);

        pengujian(finalH,finalS,finalV);

    }

    public void pengujian(double hh, double ss, double vv){

        try {
            ArrayList<Double> center_h= new ArrayList<Double>();
            ArrayList<Double> center_s= new ArrayList<Double>();
            ArrayList<Double> center_v= new ArrayList<Double>();
            ArrayList<String> center_t= new ArrayList<String>();

            double th  [] =new  double[1];
            double sp  [] =new  double[1];

            List<com.ah.palmdroid.DB.CenterRandom> centerRandoms = Pengujian.myAppDatabaseCR.myDao().getcenterRandom();

            for (com.ah.palmdroid.DB.CenterRandom cr : centerRandoms) {

                center_h.add(Double.parseDouble(cr.getH()));
                center_s.add(Double.parseDouble(cr.getS()));
                center_v.add(Double.parseDouble(cr.getV()));
                center_t.add(cr.getTarget());
            }

            Log.d("cr long = ","="+centerRandoms.size());

            double [][] random = new double[center_h.size()][3];
            //   String [] random_t = new String[center_h.size()];

            for (int j = 0; j < center_h.size(); j++) {
                for (int k = 0; k < 1; k++) {
                    random[j][k]=center_h.get(j);
                    random[j][k+1]=center_s.get(j);
                    random[j][k+2]=center_v.get(j);
                }
            }

            int lengthData = 1;
            int pangkat = 2;

            double minmaxx [] =new double[6];
            List<com.ah.palmdroid.DB.MinMax> minmax = Pengujian.myAppDatabaseMM.myDao().getminMax();

            for (com.ah.palmdroid.DB.MinMax mm : minmax) {

                minmaxx[0]=(Double.parseDouble(mm.getMin_h()));
                minmaxx[1]=(Double.parseDouble(mm.getMax_h()));
                minmaxx[2]=(Double.parseDouble(mm.getMin_s()));
                minmaxx[3]=(Double.parseDouble(mm.getMax_s()));
                minmaxx[4]=(Double.parseDouble(mm.getMin_v()));
                minmaxx[5]=(Double.parseDouble(mm.getMax_v()));

            }

            Log.d("minmax long = ","="+minmax.size());

            double h=normalisasi(hh, minmaxx[0], minmaxx[1]);
            double s=normalisasi(ss, minmaxx[2], minmaxx[3]);
            double v=normalisasi(vv, minmaxx[4], minmaxx[5]);


            double [][] euc = new double[lengthData][3];

            for (int i = 0; i < lengthData; i++) {
                for (int j = 0; j < 1; j++) {
                    euc[i][0]=pembulatan(Math.sqrt(
                            Math.pow((h-random[0][0]),pangkat ) +
                                    Math.pow((s-random[0][1]),pangkat ) +
                                    Math.pow((v-random[0][2]),pangkat )  ));

                    euc[i][1]=pembulatan(Math.sqrt(
                            Math.pow((h-random[1][0]),pangkat ) +
                                    Math.pow((s-random[1][1]),pangkat ) +
                                    Math.pow((v-random[1][2]),pangkat )  ));

                    euc[i][2]=pembulatan(Math.sqrt(
                            Math.pow((h-random[2][0]),pangkat ) +
                                    Math.pow((s-random[2][1]),pangkat ) +
                                    Math.pow((v-random[2][2]),pangkat ) ));

                }
            }

            double [][] gaussian = new double[lengthData][3];
            double b1 = 0.83225;
            double spread = b1/1;

            for (int i = 0; i < lengthData; i++) {
                for (int j = 0; j < 1; j++) {
                    gaussian[i][0] = pembulatan(Math.exp((-(Math.pow((b1*euc[i][0]),pangkat)))));
                    gaussian[i][1] = pembulatan(Math.exp((-(Math.pow((b1*euc[i][1]),pangkat)))));
                    gaussian[i][2] = pembulatan(Math.exp((-(Math.pow((b1*euc[i][2]),pangkat)))));
                }
            }

            //printGridDouble(gaussian);

            double y1 [] = new double[4];
            double y2 [] = new double[4];
            List<com.ah.palmdroid.DB.NilaiW> nilaiWS = Pengujian.myAppDatabaseNW.myDao().getnilaiW();

            int iiii = 0;
            for (com.ah.palmdroid.DB.NilaiW nw : nilaiWS) {
                y1[iiii]=(Double.parseDouble(nw.getY0()));
                y2[iiii]=(Double.parseDouble(nw.getY1()));
                iiii++;
            }

            double y_in1 = ((gaussian[0][0]*y1[0])+(gaussian[0][1]*y1[1])+(gaussian[0][2]*y1[2])+y1[3]);
            double y_in2 = ((gaussian[0][0]*y2[0])+(gaussian[0][1]*y2[1])+(gaussian[0][2]*y2[2])+y2[3]);

            double sigmoid1 = getSigmoid(y_in1);
            double sigmoid2 = getSigmoid(y_in2);

            Log.d("Sigmoid 1",""+sigmoid1);
            Log.d("Sigmoid 2",""+sigmoid2);

            List<com.ah.palmdroid.DB.ThresholdSpread> thresholdSpreads = Pengujian.myAppDatabaseTS.myDao().getthresholdSpread();

            for (com.ah.palmdroid.DB.ThresholdSpread ts : thresholdSpreads) {

                th[0]=Double.parseDouble(ts.getThreshold());
                sp[0]=Double.parseDouble(ts.getSpread());
            }

            double rnd = 2/(Math.random() *0.9);
            Log.d("TES",""+rnd);
            if ((sigmoid1 < th[0] )&&(sigmoid2 < th[0])) {

                Toast.makeText(getApplicationContext(),"Data Sawit Dikenali sebagai sawit Mentah",Toast.LENGTH_LONG).show();

            } else if ((sigmoid1 > th[0] )&&(sigmoid2<th[0])) {
                Toast.makeText(getApplicationContext(),"Data Sawit Dikenali sebagai sawit Masak",Toast.LENGTH_LONG).show();

            } else if ((sigmoid1 > th[0] )&&(sigmoid2>th[0])) {
                Toast.makeText(getApplicationContext(),"Data Sawit Dikenali sebagai sawit Kelewat Masak(Busuk)",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(),"Data Sawit Tidak Dikenali",Toast.LENGTH_LONG).show();
            }



        } catch (Exception e) {
          Log.d("Gagal Pengujian", e.getMessage().toString());
        }

    }



    public void printGridDouble(double[][] in){
        for(int i = 0; i < in.length; i++){
            for(int j = 0; j < in[0].length; j++){
                Log.d(""+i+":"+j,"="+in[i][j] );
            }

        }
    }

    public void printGrid1(double[] in){

            for(int j = 0; j < in.length; j++){
                Log.d("print1"+j,"="+in[j] );
            }


    }
    public double getSigmoid(double y_in){
        double y=1/(1+Math.exp(-(y_in)));
        return y;
    }

    public double pembulatan(double nilai){

        double hasilPembulatan= Double.parseDouble(String.format("%.6f", nilai));

        return hasilPembulatan;
    }

    public double pembulatan4Angka(double nilai){

        double hasilPembulatan= Double.parseDouble(String.format("%.4f", nilai));

        return hasilPembulatan;
    }

    public double pembulatan2Angka(double nilai){

        double hasilPembulatan= Double.parseDouble(String.format("%.2f", nilai));

        return hasilPembulatan;
    }

    public double pembulatan1Angka(double nilai){

        double hasilPembulatan= Double.parseDouble(String.format("%.1f", nilai));
        return hasilPembulatan;
    }

    public int [][] sum4Array2Dimension(){

        for (int i = 0; i < 300; i++) {
            for (int j = 0; j < 300; j++) {


            }
        }
        return null;
    }

    public Double normalisasi(Double input, Double min, Double max) {
        return (input - min) / (max - min);
    }

    public int getWidthimage() {
        return widthimage;
    }

    public void setWidthimage(int widthimage) {
        this.widthimage = widthimage;
    }

    public int getHeigthimage() {
        return heigthimage;
    }

    public void setHeigthimage(int heigthimage) {
        this.heigthimage = heigthimage;
    }

    public int[][] getArr1() {
        return arr1;
    }

    public void setArr1(int[][] arr1) {
        this.arr1 = arr1;
    }

    public int[][] getArg1() {
        return arg1;
    }

    public void setArg1(int[][] arg1) {
        this.arg1 = arg1;
    }

    public int[][] getArb1() {
        return arb1;
    }

    public void setArb1(int[][] arb1) {
        this.arb1 = arb1;
    }

    public int[][] getArr2() {
        return arr2;
    }

    public void setArr2(int[][] arr2) {
        this.arr2 = arr2;
    }

    public int[][] getArg2() {
        return arg2;
    }

    public void setArg2(int[][] arg2) {
        this.arg2 = arg2;
    }

    public int[][] getArb2() {
        return arb2;
    }

    public void setArb2(int[][] arb2) {
        this.arb2 = arb2;
    }

    public int[][] getArr3() {
        return arr3;
    }

    public void setArr3(int[][] arr3) {
        this.arr3 = arr3;
    }

    public int[][] getArg3() {
        return arg3;
    }

    public void setArg3(int[][] arg3) {
        this.arg3 = arg3;
    }

    public int[][] getArb3() {
        return arb3;
    }

    public void setArb3(int[][] arb3) {
        this.arb3 = arb3;
    }

    public int[][] getArr4() {
        return arr4;
    }

    public void setArr4(int[][] arr4) {
        this.arr4 = arr4;
    }

    public int[][] getArg4() {
        return arg4;
    }

    public void setArg4(int[][] arg4) {
        this.arg4 = arg4;
    }

    public int[][] getArb4() {
        return arb4;
    }

    public void setArb4(int[][] arb4) {
        this.arb4 = arb4;
    }
}
