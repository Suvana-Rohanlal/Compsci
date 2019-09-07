import java.util.Scanner;
import java.lang.Math;

public class SequentialCloud{

      static int time, windX, windY, totalWind;
      static float sumX, sumY, averageX, averageY = 0;
      static float[] convection;
      static float[] advection;
      static float wind;
      static int num=0;
      static float[] averageW;

      public static void main(String[]args){
        Vector vector = new Vector();
        CloudData cloud = new CloudData();

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the name of the file");
        String name = sc.nextLine();
        cloud.readData(name);

        time = cloud.getDimt();
        windX = cloud.getDimx();
        windY = cloud.getDimY();
        totalWind = cloud.dim();

        convection = new float[totalWind];
        advection = new float[totalWind];
        averageW = new float[windX*windY];

        System.out.println(time+" "+windX+" "+windY);
        String disp = average(time, windX, windY, totalWind, vector, cloud);
        System.out.println(disp);

        for(int c=0; c<totalWind; c++){
          classification(advection[c], convection[c]);
          num++;
          if(num == (windX*windY)){
            num = 0;
            System.out.println("\n");
          }
        }
      }

      public static String average(int time, int x, int y, int total, Vector vector, CloudData cloud){
        String average="";
        int count =0;
        float ix=0;
        float iy=0;
        int n = x*y;
        float nx=0;
        float ny=0;
        for(int i=0; i<time; i++){
          ny=0;
          nx=0;
          for(int j=0; j<x; j++){
            for(int k=0; k<y; k++){
              ix = cloud.advection[i][j][k].x;
              iy = cloud.advection[i][j][k].y;
              ny+=iy;
              nx+=ix;
              sumX += ix;
              sumY += iy;
              wind = vector.length(ix,iy);

              if(count<total){
                advection[count] = wind;
                convection[count] = cloud.convection[i][j][k];
                count++;
              }
            }
          }
        //  averageW[i] =
        }
        averageX = sumX/total;
        averageY = sumY/total;
        average = averageX+" "+averageY;
        return average;
      }

      public static void classification(float w, float u){
        float abs = Math.abs(u);
        if(abs>w){
          System.out.println("0");
        }
        else if((w>0.2) && (w>=abs)){
          System.out.println("1");
        }
        else{
          System.out.println("2");
        }
      }
}
