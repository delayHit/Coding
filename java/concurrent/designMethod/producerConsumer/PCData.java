import java.util.Random;
import java.util.concurrent.atomic.*;
import java.util.concurrent.*;
public final class PCData{
	private final int intData;

	public PCData(int d){
		intData = d;
	}

	public PCData(String d){
		intData = Integer.valueOf(d);
	}

	public int getData(){return intData;}

	@Override
	public String toString(){return "data:" + intData;}
}
