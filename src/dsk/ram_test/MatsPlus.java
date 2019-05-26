package dsk.ram_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dsk.ram.Ram;


public class MatsPlus extends TestAlgorithm{

	public MatsPlus(Ram ram) {
		super(ram);
	}

	@Override
	public List<ErrorLog> test() {
		List<ErrorLog> result = new ArrayList<ErrorLog>();
		runAlgorithmStep(Direction.UP, Arrays.asList(Operation.WRITE_0));
		runAlgorithmStep(Direction.UP, Arrays.asList(Operation.READ_0, Operation.WRITE_1));
		runAlgorithmStep(Direction.DOWN, Arrays.asList(Operation.READ_1,Operation.WRITE_0));
		return result;
		
	}

}