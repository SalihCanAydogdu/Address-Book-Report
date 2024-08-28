
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

	PersonService personService;

	TextField idText = new TextField();
	TextField searchUpdateIdText = new TextField();
	TextField nameText = new TextField();
	TextField streetText = new TextField();
	TextField cityText = new TextField();
	TextField genderText = new TextField();
	TextField zipText = new TextField();

	Button addButton = new Button("Add");
	Button firstButton = new Button("First");
	Button nextButton = new Button("Next");
	Button previousButton = new Button("Previous");
	Button lastButton = new Button("Last");
	Button searchByIdButton = new Button("SearchById");
	Button updateByIdButton = new Button("UpdateById");
	Button clearButton = new Button("Clean TextFields");

	Label idLable = new Label("Id");
	Label searchUpdateIdLable = new Label("Search/UpdateId");
	Label nameLable = new Label("Name");
	Label streetLable = new Label("Street");
	Label cityLable = new Label("City");
	Label genderLable = new Label("Gender");
	Label zipLable = new Label("Zip");

	@Override
	public void start(Stage primaryStage) throws IOException {
		personService = new PersonService();

		idText.setEditable(false);
		idText.setDisable(true);
		idText.setPrefColumnCount(4);
		genderText.setPrefColumnCount(1);
		zipText.setPrefColumnCount(4);
		cityText.setPrefColumnCount(12);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Look, an Information Dialog");

		GridPane p1 = new GridPane();
		p1.setAlignment(Pos.CENTER);
		p1.setHgap(5);
		p1.setVgap(5);

		p1.add(nameLable, 0, 1);
		p1.add(nameText, 1, 1);

		p1.add(streetLable, 0, 2);
		p1.add(streetText, 1, 2);

		p1.add(cityLable, 0, 3);

		p1.add(idLable, 0, 0);

		HBox p4 = new HBox(5);
		p4.getChildren().addAll(idText, searchUpdateIdLable, searchUpdateIdText);
		p1.add(p4, 1, 0);

		HBox p2 = new HBox(5);
		p2.getChildren().addAll(cityText, genderLable, genderText, zipLable, zipText);
		p1.add(p2, 1, 3);

		HBox p3 = new HBox(5);
		p3.getChildren().addAll(addButton, firstButton, nextButton, previousButton, lastButton, updateByIdButton,
				searchByIdButton, clearButton);
		p3.setAlignment(Pos.CENTER);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(p1);
		borderPane.setBottom(p3);

		Scene scene = new Scene(borderPane, 550, 180);
		primaryStage.setTitle("Address Book Project");
		primaryStage.setScene(scene);
		primaryStage.show();

		setPersonInfo(personService.getCurrent());

		clearButton.setOnAction(event -> cleanText());

		addButton.setOnAction(event -> {
			Person person = new Person(nameText.getText(), genderText.getText(), streetText.getText(),
					cityText.getText(), zipText.getText());
			try {
				personService.addPerson(person);
				setPersonInfo(person);
				alert.setContentText("The record is added successfully");
				alert.showAndWait();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		lastButton.setOnAction(event -> {
			if (personService.getCurrentIndex() == personService.getSize() -1) {
				alert.setContentText("Already on Last record.");
				alert.showAndWait();
			}else {
				Person person = personService.getLast();
				setPersonInfo(person);
			}

		});
		
		firstButton.setOnAction(event -> {
			if (personService.getCurrentIndex() == 0) {
				alert.setContentText("Already on first record.");
				alert.showAndWait();
			}else {
				Person person = personService.getFirst();
				setPersonInfo(person);
			}

		});
		
		previousButton.setOnAction(event -> {
			if (personService.getCurrentIndex() == 0) {
				alert.setContentText("You are on first record.");
				alert.showAndWait();
			}else {
				Person person = personService.previous();
				setPersonInfo(person);
			}

		});
		
		nextButton.setOnAction(event -> {
			if (personService.getCurrentIndex() == personService.getSize() -1) {
				alert.setContentText("You are on Last record.");
				alert.showAndWait();
			}else {
				Person person = personService.next();
				setPersonInfo(person);
			}

		});
		
		searchByIdButton.setOnAction(event -> {
			Person person =  personService.search(Integer.parseInt(searchUpdateIdText.getText()));
			if (person == null) {
				alert.setContentText("Can't find person with id: " + searchUpdateIdText.getText());
				alert.showAndWait();
			}else {
				setPersonInfo(person);
			}
		});
		
		updateByIdButton.setOnAction(event -> {
			Person person =  personService.update(Integer.parseInt(searchUpdateIdText.getText()),  new Person(nameText.getText(), genderText.getText(), streetText.getText(),
					cityText.getText(), zipText.getText()));
			if (person == null) {
				alert.setContentText("Can't find person with id: " + searchUpdateIdText.getText());
				alert.showAndWait();
			}else {
				setPersonInfo(person);
			}
		});


	}

	private void cleanText() {
		idText.setText("");
		searchUpdateIdText.setText("");
		nameText.setText("");
		streetText.setText("");
		cityText.setText("");
		genderText.setText("");
		zipText.setText("");
	}

	private void setPersonInfo(Person person) {
		if (person != null) {
			idText.setText(String.valueOf(person.getId()));
			nameText.setText(person.getName());
			streetText.setText(person.getStreet());
			cityText.setText(person.getCity());
			genderText.setText(person.getGender());
			zipText.setText(person.getZip());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
