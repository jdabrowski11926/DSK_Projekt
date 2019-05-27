package dsk.ram.error;

import java.util.Random;

import dsk.ram.Ram;

public class Af extends RamError{

	public Af(Ram ram, int x, int y) {
		super(ram, x, y);
	}

	@Override
	public boolean getRam(int x, int y) {
	// Pobranie z komórki s¹siaduj¹cej
		Random random = new Random();
		int fate = random.nextInt(4);
		boolean neighborValue = false;
		System.out.println("x = "+x+ " y = "+y+ ram.getRamSizeX());
		if(fate==0) {	// Komórka ni¿ej
			if(x>=ram.getRamSizeX()-1) {
				neighborValue = ram.getRam(x-1, y);
			}
			else {
				neighborValue = ram.getRam(x+1, y);
			}
		}
		if(fate==1) {	// Komórka wy¿ej
			if(x==0) {
				neighborValue = ram.getRam(x+1, y);
			}
			else{
				neighborValue = ram.getRam(x-1, y);
			}
		}
		if(fate==2) {	// Komórka z lewej
			if(y==0) {
				neighborValue = ram.getRam(x, y+1);
			}
			else {
				neighborValue = ram.getRam(x, y-1);
			}
		}
		if(fate==3) {	// Komórka z prawej
			if(y>=ram.getRamSizeY()-1) {
				neighborValue = ram.getRam(x, y-1);
			}
			else {
				neighborValue = ram.getRam(x, y+1);
			}
		}
		boolean baseValue = ram.getRam(x, y);
	// Operacja sumy lub iloczynu
		fate = random.nextInt(3);
		if(fate == 0) return neighborValue | baseValue;
		if(fate == 1) return neighborValue & baseValue;
		return false;
	}

	@Override
	public void setRam(int x, int y, boolean value) {
		ram.setRam(x, y, value);
	}

	@Override
	public String getName() {
		return "AF";
	}

}
