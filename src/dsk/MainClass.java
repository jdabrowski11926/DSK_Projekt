package dsk;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dsk.ram.Ram;
import dsk.ram.error.Af;
import dsk.ram.error.Cf;
import dsk.ram.error.Drf;
import dsk.ram.error.Psf;
import dsk.ram.error.RamError;
import dsk.ram.error.Saf;
import dsk.ram.error.Sof;
import dsk.ram.error.Tf;
import dsk.ram.stats.Draw;
import dsk.ram.test_algorithm.MarchCMinus;
import dsk.ram.test_algorithm.MarchX;
import dsk.ram.test_algorithm.MarchY;
import dsk.ram.test_algorithm.Mats;
import dsk.ram.test_algorithm.MatsPlus;
import dsk.ram.test_algorithm.MatsPlusPlus;
import dsk.ram.test_algorithm.RamTestAlgorithm;
import dsk.ram.test_algorithm.TestLog;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class MainClass {
	
	public static List<TestLog> runTestsForEachError(Ram ram, List<RamError> errors, List<RamTestAlgorithm> algorithms) {
		List<TestLog> testLogs = new ArrayList<TestLog>();
		ram.clearErrors();
		for(RamError error : errors) {
			ram.addError(error);
			for(RamTestAlgorithm algorithm : algorithms) {
				TestLog tl = new TestLog(algorithm, error);
				tl.addErrorLogs(algorithm.test(ram));
				
				testLogs.add(tl);
			}
			ram.clearErrors();
		}
		return testLogs;
	}
	
	public static void main(String[] args) {		

		Ram ram = new Ram(100);
		
		
		List<TestLog> testLogs = runTestsForEachError(
				ram,
				Arrays.asList(
						new Af(ram, 50, 5),
						new Cf(ram, 50, 5, 40, 5, Cf.CfType.CF_D),
						new Cf(ram, 50, 5, 40, 5, Cf.CfType.CF_ID_0),
						new Cf(ram, 50, 5, 40, 5, Cf.CfType.CF_ID_1),
						new Cf(ram, 50, 5, 40, 5, Cf.CfType.CF_IN),
						new Drf(ram, 50, 5, 10, 20),
						new Psf(ram, 50, 5, Psf.PsfType.PSF_4),
						new Psf(ram, 50, 5, Psf.PsfType.PSF_9),
						new Saf(ram, 50, 5),
						new Sof(ram, 50, 5),
						new Tf(ram, 50, 5, Tf.Direction.ONE_TO_ZERO),
						new Tf(ram, 50, 5, Tf.Direction.ZERO_TO_ONE)
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
		
		for(TestLog tl : testLogs) {
			System.out.println(tl.summary());
		}
				
		
		
		ram.addError(new Af(ram,5,5));
		
		Mats testAlgorithm = new Mats();
		testAlgorithm.test(ram);
		
		ram.randomRam();
		ram.showRam();
		
		Draw draw = new Draw();
		new Thread() {
            @Override
            public void run() {
            	String[] a = {"12", "32", "1"};
                draw.launch(Draw.class, a);
                
            }
        }.start();
        
        //draw.printSomething();
		
		
		
		
	}
}
