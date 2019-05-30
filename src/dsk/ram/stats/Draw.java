package dsk.ram.stats;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import dsk.ram.Ram;
import dsk.ram.error.Af;
import dsk.ram.error.Cf;
import dsk.ram.error.Drf;
import dsk.ram.error.Psf;
import dsk.ram.error.Saf;
import dsk.ram.error.Sof;
import dsk.ram.error.Tf;
import dsk.ram.test_algorithm.MarchCMinus;
import dsk.ram.test_algorithm.MarchX;
import dsk.ram.test_algorithm.MarchY;
import dsk.ram.test_algorithm.Mats;
import dsk.ram.test_algorithm.MatsPlus;
import dsk.ram.test_algorithm.MatsPlusPlus;

public class Draw extends Application {	
	final BorderPane borderPaneSummary = new BorderPane(); 
	final BorderPane borderPaneSingleAlgorithm = new BorderPane(); 
	final GridPane buttonPane = new GridPane();
	final ToolBar toolbar = new ToolBar();
	
	//Wykres typy bledow dla jednego algorytmu
	final CategoryAxis xAxisError = new CategoryAxis();
	final NumberAxis yAxisError = new NumberAxis();
	final StackedBarChart<String, Number> barChartErrors = new StackedBarChart<String, Number>(xAxisError, yAxisError);
	
	final XYChart.Series<String, Number> seriesError1 = new XYChart.Series<String, Number>();
	final XYChart.Series<String, Number> seriesError2 = new XYChart.Series<String, Number>();
	
	//Porownania algorytmow pod wzgledem ilosci krokow
	final CategoryAxis xAxisAlgorithm = new CategoryAxis();
	final NumberAxis yAxisAlgorithm = new NumberAxis();
	final StackedBarChart<String, Number> barChartAlgorithm = new StackedBarChart<String, Number>(xAxisAlgorithm, yAxisAlgorithm);
	
	final TabPane tabPane = new TabPane();
	
	final Tab tabSingleAlgorighm = new Tab("Algorytmy oddzielnie");
	final Tab tabSummary = new Tab("Podsumowanie");
	
	
	final XYChart.Series<String, Number> seriesAlgorithm = new XYChart.Series<String, Number>();

	
	
	//Widok tabeli z podsumowniem danych
	TableView table;
	
	SimulationSummary simSummary;

	
	
	public void init() throws Exception {

		
	}
	
	public SimulationSummary runSimulation(int ramLength, int ramWidth, int iterations) {
		
		Ram ram = new Ram(ramLength, ramWidth);
		ram.randomRam();
			
		Random randomizer = new Random();
		
		SimulationSummary simSummary = new SimulationSummary();
		
		for(int i=0; i<iterations; i++) {
			int x = randomizer.nextInt(ramLength);
			int y = randomizer.nextInt(ramWidth);
			
			int x1 = randomizer.nextInt(ramLength);
			int y1 = randomizer.nextInt(ramWidth) ;
			while(x == x1 && y == y1) {
				x1 = randomizer.nextInt(ramLength);
				y1 = randomizer.nextInt(ramWidth);
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
							new Mats(),
							new MatsPlus(),
							new MatsPlusPlus(),	
							new MarchX(),
							new MarchY(),
							new MarchCMinus()
					)
			);
			
			
			
			
			simSummary.algorithmNames = at.getRowLabels();
			simSummary.errorNames = at.getColumnLabels();
			
			if(simSummary.algorithmStepsCount != null) {
				for(int yy=0; yy<simSummary.algorithmNames.length; yy++) {
					simSummary.algorithmStepsCount[yy] += at.getAlgorithmStepsCount()[yy];
				}
			}else{
				simSummary.algorithmStepsCount = at.getAlgorithmStepsCount();
			}
			
			if(simSummary.affectedCellsCount != null) {
				for(int xx=0; xx<simSummary.affectedCellsCount.length; xx++) {
					simSummary.affectedCellsCount[xx] += at.getAffectedCellCount()[xx];
				}
			}else {
				simSummary.affectedCellsCount = at.getAffectedCellCount();
			}
			
			if(simSummary.detectedCellsCount != null) {
				for(int xx=0; xx<simSummary.detectedCellsCount.length; xx++) {
					for(int yy=0; yy<simSummary.detectedCellsCount[xx].length; yy++) {
						simSummary.detectedCellsCount[xx][yy] += at.getDetectedCellCount()[xx][yy];
					}
				}
			}else {
				simSummary.detectedCellsCount = at.getDetectedCellCount();
			}
			
			

		}
		
