


import java.util.Collection;
import java.util.Map;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

import Algorithm.ShortestPath;
import data_structure.LinkedList;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Visual extends StackPane {

	private Button btnCrime;
	private Button btnLoc;
	private Button btnShow, btnClose , btnremove, btnde, btndelete;
	private Button btnAddEdges, btnPath;
	private Button btnadd , btnRemoveE, btnUpdate;
	private Button btnRemoveV, btnCancel;

	private Text txtValue;
	private TextArea area;

	private Stage popup ,stage;
	
	private Line line;
	private Group group;
	private Circle circle;
	private GridPane pane;
	private BorderPane borderPane;
    private GridPane pane2;
    private ComboBox<String> comboBox, comboBox2, comboBox3, comboBox4,comboBox5;
   

    private Text txtLabel;
	private Text txtTitle, txtvb1, txtvb2;
	private Text txtcrime;
	private Text txtloc;
	private Text txtV1;
	private Text txtV2, txtCost;
	

	TextField loc,wieght, t ;
	TextField pop ;
	TextField rate;
	TextField v;
	TextField crimeField, cost, v2;
	VBox V1, V2, v1, vbox1, vbox2 , vbox3 ;
	
	//Nodes
	private CrimeType<Integer> type;
	private Demographic<Integer> demo;
	private Edge<Integer> edge;
	private Graph<Integer> graph;
	private ShortestPath<Integer> path;
	private Map<Vertex<Integer>, Integer> dis;
	
	String name;
	String crimetype;
	int Value ;
	double locationPopulation;
	double locationRate;
	private Collection<Vertex<Integer>> vertexs, demos, types;
	private Collection<Edge<Integer>> edgess;
	int costed, w ,ts;
	private Stage popup1;
	private ComboBox<String> comboBox6;
	private ComboBox<String> comboBox7;
	private Stage popup2;
	private Text txtvb3;

	

	public Visual() {
		
		vertexs  = new LinkedList<>();
		demos =  new LinkedList<>();
		types =  new LinkedList<>();
		edgess =  new LinkedList<>();


		
		SetUp();
		//setGroup();
		

		btnLoc.setOnAction(e -> {
		
	
			loc.setMinWidth(100);
			pop.setMinWidth(100);
			rate.setMinWidth(100);
			v.setMinWidth(100);
			wieght.setMinWidth(100);
			
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeight(500);
			alert.setWidth(250);		
			alert.setHeaderText("Enter Crime Location:");
			alert.setTitle("Location");			
			alert.setGraphic(loc);	
			alert.showAndWait();
			
			
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setHeight(500);
			alert2.setWidth(250);		
			alert2.setHeaderText("Enter Area Population:");
			alert2.setTitle("Population");			
			alert2.setGraphic(pop);	
			alert2.showAndWait();
			
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeight(500);
			alert1.setWidth(250);		
			alert1.setHeaderText("Enter Poverty Rate:");
			alert1.setTitle("Poverty");			
			alert1.setGraphic(rate);	
			alert1.showAndWait();
			
			Alert alert3 = new Alert(AlertType.INFORMATION);
			alert3.setHeight(500);
			alert3.setWidth(250);		
			alert3.setHeaderText("Enter The Weight:");
			alert3.setTitle("Wieght");			
			alert3.setGraphic(wieght);	
			alert3.showAndWait();
			
			Alert alert4 = new Alert(AlertType.INFORMATION);
			alert4.setHeight(500);
			alert4.setWidth(250);		
			alert4.setHeaderText("Enter The Cost:");
			alert4.setTitle("Cost");			
			alert4.setGraphic(v);	
			alert4.showAndWait();
			
			
			
			w =Integer.parseInt(wieght.getText());
			
			ts = Integer.parseInt(v.getText());
			
			name =loc.getText();
			locationPopulation =Double.parseDouble(pop.getText()) ;
			locationRate  =Double.parseDouble(pop.getText());
			
			demo = new Demographic<Integer>(ts, name, locationPopulation, locationRate,w);
			vertexs.add(demo);
			
			

		});
		
		btnCrime.setOnAction(e->{
			v.setMinWidth(100);
			crimeField.setMinWidth(100);

			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeight(500);
			alert1.setWidth(250);		
			alert1.setHeaderText("Enter Crime Type:");
			alert1.setTitle("Crime Type");			
			alert1.setGraphic(crimeField);	
			alert1.showAndWait();
			
			crimetype = crimeField.getText();
			
			type = new CrimeType<>(2, crimetype);
			
			vertexs.add(type);
		
			
			
		});
		btnShow.setOnAction(e->{
			graph = new Graph<Integer>(vertexs,edgess);
			
			view();
		//	setGroup();
		});

		btnAddEdges.setOnAction(e-> AddEdges());
		btnadd.setOnAction(e->{
			
			Vertex<Integer> vertex1 = null;
			Vertex<Integer> vertex2 = null;
			
			for (Vertex<Integer> de : demos) {	
					if(comboBox.getValue().equals(((Demographic<Integer>) de).getArea()) ){
						vertex1 = de;
										
					}			
			}
	
			for (Vertex<Integer> t : types) {	
				if(comboBox2.getValue().equals(((CrimeType<Integer>) t).getCrimeType()) ){
					vertex2 = t;
				
				}			
		}
			
			edge = new Edge<>(Integer.parseInt(comboBox3.getValue()),vertex1,vertex2);
			edgess.add(edge);
			

			for (Vertex<Integer> de : demos) {	
					if(comboBox.getValue().equals(((Demographic<Integer>) de).getArea()) ){
						de.addEdge(edge);
										
					}			
			}
	
			for (Vertex<Integer> t : types) {	
				if(comboBox2.getValue().equals(((CrimeType<Integer>) t).getCrimeType()) ){
					t.addEdge(edge);
				
				}			
		}
			
		
			btnShow.setDisable(false);
			btnRemoveE.setDisable(false);
			btnRemoveV.setDisable(false);
			btnUpdate.setDisable(false);
		});
		
		
		btnRemoveV.setOnAction(e->{
			group.getChildren().clear();
			RemoveVertex();
			
			
			
			
			
	
	for (Vertex<Integer> vertex : vertexs) {
		
		if(comboBox6.getValue().equals(((Demographic<Integer>) vertex).getArea())) {
			vertexs.remove(vertex);
		}else {
			vertexs.remove(vertex);
		}
		
	}
	graph= new Graph<>(vertexs,edgess);
	




	
			
		});
		
	btndelete.setOnAction(e->{
		comboBox4.getItems().clear();
		comboBox5.getItems().clear();
		vbox2.getChildren().clear();
		vbox1.getChildren().clear();
		
		popup1.close();
	});
		
		btnde.setOnAction(e-> {
			
			comboBox6.getItems().clear();
			vbox3.getChildren().clear();
			
			popup2.close();
			
		});
		
		
		
		btnCancel.setOnAction(e ->{
			comboBox.getItems().clear();
			comboBox2.getItems().clear();
			comboBox3.getItems().clear();
			v1.getChildren().clear();
			V1.getChildren().clear();
			V2.getChildren().clear();
			
			popup.close();
		});
		
		btnRemoveE.setOnAction(e->{
			
			group.getChildren().clear();
		
			RemoveEdge();
			
			Vertex<Integer> vertex1 = null;
			Vertex<Integer> vertex2 = null;
			
			for (Vertex<Integer> de : demos) {	
					if(comboBox4.getValue().equals(((Demographic<Integer>) de).getArea()) ){
						vertex1 = de;
										
					}			
			}
	
			for (Vertex<Integer> t : types) {	
				if(comboBox5.getValue().equals(((CrimeType<Integer>) t).getCrimeType()) ){
					vertex2 = t;
				
				}			
		}
			
			edge = new Edge<>(Integer.parseInt(comboBox3.getValue()),vertex1,vertex2);
			edgess.remove(edge);
		
			graph = new Graph<>(vertexs, edgess);
	
			
		});
		
		btnClose.setOnAction(e->{
			stage = (Stage)this.getScene().getWindow();
			
			stage.close();
			
		});
		
		btnUpdate.setOnAction(e->{
		
			graph = new Graph<>(vertexs,edgess);
			view();
			
		});
		
		btnPath.setOnAction(e->{
			path = new ShortestPath<>(graph);

			Map<Vertex<Integer>, Integer> distances = path.getShortestPath(demo);
			area.setText(distances.toString());
			
		});
		
		
	}

	private void SetUp() {
		
		graph = new Graph<Integer>(vertexs,edgess);

		txtTitle = new Text("Crime Evalution");
		txtloc = new Text("Add Crime Location");
		txtcrime = new Text("Input Type Of Crime");
		txtV1 = new Text("Crime Location");
		txtV2 = new Text("Type Of Crime");
		txtCost = new Text("Enter The Cost Between The Two Vertex");
		txtvb1 = new Text("Vertex From");
		txtvb2 = new Text("Vertex To");
		txtvb3 = new Text("Vertex To Remove");
		
		
	    loc = new TextField();
		pop = new TextField();
		rate = new TextField();
		cost = new TextField();
		v = new TextField();
		wieght = new TextField();
		crimeField = new TextField();
		V1 = new VBox();
		V2 = new VBox();
		v1 = new VBox();
		vbox1 = new VBox();
		vbox2 = new VBox();
		vbox3 = new VBox();
	
		v2 = new TextField();

		Font font = Font.font("Cursive", FontPosture.ITALIC, 30);
		txtTitle.setFont(font);
		Font font2 = Font.font("Cursive", FontPosture.ITALIC, 15);
		txtloc.setFont(font2);
		txtcrime.setFont(font2);
		txtV1.setFont(font2);
		txtV2.setFont(font2);
		txtCost.setFont(font2);
		txtvb1.setFont(font2);
		txtvb2.setFont(font2);

		area = new TextArea();

		btnCrime = new Button("Add Type Of Crime");
		btnCrime.setMaxWidth(150);
		btnCancel = new Button("Cancel");
		btnCancel.setMaxWidth(150);
		btnLoc = new Button("Add Crime Location");
		btnLoc.setMaxWidth(150);
		btnShow = new Button("Display Graph");
		btnShow.setMaxWidth(150);
		btnAddEdges = new Button("Add Edges");
		btnAddEdges.setMaxWidth(150);
		btnadd = new Button("Add Egde");
		btnadd.setMaxWidth(150);
		btnRemoveV = new Button("Remove Vertex");
		btnRemoveV.setMaxWidth(150);
		btnRemoveE = new Button("Remove Edge");
		btnRemoveE.setMaxWidth(150);
		btnUpdate = new Button("Display Updated Graph");
		btnUpdate.setMaxWidth(150);
		btnPath = new Button("Shortest Path");
		btnPath.setMaxWidth(150);
		btnClose = new Button("Close");
		btnClose.setMaxWidth(150);
		btnremove = new Button("Delete");
		btnde = new Button("Cancel");
		btndelete = new Button("Cancel");

		borderPane = new BorderPane();
	
		line = new Line();
		group = new Group();
		pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);

		pane2 = new GridPane();
		pane2.setHgap(10);
		pane2.setVgap(10);
		
		area.setMaxWidth(250);

		Color color = Color.WHITE;
		BackgroundFill backgroundFill = new BackgroundFill(color, null, null);
		Background background = new Background(backgroundFill);


	
		pane.setBackground(background);

		pane.add(txtTitle, 1, 0);
		pane.add(btnLoc, 1, 4);

		pane.add(btnCrime, 1, 5);
		pane.add(btnShow, 1, 7);
		pane.add(btnAddEdges, 1, 6);
		pane.add(btnRemoveE, 1, 8);
		pane.add(btnRemoveV, 1, 9);
		pane.add(btnPath, 1, 11);
		pane.add(btnUpdate, 1, 10);
		pane.add(area, 1, 12);
		pane.add(btnClose, 1, 20);

		

		btnShow.setDisable(true);
		btnRemoveE.setDisable(true);
		btnRemoveV.setDisable(true);
		btnUpdate.setDisable(true);

		borderPane.setLeft(pane);

		getChildren().addAll(borderPane,group);

	}
	
	public void view() {
	
		
		for(Vertex<Integer> vertex : graph.getVertices()) {
			
			 double x = (double) (Math.random() * 500) + 20; 
			 double y = (double)(Math.random() * 500) + 20;
			
			if(vertex instanceof Demographic) {
				
				((Demographic<Integer>) vertex).setX(x);
				((Demographic<Integer>) vertex).setY(y);
				
				circle = new Circle(x,y,20,Color.LIGHTPINK);
				
				 txtLabel = new Text(((Demographic<Integer>) vertex).getArea());
				 txtLabel.setFont(Font.font("Verdana",15)); 
				 txtLabel.setFill(Color.BLACK);
				 txtLabel.setY(y-25); 
				 txtLabel.setX(x-25);
				 
				 group.getChildren().addAll(circle,txtLabel);
		
				
			}else {
				
				((CrimeType<Integer>) vertex).setX(x);
				((CrimeType<Integer>) vertex).setY(y);
				
				circle = new Circle(x,y,20,Color.LIGHTBLUE);
				
				 txtLabel = new Text(((CrimeType<Integer>) vertex).getCrimeType());
				 txtLabel.setFont(Font.font("Verdana",15)); 
				 txtLabel.setFill(Color.BLACK);
				 txtLabel.setY(y-25);
				 txtLabel.setX(x-25);
				 
		
				 group.getChildren().addAll(circle,txtLabel);
				
			}
			
		}
		
		for(Edge<Integer> edge : graph.getEdges()) {
			
			double X1 = ((Demographic<Integer>) edge.getFromVertex()).getX() ;
			double Y1 = ((Demographic<Integer>) edge.getFromVertex()).getY() ;
			double X2 =  ((CrimeType<Integer>) edge.getToVertex()).getX();
			double Y2 =  ((CrimeType<Integer>) edge.getToVertex()).getY();
			
			line = new Line(X1, Y1, X2, Y2);
			txtValue = new Text(String.valueOf(edge.getCost()));
			txtValue.setX((X1+X2)/2 - 7);
			txtValue.setY((Y1+Y2)/2 - 7);
			group.getChildren().addAll(line, txtValue);
		}
		
	}
	
	public void AddEdges() {
	    popup = new Stage();
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Add Edges");
		
		comboBox = new ComboBox<>();
		comboBox2 = new ComboBox<>();
		comboBox3 = new ComboBox<>();
		
		for(int  i = 0 ; i <= 100; i++) {
			comboBox3.getItems().add(String.valueOf(i));
		}
		
		
		for (Vertex<Integer> vertex : vertexs) {
			
			if(vertex instanceof Demographic) {
				String nameString = ((Demographic<Integer>) vertex).getArea();
				demos.add(vertex);
				comboBox.getItems().add(nameString);
			}else {
				String nameString = ((CrimeType<Integer>) vertex).getCrimeType();
				types.add(vertex);
				comboBox2.getItems().add(nameString);
			}
			
		}
		comboBox.getSelectionModel().selectFirst();
		comboBox2.getSelectionModel().selectFirst();
		comboBox3.getSelectionModel().selectFirst();
		
		V1.setMinWidth(100);
		V2.setMinWidth(100);
		cost.setMinWidth(100);
		
		V1.getChildren().add(comboBox);
		V2.getChildren().add(comboBox2);
		v1.getChildren().add(comboBox3);
		
		GridPane root = new GridPane();
		root.setHgap(10);
		root.setVgap(10);
		root.add(txtV1, 1, 2);
		root.add(V1, 1, 3);
		root.add(txtV2, 3, 2);
		root.add(V2, 3, 3);
		root.add(txtCost, 4, 2);
		root.add(v1, 4, 3);
		root.add(btnadd, 1, 4);
		root.add(btnCancel, 2, 4);
		Scene scene = new Scene(root,600,200);
		popup.setScene(scene);
		
		popup.showAndWait();
		
	
		
	}
	
	public void RemoveVertex() {
		
		comboBox6 =  new ComboBox<>();
		comboBox7 = new ComboBox<>();
		  popup2 = new Stage();
		  popup2.initModality(Modality.APPLICATION_MODAL);
		  popup2.setTitle("Remove Vertex");
		  
		  
		  for(Vertex<Integer> v : vertexs) {
			  
			  
			  if(v instanceof Demographic) {
				    String edFrom = (((Demographic<Integer>) v).getArea());
				     comboBox6.getItems().add(edFrom);
				    
			  }
			  
			if(v instanceof CrimeType) {
				String edTo = (((CrimeType<Integer>) v).getCrimeType());
				 comboBox6.getItems().add(edTo);
				
			}
			  
			 
			 
		  }
		  
		  comboBox6.getSelectionModel().selectFirst();
		
			
		  vbox3.setMinWidth(100);
		
	
			
			vbox3.getChildren().add(comboBox6);
		
		
			
			GridPane root = new GridPane();
			root.setHgap(10);
			root.setVgap(10);
			root.add(txtvb3, 1, 2);
			root.add(vbox3, 1, 3);
			root.add(btnremove, 1, 4);
			root.add(btnde, 2, 4);
			Scene scene = new Scene(root,600,200);
			popup2.setScene(scene);
			
			popup2.showAndWait();

		
	}
	
	public void shortestpath(){
		
	}

	public void RemoveEdge() {
		


		comboBox4 =  new ComboBox<>();
		comboBox5 = new ComboBox<>();
		  popup1 = new Stage();
		  popup1.initModality(Modality.APPLICATION_MODAL);
		  popup1.setTitle("Add Edges");
		  
		  
		  for(Edge<Integer> edge : graph.getEdges()) {
			  
			  costed = edge.getCost();
			  
			  if(edge.getFromVertex() instanceof Demographic) {
				    String edFrom = ((Demographic<Integer>) edge.getFromVertex()).getArea();
				     comboBox4.getItems().add(edFrom);
				    
			  }
			  
			if(edge.getToVertex() instanceof CrimeType) {
				String edTo = ((CrimeType<Integer>) edge.getToVertex()).getCrimeType();
				 comboBox5.getItems().add(edTo);
				
			}
			  
			 
			 
		  }
		  
		  comboBox4.getSelectionModel().selectFirst();
			comboBox5.getSelectionModel().selectFirst();
			
		  vbox1.setMinWidth(100);
			vbox2.setMinWidth(100);
			
			
			vbox1.getChildren().add(comboBox4);
			vbox2.getChildren().add(comboBox5);
		
			
			GridPane root = new GridPane();
			root.setHgap(10);
			root.setVgap(10);
			root.add(txtvb1, 1, 2);
			root.add(vbox1, 1, 3);
			root.add(txtvb2, 3, 2);
			root.add(vbox2, 3, 3);
			root.add(btnremove, 1, 4);
			root.add(btndelete, 2, 4);
			Scene scene = new Scene(root,600,200);
			popup1.setScene(scene);
			
			popup1.showAndWait();
			
		}
		
	
	
	
	
}
