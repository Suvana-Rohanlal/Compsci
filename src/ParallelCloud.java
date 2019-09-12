import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.Scanner;

public class ParallelCloud extends RecursiveTask<float[]>{
  int low;
  int high;
  Vector arr;
  static float[] arrX;
  static float[] arrY;
  CloudData cloud;
  static final int SEQUENTIAL_CUTOFF=5000000;


  public ParallelCloud(){}

  public ParallelCloud(CloudData cloud, int l, int h){
    low=l;
    high=h;

    this.cloud = cloud;
  }

  @Override
  protected float[] compute(){
    float[] ans = new float[2];
    if((high-low) < SEQUENTIAL_CUTOFF){
      int[] ind = new int[3];
      float x = (float)0;
      float y = (float)0;



      for(int i=low; i<high; i++){

        cloud.locate(i,ind);
        x += (float)(cloud.advection[ind[0]][ind[1]][ind[2]].x);
        y += (float)(cloud.advection[ind[0]][ind[1]][ind[2]].y);

      }
      ans[0] = x;
      ans[1] = y;

    }
    else{

      ParallelCloud left = new ParallelCloud(cloud, low, (high+low)/2);
      ParallelCloud right = new ParallelCloud(cloud, (high+low)/2, high);
      left.fork();
      float[] rightAns = right.compute();
      float[] leftAns = left.join();
      ans[0] = leftAns[0]+rightAns[0];
      ans[1] = leftAns[1]+rightAns[1];

    }
    return ans;
  }



}
