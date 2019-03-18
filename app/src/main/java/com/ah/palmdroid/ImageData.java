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


    public void saveFileToSDCard(String filename, String content) throws Exception
    {
        Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        File file = new File("/sdcard", filename);
        //File file = Environment.getExternalStorageDirectory();
        FileOutputStream fos = new FileOutputStream(file,true);
        fos.write(content.getBytes());
        fos.close();
        String tmp = "\n";
        FileOutputStream fos1 = new FileOutputStream(file,true);
        fos1.write(tmp.getBytes());
        fos1.close();
    }

    public void saveFileToSDCardNoSpace(String filename, String content) throws Exception
    {
        Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        File file = new File("/sdcard", filename);
        //File file = Environment.getExternalStorageDirectory();
        FileOutputStream fos = new FileOutputStream(file,true);
        fos.write(content.getBytes());
        fos.close();
        String tmp = "\t";
        FileOutputStream fos1 = new FileOutputStream(file,true);
        fos1.write(tmp.getBytes());
        fos1.close();
    }

    public  void printMatrix(int[][] matrix){
        for (int x = 0; x< matrix.length;++x){
            for (int y = 0; y<matrix[x].length;++y){
                //System.out.print(matrix[x][y]+"\t");
                try {
                    saveFileToSDCard("LogDificam.txt", ""+matrix[x][y]+"\t");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
    }

    public  void printMatrix1D(int[] matrix){
        for (int x = 0; x< matrix.length+1;++x){

            System.out.print(matrix[x]+"\t");
            try {
                saveFileToSDCardNoSpace("LogDificam.txt", ""+matrix[x]+"\t");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //	System.out.println();
        }
    }

    public int[][] array1Dto2D(float [] array1D){

        int array2D [][] = new int[array1D.length][array1D.length];

        for (int a=0; a<array1D.length/2; a++){
            for (int b = 0; b<array1D.length/2; b++){

            }
        }

        return array2D;
    }

    public float[] array2Dto1D(int [][] array2D){

        float [] array1D = new float[array2D.length*array2D[0].length];

        for (int a=0; a<array2D.length; a++){
            for (int b=0; b<array2D[a].length; b++){
                array1D[a*array2D[0].length+b]=array2D[a][b];
            }
        }

        return array1D;
    }

    // public void getPixels (int[] pixels , int offset, int stride, int x , int y , int width , int height)

}