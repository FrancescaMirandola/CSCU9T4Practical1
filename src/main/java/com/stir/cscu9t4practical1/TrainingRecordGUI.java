// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JTextField terrain = new JTextField(10);      //creating textField to set the terrain of a cycle session
    private JTextField tempo = new JTextField(4);         //creating textField to set the tempo of a cycle session
    private JTextField where = new JTextField(10);        //creating textField to set the location of a swimming session
    private JTextField repetitions = new JTextField(4);   //creating textField to set the number of repetitions of a sprint session
    private JTextField recovery = new JTextField(4);      //creating textField to set the minutes of recovery in a sprint session
    private JTextField trainingtext = new JTextField(10); //creating textField to set the kind of training it was performed
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JLabel labtraining = new JLabel("Training:");       //creating label to training textField
    private JLabel labterrain = new JLabel("Terrain:");         //creating label to terrain textField
    private JLabel labtempo = new JLabel("Tempo:");             //creating label to tempo textField
    private JLabel labwhere = new JLabel("Where:");             //creating label to where textField
    private JLabel labrepetitions= new JLabel("Repetitions:");  //creating label to repetitions textField
    private JLabel labrecovery = new JLabel("Recovery:");       //creating label to recovery textField
    private JButton addR = new JButton("Add");
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton findAllByDate = new JButton("Find all by date");                //creating new button to jframe
    private JButton removeByDate = new JButton("Remove personal record by date");   //creating new button to jframe
    String trainings[]= { "Generic run","Sprints","Swimming","Cycle"};        //creating an array that stores the fields of 'training' list
    private JList training= new JList(trainings);                     //creating a list which displays the different possibilities of training
    //among which it's possible to choose when registering a record


    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(5, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.getHSBColor(250,300, 57));  //setting background color
        add(labn);
        add(name);
        name.setEditable(true);
        add(labtraining);   //adding the label to the layout
        add(trainingtext);  //adding the textField to the layout
        training.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );  //making list 'training' a single selection list
        training.addListSelectionListener(this::selectTraining);          //adding to list 'training' action listener
        add(training);    //adding the list 'training' to the layout
        training.setSize(10,15);  //setting the dimension of list 'training'
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);
        add(labterrain);    //adding the label to the layout
        add(terrain);       //adding the textField to the layout
        terrain.setEditable(true);  //setting the field to be editable
        add(labtempo);     //adding the label to the layout
        add(tempo);         //adding the textField to the layout
        tempo.setEditable(true); //setting the field to be editable
        add(labwhere);      //adding the label to the layout
        add(where);         //adding the textField to the layout
        where.setEditable(true);  //setting the field to be editable
        add(labrepetitions);    //adding the label to the layout
        add(repetitions);       //adding the textField to the layout
        repetitions.setEditable(true);  //setting the field to be editable
        add(labrecovery);       //adding the label to the layout
        add(recovery);          //adding the textField to the layout
        recovery.setEditable(true); //setting the field to be editable
        add(addR);
        addR.addActionListener(this);
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        add(findAllByDate);                         //adding button "Find all by date" to the GUI
        findAllByDate.addActionListener(this);   //adding to the button an action listener
        add(removeByDate);                         //adding button "Remove By Date" to the GUI
        removeByDate.addActionListener(this);   //adding to the button an action listener
        add(outputArea);
        outputArea.setEditable(false);
        setSize(720, 200);
        setVisible(true);
        blankDisplay();


    } // constructor

    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        String message = "";
        if (event.getSource() == addR) {
            message = addEntry("new record");
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
        }
        if (event.getSource() == findAllByDate){  //when the button is clicked,
            message = findAllByDate();           // call findAllByDate method and display its return message
        }
        if (event.getSource() == removeByDate) { //when the button is clicked,
            deleteEntry();                       //call deleteEntry method
        }
        outputArea.setText(message);
        blankDisplay();
        where.setEditable(true);               //after every action set the specified field back to editable
        terrain.setEditable(true);
        recovery.setEditable(true);
        tempo.setEditable(true);
        where.setEditable(true);
        repetitions.setEditable(true);
    } // actionPerformed

    //adds different Entry objects given a specified training
    public String addEntry(String what) {
        String message = "Record added successfully\n";
        System.out.println("Adding " + what + " entry to the records");
        //getting common inputs from the textFields
        String n = name.getText();
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        float km = java.lang.Float.parseFloat(dist.getText());
        int h = Integer.parseInt(hours.getText());
        int mm = Integer.parseInt(mins.getText());
        int s = Integer.parseInt(secs.getText());

        //getting more inputs based on the training chosen,
        //creating an Entry objects (based on the training chosen)
        //adding the object to myAthletes (TrainingRecord object)
        if (training.isSelectedIndex(0)){
            Entry run = new Entry(n, d, m, y, h, mm, s, km);
            myAthletes.addEntry(run);
        }if (training.isSelectedIndex(1)) {
            int rep = Integer.parseInt(repetitions.getText());
            int rec = Integer.parseInt(recovery.getText());
            Entry sprint = new SprintEntry(n, d, m, y, h, mm, s, km, rep, rec);
            myAthletes.addEntry(sprint);
        } else if (training.isSelectedIndex(2)) {
            String location = where.getText();
            Entry swimming = new SwimEntry(n, d, m, y, h, mm, s, km, location);
            myAthletes.addEntry(swimming);
        } else {
            String ter = terrain.getText();
            String temp = tempo.getText();
            Entry cycle = new CycleEntry(n, d, m, y, h, mm, s, km, ter, temp);
            myAthletes.addEntry(cycle);
        }
        training.clearSelection();             //clear the selection from the list
        return message;  //return "Record added successfully"
    }

    //takes the given inputs and looks up the last added entry of a given day, month and year
    public String lookupEntry() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.lookupEntry(d, m, y);
        return message;
    }

    // takes the given inputs and looks up all entries of a given day, month and year
    private String findAllByDate() {
        int m = Integer.parseInt(month.getText());     //get the month from the text area
        int d = Integer.parseInt(day.getText());       //get the day from the text area
        int y = Integer.parseInt(year.getText());      //get the year from the text area
        outputArea.setText("looking up records ...");  //display message in the text area
        String message = myAthletes.findAllByDate(d, m, y);     //call the method from TrainingRecord on myAthletes (TrainingRecord object)
        return message;                                //return the string where the result of the call is stored
    }

    // takes the given inputs and deletes a personal record of a given day, month and year
    public void deleteEntry(){
        String n= name.getText();                       //get the name from the text area
        int d = Integer.parseInt(day.getText());        //get the day from the text area
        int m = Integer.parseInt(month.getText());      //get the month from the text area
        int y = Integer.parseInt(year.getText());       //get the year from the text area
        outputArea.setText("looking up record to delete ...");  //display message in the text area
        myAthletes.deleteEntry(n,d,m,y);                //call the method from TrainingRecord on myAthletes (TrainingRecord object)
        outputArea.setText("Record deleted.");          //display message in the text area
    }

    //sets the type of training in the training textField when chosen from the list
    //makes only the chosen training related fields available
    public void selectTraining(ListSelectionEvent e) {
        if (training.isSelectedIndex(0)){         //if the training stored at index 0 of the list 'training' it's selected
            trainingtext.setText("Generic run");  //set the text to "Generic Run"
            where.setEditable(false);             //set all non-related compilation fields impossible to edit
            terrain.setEditable(false);
            tempo.setEditable(false);
            recovery.setEditable(false);
            repetitions.setEditable(false);

        }
        else if (training.isSelectedIndex(1)) {   //if the training stored at index 1 of the list 'training' it's selected
            trainingtext.setText("Sprint");       //set the text to "Sprint"
            where.setEditable(false);             //set all non-related compilation fields impossible to edit
            terrain.setEditable(false);
            tempo.setEditable(false);
        }
        else if (training.isSelectedIndex(2)){   //if the training stored at index 2 of the list 'training' it's selected
            trainingtext.setText("Swim");        //set the text to "Swim"
            repetitions.setEditable(false);      //set all non-related compilation fields impossible to edit
            terrain.setEditable(false);
            tempo.setEditable(false);
            recovery.setEditable(false);
        }
        else{
            trainingtext.setText("Cycle");     //if the training stored at index 3 of the list 'training' it's selected
            repetitions.setEditable(false);    //set the text to "Cycle"
            where.setEditable(false);
            recovery.setEditable(false);
        }

    }
    //sets textFields to blank
    public void blankDisplay() {
        name.setText("");
        trainingtext.setText(" ");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");
        terrain.setText("");
        tempo.setText("");
        where.setText("");
        repetitions.setText("");
        recovery.setText("");


    }// blankDisplay

    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
    }

} // TrainingRecordGUI

