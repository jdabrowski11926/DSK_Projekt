package dsk.ram.error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	@Override
	public String getName() {
		return "PSF";
	}
	
	private boolean isValidAdress(Address adress) {
		try {
			ram.getRam(adress.getX(), adress.getY());
			return true;
		}catch(IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	@Override
	public List<Address> getAffectedAdresses() {
		ArrayList<Address> result = new ArrayList<Address>();
		
		// Srodkowe
		List<Address> resultCanditates = new ArrayList<Address>( Arrays.asList( //Arrays.asList nie wspiera dodawania elementow
				new Address(x-1, y),
				new Address(x+1, y),
				new Address(x, y-1),
				new Address(x, y+1)
				
		));
		
		// Przekatne
		if(psfType == PsfType.PSF_9) {
			resultCanditates.add(new Address(x-1, y+1));
			resultCanditates.add(new Address(x-1, y-1));
			resultCanditates.add(new Address(x+1, y-1));
			resultCanditates.add(new Address(x+1, y+1));
		}
		
		// Sprawdzenie czy adresy nie wykraczaja poza rozmiar ramu
		for(Address candidate : resultCanditates) {
			if(isValidAdress(candidate)) {
				result.add(candidate);
			}
		}
		
		return result;
	}

}
