package app;

import event.*;
import handler.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    public static EventBus eventBus;
    @Override
    public void start(Stage stage) throws IOException{
        Clock clock = new Clock();
        //initialize components and event types and subscribe appropriately
        ProgressMonitor progressMonitor = new ProgressMonitor();
        FileHandler fileHandler = new FileHandler();
        SpeedCheck speedCheck = new SpeedCheck();
        AccuracyCheck accuracyCheck = new AccuracyCheck();
        eventBus = new EventBus();
        eventBus.subscribe(fileHandler, new NewPromptRequested());

        eventBus.subscribe(progressMonitor, new KeyPress());
        eventBus.subscribe(progressMonitor, new NewWord());
        eventBus.subscribe(progressMonitor, new NewPromptLoaded());
        eventBus.subscribe(progressMonitor, new Accuracy());

        eventBus.subscribe(clock, new TestStart());
        eventBus.subscribe(clock, new TestComplete());

        eventBus.subscribe(speedCheck, new NewTick());
        eventBus.subscribe(speedCheck, new Index());

        eventBus.subscribe(accuracyCheck, new NewPromptLoaded());
        eventBus.subscribe(accuracyCheck, new Index());
        eventBus.subscribe(accuracyCheck, new KeyPress());
        eventBus.subscribe(accuracyCheck, new NewWord());
        eventBus.subscribe(accuracyCheck, new NewTick());

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);

        stage.setTitle("Unitype");
        stage.setScene(scene);
        stage.show();
    }
    public static EventBus getEventBus(){
        return eventBus;
    }
    public static void main(String[] args) {
        launch();
    }
}