		//ASCII tabelka
		for(int i=0;i<simSummary.errorNames.length; i++) {
			System.out.print(simSummary.errorNames[i]+"\t");
		}
		System.out.println();
		for(int i=0;i<simSummary.algorithmNames.length; i++) {
			for(int j=0;j < simSummary.errorNames.length; j++) {
				System.out.print(simSummary.detectedCellsCount[i][j]+"\t");
			}
			System.out.println(simSummary.algorithmNames[i]);
		}
		for(int i=0;i<simSummary.errorNames.length; i++) {
			System.out.print(simSummary.affectedCellsCount[i]+"\t");
		}
		System.out.println();
		
		return simSummary;
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Wyniki symulacji");
		

		//Licze symulacje
		simSummary = runSimulation(16, 16, 1000);
		

		xAxisError.setLabel("Typ b³êdu");
		yAxisError.setLabel("Iloœæ wyst¹pieñ");
		xAxisError.setCategories(FXCollections.<String>observableArrayList(simSummary.errorNames));
		seriesError1.setName("Iloœæ wykrytych b³êdów");
		seriesError2.setName("B³êdy niewykryte");

		xAxisAlgorithm.setLabel("Typ algorytmu");
		yAxisAlgorithm.setLabel("Iloœæ wykonanych kroków");
		xAxisAlgorithm.setCategories(FXCollections.<String>observableArrayList(simSummary.algorithmNames));
		seriesAlgorithm.setName("Iloœæ wykonanym kroków");
		
		
		yAxisError.setMinorTickVisible(false);
		yAxisAlgorithm.setMinorTickVisible(false);
		
		displayAlgorithmData(5);
		displayAlgorithmSteps();

		setTable();
		barChartErrors.setCategoryGap(15);
		barChartAlgorithm.setCategoryGap(50);
		barChartErrors.getData().addAll(seriesError1, seriesError2);
		barChartAlgorithm.getData().addAll(seriesAlgorithm);
		
		Scene scene = new Scene(tabPane, 1000, 600);
		
		tabPane.getTabs().add(tabSingleAlgorighm);
		tabPane.getTabs().add(tabSummary);
		tabSingleAlgorighm.setClosable(false);
		tabSummary.setClosable(false);
		
		tabSummary.setContent(borderPaneSummary);
		
		
		for(int i=0 ; i<simSummary.algorithmNames.length; i++) {
			Button button = new Button(simSummary.algorithmNames[i]);
			button.setPrefWidth(100);
			button.setOnAction((e)->{
				if(e.getSource() instanceof Button) {
					Button b = (Button)e.getSource();
					for(int j = 0;j<simSummary.algorithmNames.length;j++) {
						if(simSummary.algorithmNames[j] == b.getText()) {
							displayAlgorithmData(j);
							break;
						}
					}
					e.consume();
				}
			});
			buttonPane.add(button, 0, i);
		}
		buttonPane.setPadding(new Insets(10, 10, 10, 10));
		buttonPane.setHgap(2);
		buttonPane.setVgap(2);

		tabSingleAlgorighm.setContent(borderPaneSingleAlgorithm);
		
		//borderPane.setTop(toolbar);
		//borderPane.setRight(barChartErrors);
		borderPaneSummary.setCenter(barChartAlgorithm);
		borderPaneSummary.setLeft(table);
		
		borderPaneSingleAlgorithm.setLeft(buttonPane);
		borderPaneSingleAlgorithm.setCenter(barChartErrors);
		
