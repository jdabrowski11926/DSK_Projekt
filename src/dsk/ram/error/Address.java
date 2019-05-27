package dsk.ram.error;

public class Address implements Comparable<Address>{
	private int x;
	private int y;
	
	public Address(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Address) {
			Address a = (Address)obj;
			return x == a.getX() && y == a.getY();
		}
		return super.equals(obj);
	}
	
	
	@Override
	public String toString() {
		return "["+x+" "+y+"]";
	}


	@Override
	public int compareTo(Address o) {
		int xCompare = x-o.getX();
		if(xCompare != 0) {
			return xCompare;
		}
		return y-o.getY();
	}

}
