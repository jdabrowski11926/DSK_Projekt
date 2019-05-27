package dsk.ram.error;

import dsk.ram.Ram;

public class Sof extends RamError{

	private boolean returnValue;

	public Sof(Ram ram, int x, int y) {
		super(ram, x, y);
		returnValue = ram.getRam(x, y);
	}

	@Override
	public boolean getRam(int x, int y) {
		return returnValue;
	}

	@Override
	public void setRam(int x, int y, boolean value) {
		ram.setRam(x, y, value);
	}

	@Override
	public String getName() {
		return "SOF";
	}


}