		stage.setScene(scene);
		stage.show();
	}

	public void stop() throws Exception {

	}
	
	public void setGraphData(String algorithmName, ArrayList<String> errorNames, ArrayList<Integer> errorsFound, ArrayList<Integer> errorsSkipped) {
		//dataErrorsFound = new ArrayList<>();
		
		

		/*
		dataAlgorithmsSteps = new ArrayList<>();
		for (int i = 14; i < list.size(); i++) {
			dataAlgorithmsSteps.add(new XYChart.Data<String, Number>(labelListAlgorithms[i - 14], Integer.parseInt(list.get(i))));
			seriesAlgorithm.getData().add(dataAlgorithmsSteps.get(i - 14));
		}*/
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setTable() {
		table = new TableView();
		table.getColumns().clear();
		
		String[][] errorCountArray = new String[simSummary.errorNames.length][simSummary.algorithmNames.length+1];
		for(int i=0 ; i<simSummary.algorithmNames.length+1; i++) {
			for(int j=0; j<simSummary.errorNames.length; j++) {
				if(i == 0) {
					errorCountArray[j][i] = simSummary.errorNames[j];
				}else {
					errorCountArray[j][i] = simSummary.detectedCellsCount[i-1][j] + "/" + simSummary.affectedCellsCount[j];
				}
			}
		}
		ObservableList<String[]> data = FXCollections.observableArrayList();
		data.addAll(Arrays.asList(errorCountArray));
		//data.remove(0);//remove titles from data
		table = new TableView();
		for (int i = 0; i < errorCountArray[0].length; i++) {
			TableColumn tc = null;
			if(i == 0) {
				tc = new TableColumn("");
			}else {
				tc = new TableColumn(simSummary.algorithmNames[i-1]);
			}
			final int colNo = i;
            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            table.setItems(data);
            tc.setPrefWidth(70);
            table.getColumns().add(tc);
		}
	}
	
	public void displayAlgorithmData(int algId) {
		//seriesError1.getData().clear();
		//seriesError2.getData().clear();
		barChartErrors.setTitle(simSummary.algorithmNames[algId]);
		
		ArrayList<String> errorNames = new ArrayList<String>();
		ArrayList<Integer> errorsFound = new ArrayList<Integer>();
		ArrayList<Integer> errorsSkipped = new ArrayList<Integer>();
		
		
		for(int errId=0;errId<simSummary.errorNames.length; errId++) {
			errorNames.add(simSummary.errorNames[errId]);
			errorsFound.add(simSummary.detectedCellsCount[algId][errId]);
			errorsSkipped.add(simSummary.affectedCellsCount[errId]-simSummary.detectedCellsCount[algId][errId]);
		}
		
		
		if(seriesError1.getData().size() == 0 && seriesError2.getData().size() == 0) {
			for (int j = 0; j < errorNames.size(); j++) {
				//System.out.println(errorNames.get(j) + " " + errorsFound.get(j) + " " + errorsSkipped.get(j));
				seriesError1.getData().add(
						new XYChart.Data<String, Number>(errorNames.get(j), errorsFound.get(j))
				);
				seriesError2.getData().add(
						new XYChart.Data<String, Number>(errorNames.get(j), errorsSkipped.get(j))
				);
			}
		}else {
			for (int j = 0; j < errorNames.size(); j++) {
				//System.out.println(errorNames.get(j) + " " + errorsFound.get(j) + " " + errorsSkipped.get(j));
				seriesError1.getData().get(j).setXValue(errorNames.get(j));
				seriesError1.getData().get(j).setYValue(errorsFound.get(j));
				
				
				seriesError2.getData().get(j).setXValue(errorNames.get(j));
				seriesError2.getData().get(j).setYValue( errorsSkipped.get(j));
				
			}
		}
			
	}
	
	public void displayAlgorithmSteps() {
		for (int i = 0; i < simSummary.algorithmNames.length; i++) {
			//dataAlgorithmsSteps.add(new XYChart.Data<String, Number>(labelListAlgorithms[i - 14], Integer.parseInt(list.get(i))));
			seriesAlgorithm.getData().add(new XYChart.Data<String, Number>(simSummary.algorithmNames[i], simSummary.algorithmStepsCount[i]));
		}
	}
	
}