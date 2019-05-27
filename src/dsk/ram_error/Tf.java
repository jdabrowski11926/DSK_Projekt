package dsk.ram_error;

import dsk.ram.Ram;

public class Tf extends RamError{
	public enum Direction{
		ZERO_TO_ONE, ONE_TO_ZERO
	}

	Direction disabledDirection;

	public Tf(Ram ram, int x, int y, Direction disabledDirection) {
		super(ram, x, y);
		this.disabledDirection = disabledDirection;
	}

	@Override
	public boolean getRam(int x, int y) {
		return ram.getRam(x, y);
	}

	@Override
	public void setRam(int x, int y, boolean value) {		
		switch (disabledDirection) {
		case ZERO_TO_ONE:
			if(value == false) { // Mozna ustawiac na 0
				ram.setRam(x, y, value);
			}
			break;
		case ONE_TO_ZERO:
			if(value == true) { // Mozna ustawiac na 1
				ram.setRam(x, y, value);
			}
			break;
		}
		
	}

}
