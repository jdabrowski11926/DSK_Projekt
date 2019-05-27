package dsk.ram_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dsk.ram.Ram;
import dsk.ram_test.RamTestAlgorithm.Direction;
import dsk.ram_test.RamTestAlgorithm.Operation;

public class MatsPlusPlus extends RamTestAlgorithm{

	public MatsPlusPlus() {
		super();
	}

	@Override
	public List<ErrorLog> test(Ram ram) {
		stepCounter = 0;
		List<ErrorLog> result = new ArrayList<ErrorLog>();
		runAlgorithmStep(Direction.UP, Arrays.asList(Operation.WRITE_0), ram);
		runAlgorithmStep(Direction.UP, Arrays.asList(Operation.READ_0, Operation.WRITE_1), ram);
		runAlgorithmStep(Direction.DOWN, Arrays.asList(Operation.READ_1,Operation.WRITE_0, Operation.READ_0), ram);
		return result;
	}

}