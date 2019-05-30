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

		
		new Thread() {
            @Override
            public void run() {
            	Draw.launch(Draw.class);       
            }
        }.start();
	}
}
