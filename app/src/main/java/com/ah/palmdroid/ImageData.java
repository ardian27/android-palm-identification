package com.ah.palmdroid;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by zorokunti on 17/12/2017.
 */

public class ImageData {

    String imageUrl;
    Bitmap gambar;
    int width,height;


    int ar[] ;

    public int setW(int w){
        width=w;
        return width;
    }
    public int setH(int h){
        height=h;
        return height;
    }
    public void setArray(int [] array,int widthh,int heightt){

        int ar [] =new int [widthh*heightt];
    }

    public int []getArrayImage(){
        return ar;
    }


    public ImageData(){

    }

}