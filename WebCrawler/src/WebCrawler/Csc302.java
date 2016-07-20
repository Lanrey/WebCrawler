/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc302;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.jsoup.Jsoup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import java.awt.Desktop; 
import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.scene.text.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.*;
import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser;


public class Csc302 extends Application{

  List<String> list = new ArrayList<>();
     ScrollPane gridholder;
    File newTextFile;
    String file_path;
    int grid_count = 0;
  //  private Scanner source ;
     private List<String> results;
    int count = 0;
    //Gridpane containing project title
    GridPane Project_title;
    GridPane grid;
    TextField textfield;
    GridPane resultGrid;
    Button submit_button;
    
    //This gridpane holds the status bar and the interface for submission
    GridPane compactGrid;
    
    //This is the interface that displays the uploaded or submitted documents
    Submit_Page submit_page;
    
    FileChooser fileChooser;
    
    private final Desktop desktop = Desktop.getDesktop();
           
    @Override
    public void start(final Stage primaryStage) {
         fileChooser = new FileChooser();
     
      primaryStage.setTitle("Concurrency Project");    
        Project_title = new Project_Title(primaryStage);
        
        //This is the css for the software
        
         String css = "-fx-background-color: lightgrey;\n"
                       +"-fx-border-color:  #f9fbf9;\n"
                       +"-fx-padding: 2;\n"
                       +"-fx-border-width: 6;\n;";
         
       String css1 = "-fx-background-color: #f9fbf9;\n"
                       +"-fx-border-color:  #f9fbf9;\n"
                       +"-fx-padding: 2;\n"
                       +"-fx-border-width: 6;\n;";
       
       //This is the css for the document submission interface
        String css2 = "-fx-background-color: white;\n"
                       +"-fx-border-color:  grey;\n"
                       +"-fx-border-insets: 1 1 1 1;\n"
                       +"-fx-border-radius:6,3,2;\n"
                       +"-fx-background-radius:6,3,2;\n"
                       +"-fx-border-image-insets: 0;\n"
                       
                       +"-fx-padding: 2;\n";
        
        //This is the css for the document submission interface
        String css3 = "-fx-background-color: white;\n"
                       +"-fx-padding: 5;\n";
                       
        
       
       grid = new GridPane();
       resultGrid = new GridPane();
       grid.setStyle(css);
       gridholder = new ScrollPane();
       gridholder.prefHeightProperty().bind(grid.prefHeightProperty());
       gridholder.prefWidthProperty().bind(grid.prefWidthProperty());
       gridholder.setContent(grid);
       gridholder.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
       gridholder.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
       
       grid.add(Project_title, 0, 0);
       grid.prefWidthProperty().bind(primaryStage.widthProperty().subtract(20));
       grid.prefHeightProperty().bind(primaryStage.heightProperty());
       grid.setHgap(10);
       grid.setVgap(10);
       
       resultGrid.setStyle(css1);
       resultGrid.prefWidthProperty().bind(primaryStage.widthProperty().subtract(20));
       resultGrid.prefHeightProperty().bind(primaryStage.heightProperty());
       resultGrid.setHgap(10);
       resultGrid.setVgap(10);
       
       
       
        compactGrid = new GridPane();
        compactGrid.prefWidthProperty().bind(primaryStage.widthProperty());
        compactGrid.setPrefHeight(250);
        compactGrid.setStyle(css2);
        compactGrid.setHgap(20);
        compactGrid.setVgap(20);
       
        submit_page = new Submit_Page(compactGrid);
        
        //Create content for submit Page
        Text title = new Text("  Assignment Title");
        title.setFill(Color.BLACK);
        title.setStroke(Color.BLACK);
        title.setStrokeWidth(0.3);
        title.setFont(Font.font ("", 14));
        
        textfield = new TextField();
        textfield.prefWidthProperty().bind(primaryStage.widthProperty().subtract(20));
        textfield.setPromptText("Enter title of Assignment/Document here");
        textfield.setMinHeight(30);
        
        Text file = new Text("  Uploaded File/s");
        file.setFill(Color.BLACK);
        file.setStroke(Color.BLACK);
        file.setStrokeWidth(0.3);
        file.setFont(Font.font ("", 14));
        
        
        compactGrid.add(title, 0, 0);
        compactGrid.add(textfield, 0, 1);
        compactGrid.add(file, 0, 2);
        compactGrid.add(submit_page, 0, 3);
         
         GridPane button_grid = new GridPane();
         button_grid.setHgap(30);
         button_grid.setVgap(30);
         button_grid.setStyle(css3);
         button_grid.setHgap(10);
         button_grid.setVgap(10);
         //This button enables user select file from document
         submit_button = new Button("submit");
          submit_button.setMinHeight(20);
          submit_button.setMinWidth(20);
          
          
          //setting the handler for the select_button
          submit_button.setOnAction((ActionEvent t) -> {
              openDialog(primaryStage);
         });   
          
          
          //This button enables user select file from document
          Button scan_button = new Button("scan");
          scan_button.setMinHeight(20);
          scan_button.setMinWidth(20);
          
          scan_button.setOnAction((ActionEvent t) -> {
              resultGrid.getChildren().clear();
              List<String> urls = new ArrayList<>();
              urls = getUrls(file_path);
              //urls.add("http://javahonk.com/pattern-matcher-java/");
              //urls.add("https://docs.oracle.com/javase/tutorial/essential/regex/");
              //urls.add("http://stackoverflow.com/questions/7459263/regex-whole-word");
              //urls.add("http://kodejava.org/how-do-i-write-embedded-flag-expression/");
              //urls.add("http://blog.codinghorror.com/excluding-matches-with-regular-expressions/");
              //urls.add("https://github.com/ocpsoft/regex-tester/blob/master/src/test/java/org/ocpsoft/tutorial/regex/server/RegexParserImplTest.java");
              //urls.add("http://www.devtoolkit.org/tools/reg-exp!java.html");
              //urls.add("https://code.google.com/p/totallylazy/source/browse/test/com/googlecode/totallylazy/regex/RegexTest.java?r=a4a6ac3d443db02821d434a5d6560cb77ec4ef4a&spec=svna4a6ac3d443db02821d434a5d6560cb77ec4ef4a");
              
              results = new ArrayList<>();
              String submit = "";
              List<String> result = new ArrayList<>();
              try {
                  Scanner source1 = new Scanner(new File(file_path));
                  while (source1.hasNext()) {
                      String fragment = source1.next();
                      results.add(fragment);
                      submit += fragment;
                  }
              }catch(Exception ex){
                  
              }    if(!urls.isEmpty()){
                  
                  ExecutorService application =
                          Executors.newCachedThreadPool();
                  
                  for (String url : urls) {
                      application.execute(new Crawl(results, submit, url));
                  }
                  
              }
         });
          
          
          button_grid.add(submit_button, 0, 0);
          button_grid.add(scan_button, 1, 0);
          
        compactGrid.add(button_grid, 0, 4); 
       
         grid.add(compactGrid, 0, 1);
         grid.add(resultGrid, 0, 2);
          
        
       StackPane root = new StackPane();
       root.getChildren().add(gridholder);
       primaryStage.setScene(new Scene(root, primaryStage.getWidth(),
               primaryStage.getHeight()));
       primaryStage.show();
    }
    
