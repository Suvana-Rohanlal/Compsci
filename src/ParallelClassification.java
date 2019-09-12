import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.RecursiveAction;
import java.util.Scanner;

public class ParallelClassification extends RecursiveAction{
  static int low;
  static int high;
  static int t,x,y=0;
  Vector vector;
  CloudData cloud;
  static final int SEQUENTIAL_CUTOFF=5000000;

  public ParallelClassification(){}

  public ParallelClassification(CloudData cloud, Vector vector, int l, int h){
    low=l;
    high=h;
    this.cloud = cloud;
    this.vector = vector;
    t=cloud.getDimt();
    x=cloud.getDimx();
    y=cloud.getDimY();
  }

  @Override
  protected void compute(){
    int num=0;
    float[] ans = new float[2];
    if((high-low) < SEQUENTIAL_CUTOFF){
      for(int i=0; i<t; i++){
        for(int j=0; j<x; j++){
          for(int k=0; k<y; k++){
              num++;
              float an = localWind(i,j,k);
              float abs = cloud.convection[i][j][k];
              classification(an, abs);
              if(num == x*y){
                System.out.print("\n");
                num=0;
              }

          }
        }
      }
    }
    else{
      ParallelClassification left = new ParallelClassification(cloud,vector, low, (high+low)/2);
      ParallelClassification right = new ParallelClassification(cloud,vector, (high+low)/2, high);
      left.fork();
      right.compute();
      left.join();
    }
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

  public float localWind(int i, int j, int k){
    int ncount=0;
    float ny=0;
    float nx=0;

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
    float wind = vector.length(nx,ny)/ncount;
    return wind;
  }
}
