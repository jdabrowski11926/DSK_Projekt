package dsk.ram_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dsk.ram.Ram;


public class Mats extends TestAlgorithm{
	public Mats(Ram ram) {
		super(ram);
	}

	@Override
	public List<ErrorLog> test() {
		List<ErrorLog> result = new ArrayList<ErrorLog>();
		result.addAll( runAlgorithmStep(Direction.UP, Arrays.asList(Operation.WRITE_0)) );
		result.addAll( runAlgorithmStep(Direction.UP, Arrays.asList(Operation.READ_0, Operation.WRITE_1)) );
		result.addAll( runAlgorithmStep(Direction.DOWN, Arrays.asList(Operation.READ_1)) );
		return result;
	}

}
