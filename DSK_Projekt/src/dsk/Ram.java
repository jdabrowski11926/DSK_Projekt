package dsk;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

import dsk.ram_error.ErrorTemplate;

public class Ram {
	
	private boolean[][] memory;
	private ArrayList<ErrorTemplate> errors = new ArrayList<ErrorTemplate>();

	
	Ram(int sizeX, int sizeY){
		memory = new boolean[sizeX][sizeY];
	}
	
	public void randomRam() {
		Random generator = new Random();
		for(int i=0; i<memory.length ; i++) {
			for(int j=0; j<memory.length ; j++) {
				if (generator.nextDouble()<0.5) {
					memory[i][j] = true;
				}
				else memory[i][j] = true;
			}
			
		}
	}
	
	public void showRam() {
		for(int i=0; i<memory.length ; i++) {
			for(int j=0; j<memory.length ; j++) {
				System.out.print(memory[i][j]);
			}
		}
	}
	
	public void damageRam(int SAF, int SOF, int DRF, int TF, int CF, int PSF) {
		System.out.println("RAM ZMIA¯D¯ONY");
		for(int i=0;i<SAF;i++) {
			
		}
		for(int i=0;i<SOF;i++) {
			
		}
		for(int i=0;i<DRF;i++) {
			
		}
		for(int i=0;i<TF;i++) {
			
		}
		for(int i=0;i<CF;i++) {
			
		}
		for(int i=0;i<PSF;i++) {
			
		}
	}
	
	// Interfejs porgramistyczny do obs³ugi klasy
	public ErrorTemplate getError(int x, int y) {
		for(ErrorTemplate error: errors) {
			if(error.affectsXY(x, y)) return error;
		}
		return null;
	}
	
	public void setRam(int x, int y, boolean value) {
		memory[x][y] = value;
	}
	
	public boolean getRam(int x, int y) {
		return memory[x][y];
	}
	
	// Operacje mogace nie zadzialac (wykorzystane w symulacji)
	public void write(int x, int y, boolean value) {
		ErrorTemplate error = getError(x, y);
		if(error != null) {
			error.setRam(x, y, value);
		}else {
			setRam(x, y, value);
		}
	}
	
	public boolean read(int x, int y) {
		ErrorTemplate error = getError(x, y);
		if(error != null) {
			return error.getRam(x, y);
		}else {
			return getRam(x, y);
		}
	}
	
	public void addError(ErrorTemplate et) {
		this.errors.add(et);
	}
}
