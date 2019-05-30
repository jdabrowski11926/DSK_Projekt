package dsk.ram;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;
import java.util.TreeMap;

import dsk.ram.error.RamError;

public class Ram {
	private short[] memory;
	public static final int RAM_SIZE_Y = Short.SIZE;
	
	private ArrayList<RamError> errors = new ArrayList<RamError>();
	
	public Ram(int size){
		memory = new short[size];
	}
	
	public void randomRam() {
		Random generator = new Random();
		for(int i=0;i<getRamSizeX();i++) {
			memory[i] = (short)generator.nextInt(1 << 16); // any short
		}
		//generator.nextBytes(memory);
	}
	
	public void showRam() {
		for(int i=0;i<memory.length; i++) {
			BitOperations.printBinary(memory[i]);
		}
	}
		
	// Interfejs porgramistyczny do obs³ugi klasy
	public RamError getError(int x, int y) {
		for(RamError error: errors) {
			if(error.affectsXY(x, y)) return error;
		}
		return null;
	}
	
	public void setRam(int x, int y, boolean value) {
		if(x >= memory.length || y >= RAM_SIZE_Y) {
			throw new IndexOutOfBoundsException();
		}
		memory[x] = BitOperations.setBit(memory[x], y, value);
	}
	
	public boolean getRam(int x, int y) {
		if(x >= memory.length || y >= RAM_SIZE_Y) {
			throw new IndexOutOfBoundsException();
		}
		return BitOperations.getBit(memory[x], y);
	}
	
	public void addError(RamError et) {
		this.errors.add(et);
	}
	
	public void clearErrors() {
		this.errors.clear();
	}
	
	// Operacje mogace nie zadzialac (wykorzystane w symulacji)
	public void write(int x, int y, boolean value) {
		RamError error = getError(x, y);
		if(error != null) {
			error.setRam(x, y, value);
		}else {
			setRam(x, y, value);
		}
	}
	
	public boolean read(int x, int y) {
		RamError error = getError(x, y);
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
		return RAM_SIZE_Y;
	}
}
