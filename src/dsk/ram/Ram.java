package dsk.ram;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;
import java.util.TreeMap;

import dsk.ram_error.ErrorTemplate;

public class Ram {
	private byte[] memory;
	private ArrayList<ErrorTemplate> errors = new ArrayList<ErrorTemplate>();

	
	public Ram(int size){
		memory = new byte[size];
	}
	
	public void randomRam() {
		Random generator = new Random();
		generator.nextBytes(memory);
	}
	
	public void showRam() {
		for(int i=0;i<memory.length; i++) {
			BitOperations.printBinary(memory[i]);
		}
	}
	
	public void damageRam(int SAF, int SOF, int DRF, int TF, int CF, int PSF) {
		for(int i=0;i<SAF;i++) {
			
		}
		for(int i=0;i<SOF;i++) {
			
		}
		for(int i=0;i<DRF;i++) {
			
		}
		for(int i=0;i<TF;i++) {
			
		}
		for(int i=0;i<CF;i++) {
			
		}
		for(int i=0;i<PSF;i++) {
			
		}
	}
	
	// Interfejs porgramistyczny do obs³ugi klasy
	public ErrorTemplate getError(int x, int y) {
		for(ErrorTemplate error: errors) {
			if(error.affectsXY(x, y)) return error;
		}
		return null;
	}
	
	public void setRam(int x, int y, boolean value) {
		if(x >= memory.length || y >= 8) {
			throw new IndexOutOfBoundsException();
		}
		memory[x] = BitOperations.setBit(memory[x], y, value);
	}
	
	public boolean getRam(int x, int y) {
		if(x >= memory.length || y >= 8) {
			throw new IndexOutOfBoundsException();
		}
		return BitOperations.getBit(memory[x], y);
	}
	
	public void addError(ErrorTemplate et) {
		this.errors.add(et);
	}
	
	// Operacje mogace nie zadzialac (wykorzystane w symulacji)
	public void write(int x, int y, boolean value) {
		ErrorTemplate error = getError(x, y);
		if(error != null) {
			error.setRam(x, y, value);
		}else {
			setRam(x, y, value);
		}
	}
	
	public boolean read(int x, int y) {
		ErrorTemplate error = getError(x, y);
		if(error != null) {
			return error.getRam(x, y);
		}else {
			return getRam(x, y);
		}
	}
	
	public int getRamSizeX() {
		return memory.length;
	}
	
	public int getRamSizeY() {
		return 8;
	}
}
