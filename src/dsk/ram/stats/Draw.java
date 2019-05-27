package dsk.ram.stats;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Draw extends Application {

	// https://docs.oracle.com/javafx/2/api/javafx/application/Application.html
	// https://www.tutorialspoint.com/javafx/javafx_application.htm
	
	List<XYChart.Data<String, Number>> dataErrorsFound;
	List<XYChart.Data<String, Number>> dataErrorsNotFound;
	List<XYChart.Data<String, Number>> dataAlgorithmsSteps;
	
	BorderPane borderPane;
	ToolBar toolbar;
	
	CategoryAxis xAxisError;
	NumberAxis yAxisError;
	CategoryAxis xAxisAlgorithm;
	NumberAxis yAxisAlgorithm;
	
	StackedBarChart<String, Number> barChartErrors;
	StackedBarChart<String, Number> barChartAlgorithm;
	
	XYChart.Series<String, Number> seriesError1;
	XYChart.Series<String, Number> seriesError2;
	XYChart.Series<String, Number> seriesAlgorithm;
	
	String[] labelListErrors = { "AF", "CF", "DRF", "PSF", "SAF", "SOF", "TF" };
	String[] labelListAlgorithms = { "MATS", "MATS+", "MATS++", "MARCH X", "MARCH Y", "MARCH C-" };
	
	TableView table;

	public void init() throws Exception {
		borderPane = new BorderPane();
		toolbar = new ToolBar();
		
		xAxisError = new CategoryAxis();
		yAxisError = new NumberAxis();
		xAxisAlgorithm = new CategoryAxis();
		yAxisAlgorithm = new NumberAxis();
		
		barChartErrors = new StackedBarChart<String, Number>(xAxisError, yAxisError);
		barChartAlgorithm = new StackedBarChart<String, Number>(xAxisAlgorithm, yAxisAlgorithm);
		
		seriesError1 = new XYChart.Series<String, Number>();
		seriesError2 = new XYChart.Series<String, Number>();
		seriesAlgorithm = new XYChart.Series<String, Number>();
		
		xAxisError.setCategories(FXCollections.<String>observableArrayList(labelListErrors));
		xAxisAlgorithm.setCategories(FXCollections.<String>observableArrayList(labelListAlgorithms));
		
		xAxisError.setLabel("Typ b��du");
		xAxisAlgorithm.setLabel("Typ algorytmu");
		yAxisError.setLabel("Ilo�� wyst�pie�");
		yAxisAlgorithm.setLabel("Ilo�� wykonanych krok�w");
		
		yAxisError.setMinorTickVisible(false);
		yAxisAlgorithm.setMinorTickVisible(false);
		
		seriesError1.setName("Ilo�� wykrytych b��d�w");
		seriesError2.setName("B��dy niewykryte");
		seriesAlgorithm.setName("Ilo�� wykonanym krok�w");
		
		TableView table = new TableView();
	}

	@Override
	public void start(Stage stage) throws Exception {

		// Ustawienia pocz�tkowe wykresu
		// ********************************************************

		
		//Node fill = seriesError1.getNode().lookup(".chart-series-area-fill");
		//Color color = new Color(0,0,1,1.0);
	    //String colorString = "rgb(" + color.getRed() * 255 + "," + color.getGreen() * 255 + "," + color.getBlue() * 255 + ");";
	    //fill.setStyle("-fx-fill: rgba(" + Color.GREEN + ", 0.15);");

		// Przes�anie parametr�w do wykresu
		// ****************************************************
		Parameters params = getParameters();
		List<String> list = params.getRaw();
		setGraphData(list);
		
		// Ustawienia tabeli
		// ****************************************************
		setTable();
        
		// Ustawienia ko�cowe sceny
		// *****************************************************************
		
		Scene scene = new Scene(borderPane, 1400, 600);
		setLayouts();
		
		barChartErrors.setCategoryGap(50);
		barChartAlgorithm.setCategoryGap(50);
		barChartErrors.getData().addAll(seriesError1, seriesError2);
		barChartAlgorithm.getData().addAll(seriesAlgorithm);
		
		int maxError = 0;
		for(int i=0 ; i<7; i++) {
			if(maxError<Integer.parseInt(list.get(i))+Integer.parseInt(list.get(i+7))) {
				maxError = Integer.parseInt(list.get(i))+Integer.parseInt(list.get(i+7));
			}
		}
		
		int maxAlgorithmSteps = 0;
		for(int i=14 ; i<list.size(); i++) {
			if(maxAlgorithmSteps<Integer.parseInt(list.get(i))) {
				maxAlgorithmSteps = Integer.parseInt(list.get(i));
			}
		}
		
		yAxisAlgorithm.setAutoRanging(false);
		yAxisAlgorithm.setLowerBound(0);
		yAxisAlgorithm.setUpperBound(maxAlgorithmSteps+1);
		yAxisAlgorithm.setTickUnit(1);
		
		yAxisError.setAutoRanging(false);
		yAxisError.setLowerBound(0);
		yAxisError.setUpperBound(maxError+1);
		yAxisError.setTickUnit(1);
		
		stage.setTitle("Wyniki symulacji");
		stage.setScene(scene);
		stage.show();
	}

	public void stop() throws Exception {

	}
	
	public void setGraphData(List<String> list) {
		dataErrorsFound = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			dataErrorsFound.add(new XYChart.Data<String, Number>(labelListErrors[i], Integer.parseInt(list.get(i))));
			seriesError1.getData().add(dataErrorsFound.get(i));
		}

		dataErrorsNotFound = new ArrayList<>();
		for (int i = 7; i < 14; i++) {
			dataErrorsNotFound.add(new XYChart.Data<String, Number>(labelListErrors[i - 7], Integer.parseInt(list.get(i))));
			seriesError2.getData().add(dataErrorsNotFound.get(i - 7));
		}
		
		dataAlgorithmsSteps = new ArrayList<>();
		for (int i = 14; i < list.size(); i++) {
			dataAlgorithmsSteps.add(new XYChart.Data<String, Number>(labelListAlgorithms[i - 14], Integer.parseInt(list.get(i))));
			seriesAlgorithm.getData().add(dataAlgorithmsSteps.get(i - 14));
		}
	}
	
	public void setTable() {
		table = new TableView();
		TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn emailCol = new TableColumn("Email"); 
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
	}
	
	public void setLayouts() {
		borderPane.setTop(toolbar);
		borderPane.setRight(barChartErrors);
		borderPane.setCenter(barChartAlgorithm);
		borderPane.setLeft(table);
	}
}