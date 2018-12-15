package eu.scislo.mobilenext;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RunLengthEncodingApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane rootPane = new BorderPane();
        Scene scene = new Scene(rootPane, 300, 275);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();

//        try {
////            System.out.println(RunLengthEncoder.encode("9992292929"));
//            System.out.println(RunLengthEncoder.encode("AAABBCCC"));
//            System.out.println(RunLengthEncoder.encode("AXV"));
//            System.out.println(RunLengthEncoder.encode(null));
//            System.out.println(RunLengthEncoder.encode(""));
//        } catch (InvalidInputException e) {
//            System.out.print(e.getMessage());
//        }

        try {
            System.out.println(RunLengthEncoder.decode(RunLengthEncoder.encode("AAABBCCC")));
            System.out.println(RunLengthEncoder.decode(RunLengthEncoder.encode("***((([[[]")));
        } catch (InvalidInputException e) {
            System.out.print(e.getMessage());
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
