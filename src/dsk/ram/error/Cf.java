package dsk.ram.error;

import java.util.Arrays;
import java.util.List;

import dsk.ram.Ram;

public class Cf extends RamError{
	public enum CfType{
		CF_IN, CF_ID_0, CF_ID_1, CF_D
	}
	
	private CfType cfType;
	private int pairX;
	private int pairY;
	
	public Cf(Ram ram, int x, int y, int pairX, int pairY, CfType cfType) {
		super(ram, x, y);
		this.pairX = pairX;
		this.pairY = pairY;
		this.cfType = cfType;
	}

	@Override
	public boolean getRam(int x, int y) {
		if(cfType == CfType.CF_D) {
			ram.setRam(pairX, pairY, !ram.getRam(pairX, pairY));
		}
		return ram.getRam(x, y);
	}

	@Override
	public void setRam(int x, int y, boolean value) {
		ram.setRam(x, y, value);
		switch (cfType) {
		case CF_IN:
			ram.setRam(pairX, pairY, !ram.getRam(pairX, pairY));
			break;
		case CF_ID_0:
			ram.setRam(pairX, pairY, false);
			break;
		case CF_ID_1:
			ram.setRam(pairX, pairY, true);
			break;
		case CF_D:
			ram.setRam(pairX, pairY, !ram.getRam(pairX, pairY));
			break;
		}
		
	}

	@Override
	public String getName() {
		switch (cfType) {
		case CF_IN:
			return "CFin";
		case CF_ID_0:
			return "CFid 0";
		case CF_ID_1:
			return "CFid 1";
		case CF_D:
			return "CFd";
		}
		return "CF";
	}
	
	@Override
	public List<Address> getAffectedAdresses() {
		return Arrays.asList(new Address(pairX, pairY));
	}



}
