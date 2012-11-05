
package controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;


public class RungeKutta {
	
	
	public class PointInTime{
		private Double t;
		private ArrayList<Double> value;

		public PointInTime(Double t, ArrayList<Double> value) {
			this.t = t;
			this.value = value;
		}

		public Double getTime() {
			return t;
		}

		public void setTime(Double t) {
			this.t = t;
		}

		public ArrayList<Double> getValue() {
			return value;
		}

		public void setValue(ArrayList<Double> value) {
			this.value = value;
		}
		
	}
	
	public interface Function{
		ArrayList<Double> evaluate(PointInTime point);
	}
	
	private Function df;
	private PointInTime initialPoint;

	

	public RungeKutta(Function df, PointInTime initialPoint) {
		this.df = df;
		this.initialPoint = initialPoint;
	}
	
	
	public PointInTime execute(Double desiredTime, int steps){
		PointInTime result=initialPoint;
		Double h = desiredTime/steps;
		
		ArrayList<Double> k1 = multiply(h,df.evaluate(initialPoint));
		ArrayList<Double> k2 = new ArrayList<Double>(k1.size());
		ArrayList<Double> k3 = new ArrayList<Double>(k1.size());
		ArrayList<Double> k4 = new ArrayList<Double>(k1.size());
		
		for (int i=0;i<steps;i++){
			
			result = new PointInTime(result.getTime()+h,sum(k1,sum(multiply(2.0, k2),sum(multiply(2.0,k3),k4))));
			k1 = multiply(h,df.evaluate(initialPoint));
		}
		
		return result;
	}
	
	private ArrayList<Double> multiply(Double c, ArrayList<Double> x){
		ArrayList<Double> result = new ArrayList<Double>(x.size());
		for (int i=0;i<x.size();i++){
			result.set(i,x.get(i)*c);
		}
		return result;
			
	}
		
	private ArrayList<Double> sum(ArrayList<Double> x, ArrayList<Double> y){
		assert(x.size()==y.size());
		ArrayList<Double> result = new ArrayList<Double>(x.size());
		for (int i=0;i<x.size();i++){
			result.set(i,x.get(i)+y.get(i));
		}
		return result;
			
	}
	
	
	

}
