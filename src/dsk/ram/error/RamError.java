package dsk.ram.error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dsk.ram.Ram;

public abstract class RamError {
	protected Ram ram;
	protected int x;
	protected int y;
	
	public RamError(Ram ram, int x, int y) {
		this.x = x;
		this.y = y;
		this.ram = ram;
	}
	
	public boolean affectsXY(int x, int y) {
		return this.x==x && this.y==y;
	}
	public abstract boolean getRam(int x, int y);
	public abstract void setRam(int x, int y, boolean value);
	public abstract String getName();
	
	public List<Address> getAffectedAdresses(){
		return Arrays.asList(new Address(x, y));
	}
}
