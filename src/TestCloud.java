import java.util.Scanner;
import java.lang.Math;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class TestCloud{
  static int time, windX, windY, totalWind;
  static float sumX, sumY, averageX, averageY = 0;
  static float[] convection;
  static float[] advection;
  static float[] Parallel;
  static float wind;
  static int num=0;
  static long start = 0;

  public static void main(String[]args){
    Vector vector = new Vector();
    CloudData cloud = new CloudData();
    ParallelCloud par = new ParallelCloud();
    SequentialCloud seq = new SequentialCloud();

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
    Parallel = new float[2];

    System.out.println("Sequential");
    System.out.println(time+" "+windX+" "+windY);
    tick();
    String disp = seq.average(time, windX, windY, totalWind, vector, cloud);
    System.out.println(disp);
    System.out.println("Average = "+tock()+" seconds");
    convection = seq.getCon();
    advection = seq.getAdv();
    for(int c=0; c<totalWind; c++){
      seq.classification(advection[c], convection[c]);
      num++;
      if(num == (windX*windY)){
        num = 0;
        System.out.print("\n");
      }
    }

    System.out.println("Parallel");
    System.out.println(time+" "+windX+" "+windY);
    //par.populate(cloud, time, windX, windY, totalWind);
    tick();
  //  float[] arrX = par.getArrX();
    //ParallelCloud cal = new ParallelCloud(arrX, 0, totalWind);
    Parallel = Par(advection);

    float t = tock();
  //  float[] arrY = par.getArrY();
    //ParallelCloud calY = new ParallelCloud(arrY, 0, totalWind);
    //float sumY = Par(arrY);

    System.out.println(Parallel[0]/totalWind+" "+Parallel[1]/totalWind);
    System.out.println(t+" seconds");
  }

  private static void tick(){
    start = System.currentTimeMillis();
  }

  private static float tock(){
    return (System.currentTimeMillis()-start)/1000.0f;
  }

  static final ForkJoinPool fjPool = new ForkJoinPool();
	static float Par(float[] arr){
	  return fjPool.invoke(new ParallelCloud(arr,0,arr.length));
	}

}
