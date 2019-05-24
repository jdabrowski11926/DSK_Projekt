package dsk.ram_error;

import dsk.Ram;

public class Tf extends ErrorTemplate{
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRam(int x, int y, boolean value) {
		switch (disabledDirection) {
		case ZERO_TO_ONE:
			
			break;
		case ONE_TO_ZERO:
			
			break;
		}
		ram.setRam(x, y, value);
	}

}
