package dsk.ram.stats;

import dsk.ram.error.Address;

public class ErrorLog implements Comparable<ErrorLog>{
	private int x;
	private int y;
	private boolean value;
	
	public ErrorLog(int x, int y, boolean value) {
		super();
		this.x = x;
		this.y = y;
		this.value = value;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isValue() {
		return value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ErrorLog) {
			ErrorLog el = (ErrorLog)obj;
			return this.x == el.x &&
				   this.y == el.y;
		}
		return super.equals(obj);
	}
	
	public Address getAddress() {
		return new Address(x, y);
	}

	@Override
	public int compareTo(ErrorLog o) {
		int xCompare = x-o.getX();
		if(xCompare != 0) {
			return xCompare;
		}
		return y-o.getY();
	}
}
