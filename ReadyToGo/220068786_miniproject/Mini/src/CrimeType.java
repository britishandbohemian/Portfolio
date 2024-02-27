import com.jwetherell.algorithms.data_structures.Graph.Vertex;

public class CrimeType<T extends Comparable<T>> extends Vertex<T>{
	
	private String crimeType;
	private double X;
	private double Y;
	
	

	public CrimeType(T value , String crimeType) {
		super(value);
		this.crimeType = crimeType;
		this.X = 0;
		this.Y = 0;

	}

	public CrimeType(T value , String crimeType, int weight) {
		super(value,weight);
		this.crimeType = crimeType;

	}


	public String getCrimeType() {
		return crimeType;
	}




	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}

	public void setCrimeType(String crimeType) {
		this.crimeType = crimeType;
	}

	@Override
	public String toString() {
		return "CrimeType [crimeType=" + crimeType + ", getCrimeType()=" + getCrimeType() + ", getValue()=" + getValue()
				+ ", getWeight()=" + getWeight() + "]";
	}


	


	



}
