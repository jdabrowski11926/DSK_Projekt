package dsk.ram_error;

import dsk.Ram;

public class Saf extends ErrorTemplate{
	public Saf(Ram ram, int x, int y) {
		super(ram, x, y);
	}

	@Override
	public boolean getRam(int x, int y) {
		return ram.getRam(x, y);
	}

	@Override
	public void setRam(int x, int y, boolean value) {
		//DO NOTHING
	}
}