package dsk.ram.test_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dsk.ram.Ram;
import dsk.ram.error.RamError;

public class AlgorithmTester {
	private List<RamError> errors;
	private List<RamTestAlgorithm> algorithms;
	private Ram ram;
	private List<TestLog> testLogs;
	
	private int[] affectedCellCount;
	private int[][] detectedCellCount;
	
	
	public AlgorithmTester(Ram ram, List<RamError> errors, List<RamTestAlgorithm> algorithms) {
		this.ram = ram;
		this.errors = errors;
		this.algorithms = algorithms;
		
		affectedCellCount = new int[errors.size()];
		for(int i=0;i<errors.size(); i++) {
			affectedCellCount[i] = errors.get(i).getAffectedAdresses().size();
		}
		detectedCellCount = new int[algorithms.size()][errors.size()];
		testLogs = test();
	}
	
	public String[] getRowLabels() {
		ArrayList<String> columns = new ArrayList<String>();
		for(RamTestAlgorithm a : algorithms) {
			columns.add(a.getName());
		}
		return columns.toArray(new String[columns.size()]);
	}
	
	public String[] getColumnLabels() {
		ArrayList<String> rows = new ArrayList<String>();
		for(RamError e : errors) {
			rows.add(e.getName());
		}
		return rows.toArray(new String[rows.size()]);
	}
	
	
	
	public int[] getAffectedCellCount() {
		return affectedCellCount;
	}

	public int[][] getDetectedCellCount() {
		return detectedCellCount;
	}

	private List<TestLog> test() {
		List<TestLog> testLogs = new ArrayList<TestLog>();
		ram.clearErrors();
		int errorId = 0;
		for(RamError error : errors) {
			ram.addError(error);
			int algorithmId = 0;
			for(RamTestAlgorithm algorithm : algorithms) {
				TestLog tl = new TestLog(algorithm, error);
				tl.addErrorLogs(algorithm.test(ram));
				testLogs.add(tl);
				detectedCellCount[algorithmId][errorId] = tl.getUniqueErrorLogs().size();
				algorithmId++;
			}
			ram.clearErrors();
			errorId++;
		}
		return testLogs;
	}

	
	
	public List<TestLog> getTestLogs() {
		return testLogs;
	}

	@Override
	public String toString() {
		String array2d = "[\n";
		int[][] detectedCells = getDetectedCellCount();
		for(int i=0;i<detectedCells.length;i++) {
			array2d += "[ ";
			for(int j=0;j<detectedCells[i].length;j++) {
				array2d += detectedCells[i][j] + " ";
			}
			array2d += "]\n";
		}
		array2d += "]\n";
		
		return "AlgorithmTester [\nColumns" + Arrays.toString(getColumnLabels()) + ", \nRows="
				+ Arrays.toString(getRowLabels()) + ", \nAffected cells="
				+ Arrays.toString(getAffectedCellCount()) + ", \nDetected cells="
				+ array2d + "]";
	}
	
	
	
	
}
