package dsk.ram.test_algorithm;



public class ErrorLog {
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
				   this.y == el.y &&
				   this.value == el.value;
		}
		return super.equals(obj);
	}
}
