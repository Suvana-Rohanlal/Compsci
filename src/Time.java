public class Time{
  static long start = 0;

  public static void tick(){
    start = System.currentTimeMillis();
  }

  public static float tock(){
    return (System.currentTimeMillis()-start)/1000.0f;
  }
}
