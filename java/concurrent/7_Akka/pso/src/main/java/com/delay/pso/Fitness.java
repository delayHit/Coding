package com.delay.pso;

import java.lang.Math;
import java.util.List;

public class Fitness{
	public static double fitness(List<Double> x){
		double sum=0;
		for(int i=1;i<x.size();i++){
			sum+=Math.sqrt(x.get(i));
		}
		return sum;
	}
}
