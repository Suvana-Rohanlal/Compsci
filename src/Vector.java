import java.lang.Math;

public class Vector{

	float x,y;
	float length = 0;

	public Vector(){
		this.x = 0.0f;
		this.y = 0.0f;
	}

	public Vector(float x, float y){
		this.x = x;
		this.y = y;
	}

	public float length(float x, float y){
		length = (float) (Math.sqrt((x*x)+(y*y)));
		return length;
	}

	public float getXValue(){
		return x;
	}

	public float getYValue(){
		return y;
	}

	public void setXValue(float x){
		this.x = x;
	}

	public void setYValue(float y){
		this.y = y;
	}
}
