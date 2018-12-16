package eu.scislo.mobilenext;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RunLengthEncodingApp extends Application {

    private Label sourceLabel = new Label("Źródło: ");
    private TextField source = new TextField();
    private Label resultLabel = new Label("Wynik operacji: ");
    private TextField result = new TextField();

    private Button copy = new Button("Kopiuj");
    private Button execute = new Button("Wykonaj");

    private Label operationLabel = new Label("Operacja: ");
    private RadioButton code = new RadioButton("kodowanie");
    private RadioButton decode = new RadioButton("dekodowanie");
    private ToggleGroupValue<Mode> operation = new ToggleGroupValue<>();

    private ViewModel viewModel = new ViewModel();

    @Override
    public void start(Stage primaryStage) throws Exception {

        // bidirectional bindings
        source.textProperty().bindBidirectional(viewModel.sourceProperty());
        result.textProperty().bindBidirectional(viewModel.resultProperty());
        operation.valueProperty().bindBidirectional(viewModel.modeProperty());

        source.setPrefWidth(250);
        code.setToggleGroup(operation);
        code.setUserData(Mode.CODE);
        decode.setToggleGroup(operation);
        decode.setUserData(Mode.DECODE);

        initialize();

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

        // button event handlers
        execute.setOnAction(event -> onExecute());
        copy.setOnAction(event -> onCopy());

    }


    private void onExecute() {
        if (viewModel.getMode() == Mode.CODE) {
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
        viewModel.modeEnumToggle();
    }

    /**
     * Initialization
     */
    private void initialize() {
        viewModel.setSource(">>JJJAAVAA<<");
        viewModel.setMode(Mode.CODE);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
