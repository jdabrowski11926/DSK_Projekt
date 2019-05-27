package dsk.ram.test_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dsk.ram.error.RamError;

public class TestLog {
	private RamTestAlgorithm testAlgorithm;
	private List<ErrorLog> errorLogs;
	private List<RamError> ramErrors;
	
	public TestLog(RamTestAlgorithm testAlgorithm, List<ErrorLog> errors, List<RamError> ramErrors) {
		this.testAlgorithm = testAlgorithm;
		this.errorLogs = errors;
		this.ramErrors = ramErrors;
	}
	
	public TestLog(RamTestAlgorithm testAlgorithm, List<ErrorLog> errors, RamError ramError) {
		this.testAlgorithm = testAlgorithm;
		this.errorLogs = errors;
		this.ramErrors = Arrays.asList(ramError);
	}

	public RamTestAlgorithm getTestAlgorithm() {
		return testAlgorithm;
	}

	public List<ErrorLog> getErrorLogs() {
		return new ArrayList<ErrorLog>(errorLogs);
	}

	public List<RamError> getRamErrors() {
		return new ArrayList<RamError>(ramErrors);
	}
	
	
}
