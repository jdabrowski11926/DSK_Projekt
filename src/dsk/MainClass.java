package dsk;


import dsk.ram_error.Af;
import dsk.ram_error.Cf;

import dsk.test.TestAlgorithm;

public class MainClass {
		
	public static void main(String[] args) {		

		Ram ram = new Ram(10);
		
		
		ram.addError(new Af(ram,5,5));
	// Iloœci b³êdów
		int errorsSAF = 10;
		int errorsSOF = 10;
		int errorsDRF = 10;
		int errorsTF = 10;
		int errorsCF = 10;
		int errorsPSF = 3;
		TestAlgorithm testAlgorithm = new TestAlgorithm(ram);
		testAlgorithm.test();
		
		ram.randomRam();
		ram.showRam();
		ram.damageRam(errorsSAF, errorsSOF, errorsDRF, errorsTF, errorsCF, errorsPSF);
		
	}
}
