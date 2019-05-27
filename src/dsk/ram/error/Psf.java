package dsk.ram.error;

import dsk.ram.Ram;

public class Psf extends RamError{
	public enum PsfType{
		PSF_9, PSF_4
	}
	
	private PsfType psfType;

	public Psf(Ram ram, int x, int y, PsfType psfType) {
		super(ram, x, y);
		
		this.psfType = psfType;
	}

	@Override
	public boolean getRam(int x, int y) {
		return ram.getRam(x, y);
	}

	@Override
	public void setRam(int x, int y, boolean value) {
		// Glowna komorka
		ram.setRam(x, y, value);
		
		// Srodkowe
		trySetRam(x-1, y, value);
		trySetRam(x+1, y, value);		
		trySetRam(x, y-1, value);
		trySetRam(x, y+1, value);
		
		// Przekatne
		if(psfType == PsfType.PSF_9) {
			trySetRam(x-1, y+1, value);
			trySetRam(x-1, y-1, value);
			trySetRam(x+1, y-1, value);
			trySetRam(x+1, y+1, value);
		}

	}

	private boolean trySetRam(int x, int y, boolean value) {
		try {
			ram.setRam(x, y, value);
			return true;
		}catch(IndexOutOfBoundsException e) {
			return false;
		}
	}

}
