package dsk.test;

import java.util.Arrays;
import java.util.List;

import dsk.Ram;

public class TestAlgorithm {

	Ram ram;
	long counter;

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

	public void test() {
		// W(0) [R(0),W(1)] R(1)
		MATS();
		//MARCHX();
	}

	public void writeRam(int x, int y, boolean value) {
		counter++;
		ram.write(x, y, value);
	}

	public boolean readRam(int x, int y) {
		counter++;
		return ram.read(x, y);
	}

	public void W(boolean value, boolean forward) {
		if (forward) {
			for (int i = 0; i < this.ram.getRamSizeX(); i++) {
				for (int j = 0; j < this.ram.getRamSizeY(); j++) {
					writeRam(i, j, value);
				}
			}
		} else {
			for (int i = this.ram.getRamSizeX() - 1; i >= 0; i--) {
				for (int j = this.ram.getRamSizeY() - 1; j >= 0; j--) {
					writeRam(i, j, value);
				}
			}
		}
	}

	public void R(boolean forward, boolean expectedValue) {
		if (forward) {
			for (int i = 0; i < this.ram.getRamSizeX(); i++) {
				for (int j = 0; j < this.ram.getRamSizeY(); j++) {
					if (readRam(i, j) != expectedValue) {
						System.out.println("Unexpected " + !expectedValue + " at [" + i + ", " + j + "]");
					}
				}
			}
		} else {
			for (int i = this.ram.getRamSizeX() - 1; i >= 0; i--) {
				for (int j = this.ram.getRamSizeY() - 1; j >= 0; j--) {
					if (readRam(i, j) != expectedValue) {
						System.out.println("Unexpected " + !expectedValue + " at [" + i + ", " + j + "]");
					}
				}
			}
		}
	}

	public void runAlgorithmStep(Direction dir, List<Operation> ops) {
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
								System.out.println("Unexpected 1 at [" + i + ", " + j + "]");
							}
							break;
						case READ_1:
							if (readRam(i, j) == false) {
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
								System.out.println("Unexpected 1 at [" + i + ", " + j + "]");
							}
							break;
						case READ_1:
							if (readRam(i, j) == false) {
								System.out.println("Unexpected 0 at [" + i + ", " + j + "]");
							}
							break;
						}
					}

				}
			}
			break;
		}
	}

	public void MATS() {
		//|W(0) ^[R(0),W(1)] vR(1)
		runAlgorithmStep(Direction.UP, Arrays.asList(Operation.WRITE_0));
		runAlgorithmStep(Direction.UP, Arrays.asList(Operation.READ_0, Operation.WRITE_1));
		runAlgorithmStep(Direction.DOWN, Arrays.asList(Operation.READ_1));
	}
	
	public void MATS_PLUS() {
		//|W(0) ^[R(0),W(1)] vR(1)
		runAlgorithmStep(Direction.UP, Arrays.asList(Operation.WRITE_0));
		runAlgorithmStep(Direction.UP, Arrays.asList(Operation.READ_0, Operation.WRITE_1));
		runAlgorithmStep(Direction.DOWN, Arrays.asList(Operation.READ_1,Operation.WRITE_0));
	}
	
	public void MARCHX() {
		//|W(0) ^[R(0),W(1)] vR(1)
		runAlgorithmStep(Direction.UP, Arrays.asList(Operation.WRITE_0));
		runAlgorithmStep(Direction.UP, Arrays.asList(Operation.READ_0, Operation.WRITE_1));
		runAlgorithmStep(Direction.DOWN, Arrays.asList(Operation.READ_1,Operation.WRITE_0));
		runAlgorithmStep(Direction.UP, Arrays.asList(Operation.READ_0));
	}

}
