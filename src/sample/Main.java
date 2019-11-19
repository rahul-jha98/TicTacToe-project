package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import sample.messaging.MessagingController;

import java.net.Socket;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        FlowPane board = (FlowPane) root.lookup("#boardPane");
        FlowPane messagePane = (FlowPane) root.lookup("#messagePane");

        Socket gameSocket = new Socket("127.0.0.1", 6000);
        //Socket messagingSocket = new Socket("192.168.43.191", 5000);

        Controller controller = new Controller(gameSocket, root);

        //MessagingController messagingController = new MessagingController(messagingSocket, messagePane);
        controller.getChildren().addAll(controller.getButtons());
        board.setPrefSize(GUI.FRAME_WIDTH, GUI.FRAME_HEIGHT);
        board.setMaxSize(GUI.FRAME_WIDTH, GUI.FRAME_HEIGHT);

        String css = this.getClass().getResource("style.css").toExternalForm();
        board.getStylesheets().add(css);



        board.getChildren().addAll(controller.getButtons());
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 700, 700));
        primaryStage.setMinHeight(700);
        primaryStage.setMinWidth(600);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
