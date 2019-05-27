package dsk.ram.stats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import dsk.ram.error.Address;
import dsk.ram.error.RamError;
import dsk.ram.test_algorithm.RamTestAlgorithm;

public class TestLog {
	private RamTestAlgorithm testAlgorithm;
	private List<RamError> ramErrors;
	
	private List<ErrorLog> errorLogs = new ArrayList<ErrorLog>();
	
	
	public TestLog(RamTestAlgorithm testAlgorithm, List<RamError> ramErrors) {
		this.testAlgorithm = testAlgorithm;
		this.ramErrors = ramErrors;
	}
	
	public TestLog(RamTestAlgorithm testAlgorithm, RamError ramError) {
		this.testAlgorithm = testAlgorithm;
		this.ramErrors = Arrays.asList(ramError);
	}

	public void addErrorLog(ErrorLog errorLog) {
		errorLogs.add(errorLog);
	}
	
	public void addErrorLogs(List<ErrorLog> errorLog) {
		errorLogs.addAll(errorLog);
	}
	
	public RamTestAlgorithm getTestAlgorithm() {
		return testAlgorithm;
	}

	public List<ErrorLog> getErrorLogs() {
		return new ArrayList<ErrorLog>(errorLogs);
	}

	public List<ErrorLog> getUniqueErrorLogs(){
		return new ArrayList<ErrorLog>(
				new TreeSet<ErrorLog>(errorLogs)
		);
	}
	
	public List<Address> getAllAffectedAddresses(){
		Set<Address> result = new TreeSet<Address>();
		for(RamError re : ramErrors) {
			result.addAll(re.getAffectedAdresses());
		}
		return new ArrayList<Address>(result);
	}
	
	public List<Address> getAllDetectedAdresses(){
		Set<Address> result = new TreeSet<Address>();
		for(ErrorLog el : errorLogs) {
			result.add(el.getAddress());
		}
		return new ArrayList<Address>(result);
	}
	
	public List<String> getAllUniqueErrorTypes(){
		TreeSet<String> result = new TreeSet<String>();
		for(RamError e : ramErrors) {
			result.add(e.getName());
		}
		return new ArrayList<String>(result);
	}
	
	public String summary() {
		String result = "Testa algorithm: " + testAlgorithm.getName() + "\n";
		
		List<Address> affectedAddresses = getAllAffectedAddresses();
		result += "\tAll addresses affected by errors ("+affectedAddresses.size()+"):\n\t\t";
		
		for(Address a : affectedAddresses) {
			result += a.toString() + " ";
		}
		
		List<Address> detectedAddresses = getAllDetectedAdresses();
		result += "\n\tAll detected addresses affected by errors ("+detectedAddresses.size()+"):\n\t\t";
		
		for(Address a : detectedAddresses) {
			result += a.toString() + " ";
		}
		result += "\n\tSuccess ratio: "+detectedAddresses.size()+"/"+affectedAddresses.size();
		result += "\n\tError types: ";
		for(String s : getAllUniqueErrorTypes()) {
			result += s + " ";
		}
		
		return result;
	}
	
	public String shortSummary() {
		List<Address> affectedAddresses = getAllAffectedAddresses();
		List<Address> detectedAddresses = getAllDetectedAdresses();
		String result = testAlgorithm.getName() + ": " + detectedAddresses.size()+"/"+affectedAddresses.size() + " [";
		for(String s : getAllUniqueErrorTypes()) {
			result += s + " ";
		}
		result += "]";
		return result;
	}
	
	public List<RamError> getRamErrors() {
		return new ArrayList<RamError>(ramErrors);
	}
	
	
}
