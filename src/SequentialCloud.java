import java.util.Scanner;
import java.lang.Math;

public class SequentialCloud{

      static int time, windX, windY, totalWind;
      static float sumX, sumY, averageX, averageY = 0;
      static float[] convection;
      static float[] advection;
      static float wind;
      static int num=0;
      static long start = 0;

      public static String average(int time, int x, int y, int total, Vector vector, CloudData cloud){
        convection = new float[total];
        advection = new float[total];
        String average="";
        int count =0;
        float ix=0;
        float iy=0;
        int n = x*y;
        float nx=0;
        float ny=0;
        int ncount=0;

        for(int i=0; i<time; i++){
          for(int j=0; j<x; j++){
            for(int k=0; k<y; k++){
              ny=0;
              nx=0;
              ncount=0;
              ix = cloud.advection[i][j][k].x;
              iy = cloud.advection[i][j][k].y;

              if(j==0){
                if(k==0){
                  ny=cloud.advection[i][j][k].y+cloud.advection[i][j+1][k].y+cloud.advection[i][j][k+1].y+cloud.advection[i][j+1][k+1].y;
                  nx=cloud.advection[i][j][k].x+cloud.advection[i][j+1][k].x+cloud.advection[i][j][k+1].x+cloud.advection[i][j+1][k+1].x;

                  ncount =4;
                }
                else if(k>0 && k<y-1){
                  ny=cloud.advection[i][j][k].y+cloud.advection[i][j][k+1].y+cloud.advection[i][j+1][k].y+cloud.advection[i][j+1][k+1].y+cloud.advection[i][j+1][k-1].y+cloud.advection[i][j][k-1].y;
                  nx=cloud.advection[i][j][k].x+cloud.advection[i][j][k+1].x+cloud.advection[i][j+1][k].x+cloud.advection[i][j+1][k+1].x+cloud.advection[i][j+1][k-1].x+cloud.advection[i][j][k-1].x;

                  ncount =6;
                }
                else{
                  ny=cloud.advection[i][j][k].y+cloud.advection[i][j+1][k].y+cloud.advection[i][j][k-1].y+cloud.advection[i][j+1][k-1].y;
                  nx=cloud.advection[i][j][k].x+cloud.advection[i][j+1][k].x+cloud.advection[i][j][k-1].x+cloud.advection[i][j+1][k-1].x;

                  ncount =4;
                }
              }
              else if(j>0 && j<y-1){
                if(k==0){
                  ny=cloud.advection[i][j][k].y+cloud.advection[i][j+1][k].y+cloud.advection[i][j][k+1].y+cloud.advection[i][j+1][k+1].y+cloud.advection[i][j-1][k+1].y+cloud.advection[i][j-1][k].y;
                  nx=cloud.advection[i][j][k].x+cloud.advection[i][j+1][k].x+cloud.advection[i][j][k+1].x+cloud.advection[i][j+1][k+1].x+cloud.advection[i][j-1][k+1].x+cloud.advection[i][j-1][k].x;

                  ncount =6;
                }
                else if(k>0 && k<y-1){
                  ny=cloud.advection[i][j][k].y+cloud.advection[i][j+1][k].y+cloud.advection[i][j][k+1].y+cloud.advection[i][j+1][k+1].y;
                  ny = ny +cloud.advection[i][j+1][k-1].y+cloud.advection[i][j][k-1].y+cloud.advection[i][j-1][k-1].y+cloud.advection[i][j-1][k].y+cloud.advection[i][j-1][k+1].y;
                  nx=cloud.advection[i][j][k].x+cloud.advection[i][j+1][k].x+cloud.advection[i][j][k+1].x+cloud.advection[i][j+1][k+1].x+cloud.advection[i][j+1][k-1].x+cloud.advection[i][j][k-1].x;
                  nx = nx +cloud.advection[i][j-1][k-1].x+cloud.advection[i][j-1][k].x+cloud.advection[i][j-1][k+1].x;

                  ncount = 9;
                }
                else{
                  ny=cloud.advection[i][j][k].y+cloud.advection[i][j+1][k].y+cloud.advection[i][j][k-1].y+cloud.advection[i][j+1][k-1].y+cloud.advection[i][j-1][k-1].y+cloud.advection[i][j-1][k].y;
                  nx=cloud.advection[i][j][k].x+cloud.advection[i][j+1][k].x+cloud.advection[i][j][k-1].x+cloud.advection[i][+1][k-1].x+cloud.advection[i][j-1][k-1].x+cloud.advection[i][j-1][k].x;

                  ncount =6;
                }
              }
              else{
                if(k==0){
                  ny=cloud.advection[i][j][k].y+cloud.advection[i][j-1][k].y+cloud.advection[i][j][k+1].y+cloud.advection[i][j-1][k+1].y;
                  nx=cloud.advection[i][j][k].x+cloud.advection[i][j-1][k].x+cloud.advection[i][j][k+1].x+cloud.advection[i][j-1][k+1].x;

                  ncount =4;
                }
                else if(k>0 && k<y-1){
                  ny=cloud.advection[i][j][k].y+cloud.advection[i][j-1][k].y+cloud.advection[i][j][k+1].y+cloud.advection[i][j-1][k+1].y+cloud.advection[i][j-1][k-1].y+cloud.advection[i][j][k-1].y;
                  nx=cloud.advection[i][j][k].x+cloud.advection[i][j-1][k].x+cloud.advection[i][j][k+1].x+cloud.advection[i][j-1][k+1].x+cloud.advection[i][j-1][k-1].x+cloud.advection[i][j][k-1].x;

                  ncount = 6;
                }
                else{
                  ny=cloud.advection[i][j][k].y+cloud.advection[i][j-1][k].y+cloud.advection[i][j][k-1].y+cloud.advection[i][j-1][k-1].y;
                  nx=cloud.advection[i][j][k].x+cloud.advection[i][j-1][k].x+cloud.advection[i][j][k-1].x+cloud.advection[i][j-1][k-1].x;

                  ncount =4;
                }
              }

              sumX += ix;
              sumY += iy;
              wind = vector.length(nx,ny)/ncount;

              if(count<total){
                advection[count] = wind;
                convection[count] = cloud.convection[i][j][k];
                count++;
              }

            }
          }

        }
        averageX = sumX/total;
        averageY = sumY/total;
        average = averageX+" "+averageY;
        return average;
      }

      public float[] getAdv(){
        return advection;
      }

      public float[] getCon(){
        return convection;
      }

      public static void classification(float w, float u){

        float abs = Math.abs(u);
       if(abs>w){
        System.out.print("0");
        }
        else if((w>0.2) && (w>=abs)){
       System.out.print("1");
        }
        else{
         System.out.print("2");
        }
      }




}
