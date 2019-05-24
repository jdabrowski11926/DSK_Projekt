package dsk.ram_error;

import dsk.Ram;

public class Lcf extends ErrorTemplate{

	
	public Lcf(Ram ram, int x, int y) {
		super(ram, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean getRam(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRam(int x, int y, boolean value) {
		// TODO Auto-generated method stub
		
	}



}
