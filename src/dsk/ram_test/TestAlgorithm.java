package dsk.ram_test;

import java.util.ArrayList;
import java.util.List;
import dsk.ram.Ram;

public abstract class TestAlgorithm {

	protected Ram ram;
	protected long counter;

	enum Direction {
		UP, DOWN
	}

	enum Operation {
		WRITE_0, WRITE_1, READ_0, READ_1
	}

	public TestAlgorithm(Ram ram) {
		this.ram = ram;
		counter = 0;
	}

	public abstract List<ErrorLog> test();

	protected void writeRam(int x, int y, boolean value) {
		counter++;
		ram.write(x, y, value);
	}

	protected boolean readRam(int x, int y) {
		counter++;
		return ram.read(x, y);
	}

	protected List<ErrorLog> runAlgorithmStep(Direction dir, List<Operation> ops) {
		List<ErrorLog> result = new ArrayList<ErrorLog>();
		switch (dir) {
		case DOWN:
			for (int i = this.ram.getRamSizeX()-1; i >= 0; i--) {
				for (int j =  this.ram.getRamSizeY()-1; j >= 0; j--) {
					for (Operation op : ops) {
						switch (op) {
						case WRITE_0:
							writeRam(i, j, false);
							break;
						case WRITE_1:
							writeRam(i, j, true);
							break;
						case READ_0:
							if (readRam(i, j) == true) {
								result.add(new ErrorLog(i, j, true));
								System.out.println("Unexpected 1 at [" + i + ", " + j + "]");
							}
							break;
						case READ_1:
							if (readRam(i, j) == false) {
								result.add(new ErrorLog(i, j, false));
								System.out.println("Unexpected 0 at [" + i + ", " + j + "]");
							}
							break;
						}
					}

				}
			}
			break;
		case UP:
			for (int i = 0; i < this.ram.getRamSizeX(); i++) {
				for (int j = 0; j < this.ram.getRamSizeY(); j++) {

					for (Operation op : ops) {
						switch (op) {
						case WRITE_0:
							writeRam(i, j, false);
							break;
						case WRITE_1:
							writeRam(i, j, true);
							break;
						case READ_0:
							if (readRam(i, j) == true) {
								result.add(new ErrorLog(i, j, true));
								System.out.println("Unexpected 1 at [" + i + ", " + j + "]");
							}
							break;
						case READ_1:
							if (readRam(i, j) == false) {
								result.add(new ErrorLog(i, j, false));
								System.out.println("Unexpected 0 at [" + i + ", " + j + "]");
							}
							break;
						}
					}

				}
			}
			break;
		}
		return result;
	}

}
