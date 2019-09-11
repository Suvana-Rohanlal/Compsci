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
    //  static float[] averageW;

  /* public static void main(String[]args){
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
      //  averageW = new float[windX*windY];

        System.out.println(time+" "+windX+" "+windY);

        String disp = average(time, windX, windY, totalWind, vector, cloud);

        System.out.println(disp);

        //System.out.println("Time took to run average is "+avgT+" seconds.");

        //tick();
        for(int c=0; c<totalWind; c++){
          classification(advection[c], convection[c]);
          num++;
          if(num == (windX*windY)){
            num = 0;
            System.out.print("\n");
          }
        }

        //System.out.println("Time took to run classification is "+classT+" seconds.");
        sc.close();
      }*/

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
        //System.out.println("loop 1");
        for(int i=0; i<time; i++){
        //  System.out.println("loop 2");
          for(int j=0; j<x; j++){
          //  System.out.println("loop 3");
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

                /*  ny = ny/4;
                  nx = nx/4;*/
                  ncount =4;
                }
                else if(k>0 && k<y-1){
                  ny=cloud.advection[i][j][k].y+cloud.advection[i][j][k+1].y+cloud.advection[i][j+1][k].y+cloud.advection[i][j+1][k+1].y+cloud.advection[i][j+1][k-1].y+cloud.advection[i][j][k-1].y;
                  nx=cloud.advection[i][j][k].x+cloud.advection[i][j][k+1].x+cloud.advection[i][j+1][k].x+cloud.advection[i][j+1][k+1].x+cloud.advection[i][j+1][k-1].x+cloud.advection[i][j][k-1].x;
                /*  ny = ny/6;
                  nx = nx/6;*/
                  ncount =6;
                }
                else{
                  ny=cloud.advection[i][j][k].y+cloud.advection[i][j+1][k].y+cloud.advection[i][j][k-1].y+cloud.advection[i][j+1][k-1].y;
                  nx=cloud.advection[i][j][k].x+cloud.advection[i][j+1][k].x+cloud.advection[i][j][k-1].x+cloud.advection[i][j+1][k-1].x;
                /*  ny = ny/4;
                  nx = nx/4;*/
                  ncount =4;
                }
              }
              else if(j>0 && j<y-1){
                if(k==0){
                  ny=cloud.advection[i][j][k].y+cloud.advection[i][j+1][k].y+cloud.advection[i][j][k+1].y+cloud.advection[i][j+1][k+1].y+cloud.advection[i][j-1][k+1].y+cloud.advection[i][j-1][k].y;
                  nx=cloud.advection[i][j][k].x+cloud.advection[i][j+1][k].x+cloud.advection[i][j][k+1].x+cloud.advection[i][j+1][k+1].x+cloud.advection[i][j-1][k+1].x+cloud.advection[i][j-1][k].x;
                /*  ny = ny/6;
                  nx = nx/6;*/
                  ncount =6;
                }
                else if(k>0 && k<y-1){
                  ny=cloud.advection[i][j][k].y+cloud.advection[i][j+1][k].y+cloud.advection[i][j][k+1].y+cloud.advection[i][j+1][k+1].y;
                  ny = ny +cloud.advection[i][j+1][k-1].y+cloud.advection[i][j][k-1].y+cloud.advection[i][j-1][k-1].y+cloud.advection[i][j-1][k].y+cloud.advection[i][j-1][k+1].y;
                  nx=cloud.advection[i][j][k].x+cloud.advection[i][j+1][k].x+cloud.advection[i][j][k+1].x+cloud.advection[i][j+1][k+1].x+cloud.advection[i][j+1][k-1].x+cloud.advection[i][j][k-1].x;
                  nx = nx +cloud.advection[i][j-1][k-1].x+cloud.advection[i][j-1][k].x+cloud.advection[i][j-1][k+1].x;
                /*  ny = ny/9;
                  nx = nx/9;*/
                  ncount = 9;
                }
                else{
                  ny=cloud.advection[i][j][k].y+cloud.advection[i][j+1][k].y+cloud.advection[i][j][k-1].y+cloud.advection[i][j+1][k-1].y+cloud.advection[i][j-1][k-1].y+cloud.advection[i][j-1][k].y;
                  nx=cloud.advection[i][j][k].x+cloud.advection[i][j+1][k].x+cloud.advection[i][j][k-1].x+cloud.advection[i][+1][k-1].x+cloud.advection[i][j-1][k-1].x+cloud.advection[i][j-1][k].x;
                /*  ny = ny/6;
                  nx = nx/6;*/
                  ncount =6;
                }
              }
              else{
                if(k==0){
                  ny=cloud.advection[i][j][k].y+cloud.advection[i][j-1][k].y+cloud.advection[i][j][k+1].y+cloud.advection[i][j-1][k+1].y;
                  nx=cloud.advection[i][j][k].x+cloud.advection[i][j-1][k].x+cloud.advection[i][j][k+1].x+cloud.advection[i][j-1][k+1].x;
                /*  ny = ny/4;
                  nx = nx/4;*/
                  ncount =4;
                }
                else if(k>0 && k<y-1){
                  ny=cloud.advection[i][j][k].y+cloud.advection[i][j-1][k].y+cloud.advection[i][j][k+1].y+cloud.advection[i][j-1][k+1].y+cloud.advection[i][j-1][k-1].y+cloud.advection[i][j][k-1].y;
                  nx=cloud.advection[i][j][k].x+cloud.advection[i][j-1][k].x+cloud.advection[i][j][k+1].x+cloud.advection[i][j-1][k+1].x+cloud.advection[i][j-1][k-1].x+cloud.advection[i][j][k-1].x;
                /*  ny = ny/6;
                  nx = nx/6;*/
                  ncount = 6;
                }
                else{
                  ny=cloud.advection[i][j][k].y+cloud.advection[i][j-1][k].y+cloud.advection[i][j][k-1].y+cloud.advection[i][j-1][k-1].y;
                  nx=cloud.advection[i][j][k].x+cloud.advection[i][j-1][k].x+cloud.advection[i][j][k-1].x+cloud.advection[i][j-1][k-1].x;
                /*  ny = ny/4;
                  nx = nx/4;*/
                  ncount =4;
                }
              }

              sumX += ix;
              sumY += iy;
              wind = vector.length(nx,ny)/ncount;
            //  System.out.println(ncount+" ");
              if(count<total){
                advection[count] = wind;
                convection[count] = cloud.convection[i][j][k];
                count++;
              }
              //System.out.println("Out of Y");
            }
          }
        //  averageW[i] =
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
