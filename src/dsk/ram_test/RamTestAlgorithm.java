package dsk.ram_test;

import java.util.ArrayList;
import java.util.List;
import dsk.ram.Ram;

public abstract class RamTestAlgorithm {

	protected long stepCounter;

	enum Direction {
		UP, DOWN
	}

	enum Operation {
		WRITE_0, WRITE_1, READ_0, READ_1
	}

	public RamTestAlgorithm() {
		stepCounter = 0;
	}

	public abstract List<ErrorLog> test(Ram ram);

	protected void writeRam(int x, int y, boolean value, Ram ram) {
		stepCounter++;
		ram.write(x, y, value);
	}

	protected boolean readRam(int x, int y, Ram ram) {
		stepCounter++;
		return ram.read(x, y);
	}

	public long getStepCounter() {
		return stepCounter;
	}

	protected List<ErrorLog> runAlgorithmStep(Direction dir, List<Operation> ops, Ram ram) {
		List<ErrorLog> result = new ArrayList<ErrorLog>();
		switch (dir) {
		case DOWN:
			for (int i = ram.getRamSizeX()-1; i >= 0; i--) {
				for (int j =  ram.getRamSizeY()-1; j >= 0; j--) {
					for (Operation op : ops) {
						switch (op) {
						case WRITE_0:
							writeRam(i, j, false, ram);
							break;
						case WRITE_1:
							writeRam(i, j, true, ram);
							break;
						case READ_0:
							if (readRam(i, j, ram) == true) {
								result.add(new ErrorLog(i, j, true));
								System.out.println("Unexpected 1 at [" + i + ", " + j + "]");
							}
							break;
						case READ_1:
							if (readRam(i, j, ram) == false) {
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
			for (int i = 0; i < ram.getRamSizeX(); i++) {
				for (int j = 0; j < ram.getRamSizeY(); j++) {

					for (Operation op : ops) {
						switch (op) {
						case WRITE_0:
							writeRam(i, j, false, ram);
							break;
						case WRITE_1:
							writeRam(i, j, true, ram);
							break;
						case READ_0:
							if (readRam(i, j, ram) == true) {
								result.add(new ErrorLog(i, j, true));
								System.out.println("Unexpected 1 at [" + i + ", " + j + "]");
							}
							break;
						case READ_1:
							if (readRam(i, j, ram) == false) {
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
