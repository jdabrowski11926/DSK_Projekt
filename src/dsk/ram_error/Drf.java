package dsk.ram_error;

import java.util.Random;

import dsk.ram.Ram;

public class Drf extends RamError{
	private int timeToActivate;
	private int minTimeToActivate;
	private int maxTimeToActivate;
	private Random randomizer = new Random();

	public Drf(Ram ram, int x, int y, int minTimeToActivate, int maxTimeToActivate) {
		super(ram, x, y);
		this.minTimeToActivate = minTimeToActivate;
		this.maxTimeToActivate = maxTimeToActivate;
		this.timeToActivate =  minTimeToActivate + randomizer.nextInt(
				maxTimeToActivate-minTimeToActivate
		);
	}

	@Override
	public boolean getRam(int x, int y) {
		timeToActivate--;
		if(timeToActivate <= 0) {
			ram.setRam(x, y, !ram.getRam(x, y));
			timeToActivate = randomizer.nextInt(
				maxTimeToActivate-minTimeToActivate
			);
		}
		return ram.getRam(x, y);
	}

	@Override
	public void setRam(int x, int y, boolean value) {
		timeToActivate = randomizer.nextInt(
				maxTimeToActivate-minTimeToActivate
		);
		ram.setRam(x, y, value);
		
	}
}