     public static void main(String[] args) {
        launch(args);
    }
    
    public void openDialog(Stage stage){
        fileChooser.setTitle("Select Document");
        fileChooser.setInitialDirectory(
            new File(System.getProperty("user.home")));
            File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                         file_path = file.getAbsolutePath();
                         Text text = new Text();
                         text.setText("Document located at "+file_path+" was uploaded");
                         text.setFont(Font.font ("Verdana", 13));
                         text.setFill(Color.BLACK);
                         
                         submit_page.add(text, 0, count);
                         submit_button.setDisable(true);
                   }
          }
     public List<String> getUrls(String location){
        List<String> urlist = new ArrayList<>();
        List<String> e_results = new ArrayList<>();
        List<String> matches = new ArrayList<>();
        String query = "";
        String e_page = "";
        try{
       
        Scanner the_source = new Scanner(new File(location));
            while(the_source.hasNext() ){
                String fragment = the_source.next();
                e_results.add(fragment);
                e_page += fragment + " ";
            }
        }
        catch(Exception ex){
            
        }
        
        matches = getOcurrence(e_results, e_page);
        if(matches.size() > 16){
        for(int i = 0; i <  16; i++){
            int idx = new Random().nextInt(matches.size());
            query +=  matches.get(idx) + " ";
          }
        }
        else
        {
            for (String matche : matches) {
                query += matche + " ";
            }
            
        }
        
        
        try{          
            
                   String search_query = textfield.getText() + " " + query;
                    for (int i = 0; i < 8; i = i + 4) {
	            String address = "http://ajax.googleapis.com/ajax/"
                            + "services/search/web?v=1.0&start="+i+"&q=";
	            String charset = "UTF-8";
                    try{
	             URL url = new URL(address +
                             URLEncoder.encode(search_query, charset));
	             Reader reader = 
                             new InputStreamReader(url.openStream(), charset);
	             Retrieve web_results =
                             new Gson().fromJson(reader, Retrieve.class);    
                     // Show title and URL of each results
                    for (int m = 0; m <= 3; m++) {
                    @SuppressWarnings("ThrowableResultIgnored")
                    String TITLE = 
                   web_results.getResponseData().getResults().get(m).getTitle();
                    String URL =
                       web_results.getResponseData().getResults().get(m).getUrl();
                    System.out.println("Title: " + web_results.getResponseData()
                            .getResults().get(m).getTitle());
                    System.out.println("URL: " + web_results.getResponseData().
                            getResults().get(m).getUrl() + "\n");
                    urlist.add(URL);}   }
                    catch(IOException | JsonSyntaxException | JsonIOException ex)
                    {
                       System.out.print("No Internet Connection Available\n"); 
                    }}
               
               }
                catch(Exception ex)
                {
                } 
              
        
       
            
        return urlist;
        
    }
     
    public  List<String> getOcurrence(List<String> submit, String resource){
        String[] tokens_1 = resource.split( "\\s" );       
        List<String> results2 = Arrays.asList(tokens_1);
        List<String> lower_occurences = new ArrayList<>();
        
        submit.stream().forEach((submit1) -> {
            int frequency = Collections.frequency(results2, submit1);
          if ((frequency < 2) && (submit1.length() > 4) && (submit1.length() < 14)) {
              lower_occurences.add(submit1);
          }
      });
        
        return lower_occurences;
    }
    
        private class Crawl implements Runnable{
        String submitted;
        String thread;
        String website;
        List<String> tokens;
        
        public  Crawl(List<String> tokenized_file, String file, String url){
            tokens = tokenized_file;
            submitted = file;
            website = url;
        }
        
        @Override
        public synchronized void run(){
            thread = Thread.currentThread().getName();
            main_crawler();
       }
        
        public synchronized void main_crawler(){
             thread = Thread.currentThread().getName();
              Platform.runLater(new Runnable(){
                  @Override
                  public void run(){
                     Scan scan = new Scan(tokens, submitted, website);
                     List<String> response = scan.getResult();
         
                 if(response != null && response.size() > 1){
                   
                   response.add(0, thread);
                   response.add(4, website);
                   
                       
                 GridPane report = new ReportPane(response, grid);
                 resultGrid.add(report, 0, grid_count);
                 grid_count++;
                       
                       
                for(int i = 0; i < response.size(); i++){
                   System.out.print(response.get(i)+"\n");
                   }
                 System.out.print("\n\n\n");
                
                 }}
              });  
              
        }
    }
}
 class Scan{
    private final List<String> results;
    String result;
    String site;
    String site_content;
    
    private List<String> e_results;
    private Scanner source;
    private Scanner Url;
    private List<String> Matching_words;
    private int count;
    
    
    public Scan(List<String> tokens, String file, String url){
        results = tokens;
        result = file;
        site = url;
        
    }
    
    public List<String> getResult(){
        site_content = generate_url_content(site);
        e_results = compare(results, result, site_content);
        
        return e_results;
    }
    
    private String generate_url_content(String url){
        try{
                 URL website = new URL(url);
                 Url = new Scanner(new InputStreamReader
                         (website.openStream()));
                         
                 }
                 catch(IOException ex){}
                 StringBuilder sb = new StringBuilder();
                 String string = "";
                 String total = "";  
                 try{
                 
                 while(Url.hasNext()){
                 sb.append(Url.nextLine());}}
                 catch(Exception ex){}
                 string = Jsoup.parse(sb.toString()).text();
               
                 return string;
    }
    
    private List<String> compare(List<String> list, String file_1, String file_2){
        List<String> matches = new ArrayList<>();
        List<String> search_details = new ArrayList<>();
        String report = " ";
        int size_of_file = list.size();
        int match = 0;
        for(int i = 0 ; i < list.size(); i++){
           if(file_2.contains(list.get(i)))
               match++;         
            }
        
        search_details.add("No of matches found:  "+match+" words matched out of " +size_of_file);
        
        int percentage_match = (match * 100)/size_of_file;
        
        search_details.add("Percentage Match Found:  "+percentage_match+"");
        
        if((percentage_match >= 0) && (percentage_match <= 20)){
            report = "Conclusion:  a minor section of the submitted document was found";
        }
        
        if((percentage_match >= 0 ) && (percentage_match <= 45)){
            report = "Conclusion:  a section of the submitted document was found";
        }
        
        else if((percentage_match > 40 ) && (percentage_match <= 70)){
            report = "Conclusion:  A large section of the submitted document was found";
        }
        
        else{
            report = "Conclusion:  Most of the submitted document was found";
        }
        
       search_details.add(report);
        
        return search_details;
    }
    
   
}
class Submit_Page extends GridPane {
    
    public Submit_Page(GridPane pane){
        String css1 = "-fx-background-color: white;\n"
                       +"-fx-border-color: gray;\n"
                       +"-fx-border-insets: 1 1 1 1;\n"
                       +"-fx-border-radius:6,3,2;\n"
                       +"-fx-background-radius:6,3,2;\n"
                       +"-fx-padding: 10;\n"
                       +"-fx-border-spacing: 0;\n;";
        
         
        
        setMinHeight(150);
        prefWidthProperty().bind(pane.widthProperty().subtract(15));
        setStyle(css1);
    }
    
}
   
    

