package com.ah.palmdroid;

import java.sql.Connection;
import java.util.ArrayList;

public class FunctionSawit {


    public  double[][] transposeMatrix(double [][] m){
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }

    public static double[][] multiplicar(double [][] A, double[][] B) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        double[][] C = new double[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                C[i][j] = 0.00000;
            }
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    public static double[][] invert(double a[][])
    {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle
        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i]*b[index[i]][k];

        // Perform backward substitutions
        for (int i=0; i<n; ++i)
        {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j)
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k)
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

// Method to carry out the partial-pivoting Gaussian
// elimination.  Here index[] stores pivoting order.

    public static void gaussian(double a[][], int index[])
    {
        int n = index.length;
        double c[] = new double[n];

        // Initialize the index
        for (int i=0; i<n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i=0; i<n; ++i)
        {
            double c1 = 0;
            for (int j=0; j<n; ++j)
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        // Search the pivoting element from each column
        int k = 0;
        for (int j=0; j<n-1; ++j)
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i)
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1)
                {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i)
            {
                double pj = a[index[i]][j]/a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }


    // getting the maximum value
    public  double getMaxValue(double[] array) {
        double maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        return maxValue;
    }

    // getting the miniumum value
    public  double getMinValue(double[] array) {
        double minValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }
        return minValue;
    }

    public String [] splitString (String string){
        String s = string;
        String[] arr = s.split("");
        return arr;
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


    public double getSigmoid(double y_in){
        double y=1/(1+Math.exp(-(y_in)));
        return y;
    }



/*
    int [][] red1, red2, red3, red4;
    int [][] green1, green2, green3, green4;
    int [][] blue1, blue2, blue3, blue4;
    int width=300, height=300;

    int [][] hsv;

    int [][] r;
    int [][] g;
    int [][] b;


    double [] d1,d2,d3;


    double datapelatihan[][];
    String datapelatihan_t[];
    double datapelatihan_random [][];
    String datapelatihan_random_t[];

    double minmax [] =new double[6];
    double min_h,max_h,min_s,max_s,min_v,max_v;

    public FunctionSawit(){
    }




    public void pengujian(double hh, double ss, double vv, double threshold){

        try {
            ArrayList<Double> center_h= new ArrayList<Double>();
            ArrayList<Double> center_s= new ArrayList<Double>();
            ArrayList<Double> center_v= new ArrayList<Double>();
            ArrayList<String> center_t= new ArrayList<String>();


            Connection conn ;

            //java.sql.Statement stm = conn.createStatement();


   //         java.sql.ResultSet center = stm.executeQuery("select * from center_random ");

            // data center random
            int ii = 0;
     //       while((center.next())){
       //         center_h.add(center.getDouble("h"));
                center_s.add(center.getDouble("s"));
                center_v.add(center.getDouble("v"));
                center_t.add(center.getString("Target"));

                ii++;
            }

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



            java.sql.ResultSet minmaxs = stm.executeQuery("select * from min_max ");

            int iii = 0;
            while((minmaxs.next())){
                minmax[0]=(minmaxs.getDouble("min_h"));
                minmax[1]=(minmaxs.getDouble("max_h"));
                minmax[2]=(minmaxs.getDouble("min_s"));
                minmax[3]=(minmaxs.getDouble("max_s"));
                minmax[4]=(minmaxs.getDouble("min_v"));
                minmax[5]=(minmaxs.getDouble("max_v"));
                iii++;
            }


            double h=normalisasi(hh, minmax[0], minmax[1]);
            double s=normalisasi(ss, minmax[2], minmax[3]);
            double v=normalisasi(vv, minmax[4], minmax[5]);


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

            for (int i = 0; i < lengthData; i++) {
                for (int j = 0; j < 1; j++) {
                    gaussian[i][0] = pembulatan(Math.exp((-(Math.pow((b1*euc[i][0]),pangkat)))));
                    gaussian[i][1] = pembulatan(Math.exp((-(Math.pow((b1*euc[i][1]),pangkat)))));
                    gaussian[i][2] = pembulatan(Math.exp((-(Math.pow((b1*euc[i][2]),pangkat)))));
                }
            }

            java.sql.ResultSet nilai_w = stm.executeQuery("select * from nilai_w ");
            double y1 [] = new double[4];
            double y2 [] = new double[4];

            int iiii = 0;
            while((nilai_w.next())){
                y1[iiii]=(nilai_w.getDouble("y0"));
                y2[iiii]=(nilai_w.getDouble("y1"));
                iiii++;
            }

            double y_in1 = ((gaussian[0][0]*y1[0])+(gaussian[0][1]*y1[1])+(gaussian[0][2]*y1[2])+y1[3]);
            double y_in2 = ((gaussian[0][0]*y2[0])+(gaussian[0][1]*y2[1])+(gaussian[0][2]*y2[2])+y2[3]);

            double sigmoid1 = getSigmoid(y_in1);
            double sigmoid2 = getSigmoid(y_in2);

            stm.close();
            System.out.println("th"+threshold);
            if ((sigmoid1 < threshold )&&(sigmoid2<threshold)) {

                JOptionPane.showMessageDialog(
                        null,
                        "Data Sawit Dikenali sebagai sawit Mentah",
                        "Hasil Pengujian Buah Kelapa Sawit",
                        JOptionPane.INFORMATION_MESSAGE);

            } else if ((sigmoid1 > threshold )&&(sigmoid2<threshold)) {
                JOptionPane.showMessageDialog(
                        null,
                        "Data Sawit Dikenali sebagai sawit Masak",
                        "Hasil Pengujian Buah Kelapa Sawit",
                        JOptionPane.INFORMATION_MESSAGE);
            } else if ((sigmoid1 > threshold )&&(sigmoid2>threshold)) {
                JOptionPane.showMessageDialog(
                        null,
                        "Data Sawit Dikenali sebagai sawit Kelewat Masak(Busuk)",
                        "Hasil Pengujian Buah Kelapa Sawit",
                        JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(
                        null,
                        "Data Sawit Tidak Dikenali",
                        "Hasil Pengujian Buah Kelapa Sawit",
                        JOptionPane.INFORMATION_MESSAGE);
            }



        } catch (Exception e) {
            System.out.println("Gagal Melakukan Pengujian"+e.getMessage().toString());
        }

    }

    public  double[][] transposeMatrix(double [][] m){
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }

    public static double[][] multiplicar(double [][] A, double[][] B) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        double[][] C = new double[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                C[i][j] = 0.00000;
            }
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    public static double[][] invert(double a[][])
    {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle
        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i]*b[index[i]][k];

        // Perform backward substitutions
        for (int i=0; i<n; ++i)
        {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j)
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k)
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

// Method to carry out the partial-pivoting Gaussian
// elimination.  Here index[] stores pivoting order.

    public static void gaussian(double a[][], int index[])
    {
        int n = index.length;
        double c[] = new double[n];

        // Initialize the index
        for (int i=0; i<n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i=0; i<n; ++i)
        {
            double c1 = 0;
            for (int j=0; j<n; ++j)
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        // Search the pivoting element from each column
        int k = 0;
        for (int j=0; j<n-1; ++j)
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i)
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1)
                {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i)
            {
                double pj = a[index[i]][j]/a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }

    public String [] splitString (String string){
        String s = string;
        String[] arr = s.split("");
        return arr;
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


    public double getSigmoid(double y_in){
        double y=1/(1+Math.exp(-(y_in)));
        return y;
    }




    //PRINT , GET , SET Value
    //******************//******************//******************//******************//******************//******************

    public void printGrid(int[][] in){
        for(int i = 0; i < in.length; i++){
            for(int j = 0; j < in[0].length; j++){
                System.out.print(in[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

    public void printGrid1(double[] in){
        for(int i = 0; i < in.length; i++){

            System.out.print(in[i] + "\t");

            System.out.print("\n");
        }
    }

    public void printGridDouble(double[][] in){
        for(int i = 0; i < in.length; i++){
            for(int j = 0; j < in[0].length; j++){
                System.out.print(in[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

    public int[][] getR() {
        return r;
    }

    public void setR(int[][] r) {
        this.r = r;
    }

    public int[][] getG() {
        return g;
    }

    public void setG(int[][] g) {
        this.g = g;
    }

    public int[][] getB() {
        return b;
    }

    public void setB(int[][] b) {
        this.b = b;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getRed1() {
        return red1;
    }

    public void setRed1(int[][] red1) {
        this.red1 = red1;
    }

    public int[][] getRed2() {
        return red2;
    }

    public void setRed2(int[][] red2) {

        this.red2 = red2;
    }

    public int[][] getRed3() {
        return red3;
    }

    public void setRed3(int[][] red3) {
        this.red3 = red3;
    }

    public int[][] getRed4() {
        return red4;
    }

    public void setRed4(int[][] red4) {
        this.red4 = red4;
    }

    public int[][] getGreen1() {
        return green1;
    }

    public void setGreen1(int[][] green1) {
        this.green1 = green1;
    }

    public int[][] getGreen2() {
        return green2;
    }

    public void setGreen2(int[][] green2) {
        this.green2 = green2;
    }

    public int[][] getGreen3() {
        return green3;
    }

    public void setGreen3(int[][] green3) {
        this.green3 = green3;
    }

    public int[][] getGreen4() {
        return green4;
    }

    public void setGreen4(int[][] green4) {
        this.green4 = green4;
    }

    public int[][] getBlue1() {
        return blue1;
    }

    public void setBlue1(int[][] blue1) {
        this.blue1 = blue1;
    }

    public int[][] getBlue2() {
        return blue2;
    }

    public void setBlue2(int[][] blue2) {
        this.blue2 = blue2;
    }

    public int[][] getBlue3() {
        return blue3;
    }

    public void setBlue3(int[][] blue3) {
        this.blue3 = blue3;
    }

    public int[][] getBlue4() {
        return blue4;
    }

    public void setBlue4(int[][] blue4) {
        this.blue4 = blue4;
    }

    public double[][] getDatapelatihan() {
        return datapelatihan;
    }

    public void setDatapelatihan(double[][] datapelatihan) {
        this.datapelatihan = datapelatihan;
    }

    public String[] getDatapelatihan_t() {
        return datapelatihan_t;
    }

    public void setDatapelatihan_t(String[] datapelatihan_t) {
        this.datapelatihan_t = datapelatihan_t;
    }

    public double[][] getDatapelatihan_random() {
        return datapelatihan_random;
    }

    public void setDatapelatihan_random(double[][] datapelatihan_random) {
        this.datapelatihan_random = datapelatihan_random;
    }

    public String[] getDatapelatihan_random_t() {
        return datapelatihan_random_t;
    }

    public void setDatapelatihan_random_t(String[] datapelatihan_random_t) {
        this.datapelatihan_random_t = datapelatihan_random_t;
    }
*/
}
