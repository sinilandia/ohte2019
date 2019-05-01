package ui;

import domain.RaidService;
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
    
    Label menuLabel;

    @Override
    public void init() throws Exception {
        this.raidService = new RaidService();
        this.menuLabel = new Label();
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
                //primaryStage.setScene(signUpForRaidScene);  
                usernameInput.setText("");
                loginMessage.setText("yay logged in!");
                loginMessage.setTextFill(Color.BLACK);
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
   
        // new createNewUserScene
        //add scene components here
        
        
        // main scene       
        //ScrollPane todoScollbar = new ScrollPane();       
        
        
        // seutp primary stage
        
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
