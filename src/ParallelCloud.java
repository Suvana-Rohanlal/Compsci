import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.Scanner;

public class ParallelCloud extends RecursiveTask<Float>{
  int low;
  int high;
  float[][][] arr;
  static float[] arrX;
  static float[] arrY;
  CloudData cloud = new CloudData();
  static final int SEQUENTIAL_CUTOFF=500;

  //int ans = 0;
  public ParallelCloud(){}

  public ParallelCloud(float[][][] a, int l, int h){
    low=l;
    high=h;
    arr=a;
  }

  protected float[] compute(){

    if((high-low) < SEQUENTIAL_CUTOFF){
      int[] ind = new int[3];
      float x = (float)0;
      float y = (float)0;
      float ans[] = new float[2];

      //float ans = 0;
      for(int i=low; i<high; i++){

        cloud.locate(i,ind);
        x += (float)(cloud.advection[ind[0]][ind[1]][ind[2]].x);
        y += (float)(cloud.advection[ind[0]][ind[1]][ind[2]].y);
        //ans += arr[i];
      //  System.out.println("ans= "+ans);
      }
      ans[0] = x;
      ans[1] = y;
      return ans;
    }
    else{
    //  System.out.println("entered else");
      ParallelCloud left = new ParallelCloud(arr, low, (high+low)/2);
      ParallelCloud right = new ParallelCloud(arr,(high+low)/2, high);
      left.fork();
      float[] rightAns = right.compute();
      float leftAns = left.join();
      float[] ans = {leftAns+rightAns[0],leftAns+rightAns[1]};
      return ans;
    }
  }

  /*public static void populate(CloudData cloud, int t, int x, int y, int totalWind){
    arrX = new float[totalWind];
    arrY = new float[totalWind];
    int count =0;
    for(int i=0; i<t; i++){
      for(int k=0; k<x; k++){
        for(int j=0; j<y; j++){
        //  System.out.println(cloud.advection[i][k][j].x+" "+cloud.advection[i][k][j].y);
       arrX[count] = cloud.advection[i][k][j].x;
         arrY[count] = cloud.advection[i][k][j].y;
          count++;
        }
      }
    }
    //System.out.println("done");
  }

 public static float[] getArrX(){
    return arrX;
  }
  public static float[] getArrY(){
    return arrY;
  }*/

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
