import com.jwetherell.algorithms.data_structures.Graph.Vertex;

public class Demographic<T extends Comparable<T>> extends Vertex<T>{ 
	
	
	//Geographic Location
	private String Area;
	private double areaPopulation;
	private double areaRate;
	private double Y;
	private double X;
	
     public Demographic(T value, String Area, double areaPopulation, double areaRate) {
		super(value);
		this.Area = Area;
		this.areaPopulation = areaPopulation;
		this.areaRate = areaRate;
		this.X =0;
		this.Y = 0;
		
	}
	
     public Demographic(T value, String Area, double areaPopulation, double areaRate, int weight) {
 		super(value,weight);
 		this.Area = Area;
 		this.areaPopulation = areaPopulation;
 		this.areaRate = areaRate;
 		this.X =0;
		this.Y = 0;
 		
 	}
     
 	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}
	
	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}

	public double getAreaPopulation() {
		return areaPopulation;
	}

	public void setAreaPopulation(double areaPopulation) {
		this.areaPopulation = areaPopulation;
	}

	public double getAreaRate() {
		return areaRate;
	}

	public void setAreaRate(double areaRate) {
		this.areaRate = areaRate;
	}

	@Override
	public String toString() {
		return "Demographic [Area=" + Area + ", areaPopulation=" + areaPopulation + ", areaRate=" + areaRate
				+ ", getArea()=" + getArea() + ", getAreaPopulation()=" + getAreaPopulation() + ", getAreaRate()="
				+ getAreaRate() + ", getValue()=" + getValue() + ", getWeight()=" + getWeight() + "]";
	}
     
   
	
}
