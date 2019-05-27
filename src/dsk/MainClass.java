package dsk;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import dsk.ram.Ram;
import dsk.ram.error.Af;
import dsk.ram.error.Cf;
import dsk.ram.error.Drf;
import dsk.ram.error.Psf;
import dsk.ram.error.RamError;
import dsk.ram.error.Saf;
import dsk.ram.error.Sof;
import dsk.ram.error.Tf;
import dsk.ram.stats.AlgorithmTester;
import dsk.ram.stats.Draw;
import dsk.ram.stats.TestLog;
import dsk.ram.test_algorithm.MarchCMinus;
import dsk.ram.test_algorithm.MarchX;
import dsk.ram.test_algorithm.MarchY;
import dsk.ram.test_algorithm.Mats;
import dsk.ram.test_algorithm.MatsPlus;
import dsk.ram.test_algorithm.MatsPlusPlus;
import dsk.ram.test_algorithm.RamTestAlgorithm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class MainClass {
	public static void main(String[] args) {		

	int RAM_LENGTH = 100;
	int ITERATIONS = 100;
	int RAM_WIDTH = 8;
	
	Ram ram = new Ram(RAM_LENGTH);
	ram.randomRam();
		
	Random randomizer = new Random();
	
	
	String[] rows = null;
	String[] cols = null;
	int[] affectedCellsCount = null;
	int[][] detectedCellsCount = null;
	
	for(int i=0; i<ITERATIONS; i++) {
		int x = randomizer.nextInt(RAM_LENGTH);
		int y = randomizer.nextInt(RAM_WIDTH);
		
		int x1 = randomizer.nextInt(RAM_LENGTH);
		int y1 = randomizer.nextInt(RAM_WIDTH) ;
		while(x == x1 && y == y1) {
			x1 = randomizer.nextInt(RAM_LENGTH);
			y1 = randomizer.nextInt(RAM_WIDTH);
		}
		
		AlgorithmTester at = new AlgorithmTester(
				ram,
				Arrays.asList(
						new Af(ram, x, y),
						new Cf(ram, x, y, x1, y1, Cf.CfType.CF_D),
						new Cf(ram, x, y, x1, y1, Cf.CfType.CF_ID_0),
						new Cf(ram, x, y, x1, y1, Cf.CfType.CF_ID_1),
						new Cf(ram, x, y, x1, y1, Cf.CfType.CF_IN),
						new Drf(ram, x, y, 10, 20),
						new Psf(ram, x, y, Psf.PsfType.PSF_4),
						new Psf(ram, x, y, Psf.PsfType.PSF_9),
						new Saf(ram, x, y),
						new Sof(ram, x, y),
						new Tf(ram, x, y, Tf.Direction.ONE_TO_ZERO),
						new Tf(ram, x, y, Tf.Direction.ZERO_TO_ONE)
				),
				Arrays.asList(
						new MarchCMinus(),
						new MarchX(),
						new MarchY(),
						new Mats(),
						new MatsPlus(),
						new MatsPlusPlus()
				)
		);
		
		rows = at.getRowLabels();
		cols = at.getColumnLabels();
		if(affectedCellsCount != null) {
			for(int xx=0; xx<affectedCellsCount.length; xx++) {
				affectedCellsCount[xx] += at.getAffectedCellCount()[xx];
			}
		}else {
			affectedCellsCount = at.getAffectedCellCount();
		}
		
		if(detectedCellsCount != null) {
			for(int xx=0; xx<detectedCellsCount.length; xx++) {
				for(int yy=0; yy<detectedCellsCount[xx].length; yy++) {
					detectedCellsCount[xx][yy] += at.getDetectedCellCount()[xx][yy];
				}
			}
		}else {
			detectedCellsCount = at.getDetectedCellCount();
		}
	}

//	String[] rows;
//	String[] cols;
//	int[] affectedCellsCount;
//	int[][] detectedCellsCount;
//	
	for(int i=0;i<cols.length; i++) {
		System.out.print(cols[i]+"\t");
	}
	System.out.println();
	for(int i=0;i<rows.length; i++) {
		for(int j=0;j < cols.length; j++) {
			System.out.print(detectedCellsCount[i][j]+"\t");
		}
		System.out.println(rows[i]);
	}
	for(int i=0;i<cols.length; i++) {
		System.out.print(affectedCellsCount[i]+"\t");
	}
	System.out.println();
		
		Draw draw = new Draw();
		new Thread() {
            @Override
            public void run() {
            	String[] a = {"1", "2", "20", "4", "5", "6", "7", 
            			"1", "2", "3", "4", "5", "6", "7",
            			"321", "332", "543", "111", "666", "765"};
                draw.launch(Draw.class, a);       
            }
        }.start();
        
        //draw.printSomething();
		
		
		
		
	}
}
