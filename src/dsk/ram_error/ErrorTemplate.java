package dsk.ram_error;

import dsk.Ram;

public abstract class ErrorTemplate {
	protected Ram ram;
	private int x;
	private int y;
	
	public ErrorTemplate(Ram ram, int x, int y) {
		this.ram = ram;
	}
	
	public boolean affectsXY(int x, int y) {
		return this.x==x && this.y==y;
	}
	public abstract boolean getRam(int x, int y);
	public abstract void setRam(int x, int y, boolean value);
}
