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
    private JTextField terrain = new JTextField(10);
    private JTextField tempo = new JTextField(4);
    private JTextField where = new JTextField(10);
    private JTextField repetitions = new JTextField(4);
    private JTextField recovery = new JTextField(4);
    private JTextField trainingtext = new JTextField(10);
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JLabel labtraining = new JLabel("Training:");
    private JLabel labterrain = new JLabel("Terrain:");
    private JLabel labtempo = new JLabel("Tempo:");
    private JLabel labwhere = new JLabel("Where:");
    private JLabel labrepetitions= new JLabel("Repetitions:");
    private JLabel labrecovery = new JLabel("Recovery:");
    private JButton addR = new JButton("Add");
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton findAllByDate = new JButton("Find all by date"); //adding new button to jframe
    String trainings[]= { "Sprints","Swimming","Cycle"};
    private JList training= new JList(trainings);


    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(5, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        add(labn);
        add(name);
        name.setEditable(true);
        add(labtraining);
        add(trainingtext);
        training.setSelectionMode(ListSelectionModel.SINGLE_SELECTION ); //
        training.addListSelectionListener(this::valueChanged);
        add(training);
        training.setSize(10,15);

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
        add(labterrain);
        add(terrain);
        terrain.setEditable(true);
        add(labtempo);
        add(tempo);
        tempo.setEditable(true);
        add(labwhere);
        add(where);
        where.setEditable(true);
        add(labrepetitions);
        add(repetitions);
        repetitions.setEditable(true);
        add(labrecovery);
        add(recovery);
        recovery.setEditable(true);
        add(addR);
        addR.addActionListener(this);
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        add(findAllByDate);                        //adding button "Find all by date" to the GUI
        findAllByDate.addActionListener(this);  //registering the button as a listener
        add(outputArea);
        outputArea.setEditable(false);
        setSize(720, 200);
        setVisible(true);
        blankDisplay();

        // To save typing in new entries while testing, uncomment
        // the following lines (or add your own test cases)
        
    } // constructor

    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        String message = "";
        if (event.getSource() == addR) {
            message = addEntry("generic");
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
        }
        if (event.getSource() == findAllByDate){
            message = lookupEntry();
        }
        outputArea.setText(message);
        blankDisplay();
    } // actionPerformed

    public String addEntry(String what) {
        String message = "Record added\n";
        System.out.println("Adding " + what + " entry to the records");
        //
        String n = name.getText();
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        float km = java.lang.Float.parseFloat(dist.getText());
        int h = Integer.parseInt(hours.getText());
        int mm = Integer.parseInt(mins.getText());
        int s = Integer.parseInt(secs.getText());
        int rep;
        int rec;
        String location;
        String ter;
        String temp;
        //
        Entry sprint;
        Entry swimming;
        Entry cycle;
        //
        if (training.isSelectedIndex(0)) {
            rep = Integer.parseInt(repetitions.getText());
            rec = Integer.parseInt(recovery.getText());
            sprint = new SprintEntry(n, d, m, y, h, mm, s, km, rep, rec);
            myAthletes.addEntry(sprint);
        } else if (training.isSelectedIndex(1)) {
            location = where.getText();
            swimming = new SwimEntry(n, d, m, y, h, mm, s, km, location);
            myAthletes.addEntry(swimming);
        } else {
            ter = terrain.getText();
            temp = tempo.getText();
            cycle = new CycleEntry(n, d, m, y, h, mm, s, km, ter, temp);
            myAthletes.addEntry(cycle);
        }
                return message;
            }


    public String lookupEntry() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.lookupEntry(d, m, y);
        return message;
    }

    public void valueChanged(ListSelectionEvent e) { {
            if (training.isSelectedIndex(0)) {
                trainingtext.setText("Sprint");
                where.setEditable(false);
                terrain.setEditable(false);
                tempo.setEditable(false);
            }
            else if (training.isSelectedIndex(1)){
                trainingtext.setText("Swim");
                repetitions.setEditable(false);
                terrain.setEditable(false);
                tempo.setEditable(false);
                recovery.setEditable(false);
            }
            else{
                trainingtext.setText("Cycle");
                repetitions.setEditable(false);
                where.setEditable(false);
                recovery.setEditable(false);
            }

        }
    }

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

