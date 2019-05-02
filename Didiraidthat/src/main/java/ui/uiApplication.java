package ui;

import domain.Gym;
import domain.RaidService;
import domain.Raid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class uiApplication extends Application {
    private RaidService raidService;
    
    private Scene loginScene;
    private Scene signUpForRaidScene;
    private Scene createRaidScene;
    private Scene myRaidsScene;
    
    private Label menuLabel;
    private VBox raidNodes;

    @Override
    public void init() {
        this.raidService = new RaidService();
        this.menuLabel = new Label();
    }
    
    public Node createRaidNode(Raid raid) {
        HBox hbox = new HBox(10);
        Label label  = new Label(raid.toString());
//        label.setMinHeight(28);
//        Button button = new Button("Sign up");
//        button.setOnAction(e->{
//            raidService.signUpForRaid(raid.getId());
//            redrawActiveRaids();
//        }
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        hbox.setPadding(new Insets(5,5,5,5));
        
        hbox.getChildren().addAll(label, spacer); //add button
        return hbox;
    }
    
    public void redrawActiveRaids() {
        raidNodes.getChildren().clear();      
        List<Raid> allRaids = raidService.getRaided();
        allRaids.forEach(raid ->{
            raidNodes.getChildren().add(createRaidNode(raid));
        }); 
    }
    
    @Override
    public void start(Stage primaryStage) {  
        
        // login scene
        
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("username");
        TextField usernameInput = new TextField();
        
        inputPane.getChildren().addAll(loginLabel, usernameInput);
        Label loginMessage = new Label();
        
        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");
        loginButton.setOnAction(e->{
            String username = usernameInput.getText();
            menuLabel.setText("Welcome, " + username);
            if ( raidService.login(username) ){
                loginMessage.setText("");           
                primaryStage.setScene(signUpForRaidScene);  
                usernameInput.setText(""); 
                redrawActiveRaids();
            } else {
                loginMessage.setText("player does not exist");
                loginMessage.setTextFill(Color.RED);
            }      
        });  
        
        createButton.setOnAction(e->{
            if (raidService.addNewUser(usernameInput.getText())) {
            loginMessage.setText("player " + usernameInput.getText() + " created");
            loginMessage.setTextFill(Color.BLACK);
            usernameInput.setText(""); 
            } else {
                loginMessage.setText("couldn't create player");
                loginMessage.setTextFill(Color.RED);
            }
        });  
        
        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton);       
        
        loginScene = new Scene(loginPane, 300, 250);    
        
        
        // new signUpForRaidScene 
        BorderPane layout = new BorderPane();
        //menubar
        Button signUpButton = new Button("Sign up for a raid");
        Button createRaidButton = new Button("Create raid");
        Button myRaidsButton = new Button("My Raids");
        HBox menubar = new HBox(10);
        menubar.setPadding(new Insets(20, 20, 20, 20));
        menubar.setSpacing(10);
        menubar.getChildren().addAll(signUpButton, createRaidButton, myRaidsButton);
        layout.setTop(menubar);
        //active raids
        raidNodes = new VBox(10);
        raidNodes.setMaxWidth(500);
        raidNodes.setMaxHeight(350);
        redrawActiveRaids();
        
        ScrollPane raidScrollbar = new ScrollPane();  
        raidScrollbar.setContent(raidNodes);
        layout.setCenter(raidScrollbar);
        
        signUpForRaidScene = new Scene(layout, 500, 400);
        
        
        // setup primary stage
        
        primaryStage.setTitle("Did I Raid That?");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
    
    @Override
    public void stop() {
      // stop stuff?
      System.out.println("Goodbye.");
    }    
    
    public static void main(String[] args) {
        launch(args);
    }
        
}
