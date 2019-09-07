public class ParallelCloud{

  public static void main(String[]args){
      Vector vec = new Vector();
      CloudData cloud = new CloudData();

      Scanner sc = new Scanner(System.in);
      System.out.println("Please enter the name of the file");
      String name = sc.nextLine();
      cloud.readData(name);


      sc.close();
  }

  int sumOfX(Float[][][] arr, int time){
    Float[] resX = new Float[time];
    Float[] resY = new Float[time];
    int length = arr.length;
    FORALL(i=0; i<4; i++){
      resX[i] = sumRange(arr, i*length/time, (i+1)*length/time);
      resY[i] = sumRange(arr, i*length/time, (i+1)*length/time);
    }
    return resX[];
  }



  int sumRangeX(int[][][] arr, int low, int high){
    result =0;
    for(j=low; j<high; j++){
      result += arr[j];
    }
    return result;
  }

  int sumRangeY(int[][][] arr, int low, int high){
    result =0;
    for(j=low; j<high; j++){
      result += arr[j];
    }
    return result;
  }

}
