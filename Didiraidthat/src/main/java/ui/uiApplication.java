package ui;

import domain.RaidService;
import domain.Raid;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    private String username;

    @Override
    public void init() {
        this.raidService = new RaidService();
        this.menuLabel = new Label();
    }
    
    public Node createRaidNode(Raid raid) {
        HBox hbox = new HBox(10);
        Label label  = new Label(raid.toString());
        label.setMinHeight(90);
        Button button = new Button("Sign up");
        button.setOnAction(e->{
            raidService.signUpForRaid(raid.getId());           
            button.setText("GOING"); 
            button.setDisable(true); 
        });
               
        //disable button if user has already signed up for the raid
        List<Raid> usersRaids = raidService.findUsersRaids();
        for (int i = 0; i < usersRaids.size(); i++) {
            if (raid.getId()==usersRaids.get(i).getId()) {
                button.setText("GOING"); 
                button.setDisable(true); 
            }
        }
                
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        hbox.setPadding(new Insets(5,5,5,5));
        
        hbox.getChildren().addAll(label, spacer, button);
        return hbox;
    }  
    
    public void redrawActiveRaids() {       
        raidNodes.getChildren().clear();      
        List<Raid> allRaids = raidService.findUpcomingRaids(); 
        allRaids.forEach(raid -> {
            raidNodes.getChildren().add(createRaidNode(raid));
        }); 
    }
    
    // My raids Scene: create raid Node without signUpForRaidButton 
    public Node createUsersRaidNode(Raid raid) {
        HBox hbox = new HBox(10);
        Label label  = new Label(raid.toString());
        label.setMinHeight(90);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        hbox.setPadding(new Insets(5,5,5,5));   
        hbox.getChildren().addAll(label, spacer);
        return hbox;
    }  
    
    // My raids Scene: shows user's raids, both past and present 
    public void drawUsersRaids() {       
        raidNodes.getChildren().clear();      
        List<Raid> allRaids = raidService.findUsersRaids();
        allRaids.forEach(raid -> {
            raidNodes.getChildren().add(createUsersRaidNode(raid));
        }); 
    }
    
    public void createRaid() {
        VBox createRaidPane = new VBox(10);
        createRaidPane.setPadding(new Insets(10));
        
        //user input for Gym name
        HBox gymPane = new HBox(10);       
        Label gymLabel = new Label("Gym name:");
        TextField gymNameInput = new TextField();       
        gymPane.getChildren().addAll(gymLabel, gymNameInput);
        
        //user chooses if the gym is an EX Gym
        HBox exPane = new HBox(10);
        Label exLabel = new Label("Is it an EX Gym? Yes/No");
        ChoiceBox exBox = new ChoiceBox();
        exBox.getItems().add("Yes");
        exBox.getItems().add("No");
        exPane.getChildren().addAll(exLabel, exBox);
        
        //user input for raid level
        HBox levelPane = new HBox(10);
        Label levelLabel = new Label("Raid level (1-5):");
        TextField levelInput = new TextField();
        levelPane.getChildren().addAll(levelLabel, levelInput);
        
        //user input for starting time in hours and minutes
        HBox hoursPane = new HBox(10);
        Label hoursLabel = new Label("Start time in hours (HH):");
        TextField hoursInput = new TextField();
        hoursPane.getChildren().addAll(hoursLabel, hoursInput);
        HBox minutesPane = new HBox(10);
        Label minutesLabel = new Label("Start time in minutes (mm):");
        TextField minutesInput = new TextField(); 
        minutesPane.getChildren().addAll(minutesLabel, minutesInput);       
        
        //add raid Button
        Button addRaidButton = new Button("Add raid");
        addRaidButton.setOnAction(e->{
                String gymName = gymNameInput.getText();
                String ex = (String) exBox.getValue();
                String level = levelInput.getText();
                String hours = hoursInput.getText();
                String minutes = minutesInput.getText();
                
                if (gymName.length() > 15) {
                    menuLabel.setText("Gym name too long. Name can be max 15 characters.");  
                    menuLabel.setTextFill(Color.RED);
                } else if (level.length() > 2 || 
                        !(level.equals("1") || 
                        level.equals("2") ||
                        level.equals("3") ||
                        level.equals("4") ||
                        level.equals("5") ||
                        level.equals("EX"))) {
                    menuLabel.setText("Level can be 1-5 or EX.");
                    menuLabel.setTextFill(Color.RED);
                } else {                
                    if (raidService.createRaid(gymName, ex, level, hours, minutes)) {
                        menuLabel.setText("Raid created succesfully!");
                        menuLabel.setTextFill(Color.BLACK);
                    } else {
                        menuLabel.setText("Raid info was incorrect. Raid not created.");
                        menuLabel.setTextFill(Color.RED);
                    }
                    
                }
        });  
        
        createRaidPane.getChildren().addAll(gymPane, exPane, levelPane, 
                hoursPane, minutesPane, addRaidButton);  
        raidNodes.getChildren().clear();
        raidNodes.getChildren().addAll(createRaidPane);
    }
    
    public void setMenuLabel() {
        menuLabel.setText("Welcome, " + username);
        menuLabel.setTextFill(Color.BLACK);
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
            this.username = usernameInput.getText();
            setMenuLabel();
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
        
        
        // menu bar for buttons in after login scene
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
        
        //Sign up for raids Scene: active raids
        raidNodes = new VBox(10);
        raidNodes.setMaxWidth(500);
        raidNodes.setMaxHeight(350);
        
        ScrollPane raidScrollbar = new ScrollPane();  
        raidScrollbar.setContent(raidNodes);
        layout.setCenter(raidScrollbar);
        
        signUpForRaidScene = new Scene(layout, 500, 400);
              
        //click button My Raids
        myRaidsButton.setOnAction(e->{  
            setMenuLabel();
            drawUsersRaids();
        });  
              
        //click button Sign Up For Raid
        signUpButton.setOnAction(e->{  
            setMenuLabel();
            redrawActiveRaids();
        });
            
        //click createRaidButton
        createRaidButton.setOnAction(e->{ 
            setMenuLabel();
            createRaid();
        });  

        // setup primary stage
        layout.setBottom(menuLabel);
        primaryStage.setTitle("Did I Raid That?");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
    
    @Override
    public void stop() {
        //Goodbye hooman!
    }    
    
    public static void main(String[] args) {
        launch(args);
    }
        
}
