package eu.scislo.mobilenext;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RunLengthEncodingApp extends Application {

    private Mode mode = Mode.CODE;

    private Label sourceLabel = new Label("Źródło: ");
    private TextField source = new TextField();
    private Label resultLabel = new Label("Wynik operacji: ");
    private TextField result = new TextField();

    private Button copy = new Button("Kopiuj");
    private Button execute = new Button("Wykonaj");

    private Label operationLabel = new Label("Operacja: ");
    private RadioButton code = new RadioButton("kodowanie");
    private RadioButton decode = new RadioButton("dekodowanie");
    private ToggleGroup operation = new ToggleGroup();

    private ViewModel viewModel = new ViewModel();

    @Override
    public void start(Stage primaryStage) throws Exception {

        viewModel.setSource(">>JJJAAVAA<<");
        source.textProperty().bindBidirectional(viewModel.sourceProperty());
        result.textProperty().bindBidirectional(viewModel.resultProperty());


        source.setPrefWidth(250);
        code.setToggleGroup(operation);
        code.setUserData(Mode.CODE);
        decode.setToggleGroup(operation);
        decode.setUserData(Mode.DECODE);
        setMode();

        VBox rightVBox = new VBox();
        rightVBox.getStyleClass().add("right-pane");
        rightVBox.setAlignment(Pos.TOP_LEFT);
        rightVBox.getChildren().addAll(operationLabel, code, decode);

        VBox leftVBox = new VBox();
        leftVBox.getStyleClass().add("left-pane");
        leftVBox.getChildren().addAll(sourceLabel, source, copy, resultLabel, result, execute);

        BorderPane rootPane = new BorderPane();
        Scene scene = new Scene(rootPane, 450, 200);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setTitle("Run Length Encoding");
        primaryStage.setScene(scene);

        primaryStage.show();
        rootPane.setRight(rightVBox);
        rootPane.setLeft(leftVBox);

        // event handlers
        operation.selectedToggleProperty().addListener((observable) -> mode = (Mode) operation.getSelectedToggle().getUserData());
        execute.setOnAction(event -> onExecute());
        copy.setOnAction(event -> onCopy());

    }

    private void setMode() {
        if (mode == Mode.CODE) {
            code.setSelected(true);
        } else {
            decode.setSelected(true);
        }
    }

    private void onExecute() {
        if (mode == Mode.CODE) {
            try {
                viewModel.setResult(RunLengthEncoder.encode(viewModel.getSource()));
            } catch (InvalidInputException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
                alert.showAndWait();
            }
        } else {
            try {
                viewModel.setResult(RunLengthEncoder.decode(viewModel.getSource()));
            } catch (InvalidInputException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
                alert.showAndWait();
            }
        }
    }

    private void onCopy() {
        viewModel.setSource(viewModel.getResult());
        viewModel.setResult("");
        mode = (mode == Mode.CODE) ? Mode.DECODE : Mode.CODE;
        setMode();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
