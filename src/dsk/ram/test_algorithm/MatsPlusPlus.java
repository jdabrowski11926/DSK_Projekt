package dsk.ram.test_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dsk.ram.Ram;
import dsk.ram.test_algorithm.RamTestAlgorithm.Direction;
import dsk.ram.test_algorithm.RamTestAlgorithm.Operation;

public class MatsPlusPlus extends RamTestAlgorithm{

	public MatsPlusPlus() {
		super();
	}

	@Override
	public List<ErrorLog> test(Ram ram) {
		stepCounter = 0;
		List<ErrorLog> result = new ArrayList<ErrorLog>();
		result.addAll( runAlgorithmStep(Direction.UP, Arrays.asList(Operation.WRITE_0), ram) );
		result.addAll( runAlgorithmStep(Direction.UP, Arrays.asList(Operation.READ_0, Operation.WRITE_1), ram) );
		result.addAll( runAlgorithmStep(Direction.DOWN, Arrays.asList(Operation.READ_1,Operation.WRITE_0, Operation.READ_0), ram) );
		return result;
	}

	@Override
	public String getName() {
		return "MATS++";
	}

}