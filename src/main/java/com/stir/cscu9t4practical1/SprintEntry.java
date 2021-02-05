package com.stir.cscu9t4practical1;

public class SprintEntry extends Entry {   //subclass of Entry
    private int repetitions;
    private int recovery;

    //constructor
    public SprintEntry(String n, int d, int m, int y, int h, int min, int s, float dist, int repetitions, int recovery) {
        super(n, d, m, y, h, min, s, dist);  //from superclass
        this.repetitions = repetitions;     //set to the value given when created
        this.recovery = recovery;           //set to the value given when created
    }

    /**
     *  getRecovery()
     *  return:  int
     *  get the value of variable recovery
     */
    public int getRecovery() {
        return recovery;
    }
    /**
     *  getRepetitions()
     *  return:  int
     *  get the value of variable recovery
     */

    public int getRepetitions() {
        return repetitions;
    }

    /**
     *  getEntry() (overriding method from superclass)
     *  return:  String (result)
     *  get the whole record
     */
    public String getEntry() {
        String result = getName() + " sprinted " + getRepetitions() + "x" + getDistance() + " m in " +getHour()+":"+getMin()+":"+ getSec()+ " with "+ getRecovery() + " minutes recovery on " +
                +getDay() + "/" + getMonth() + "/" + getYear() + "\n";
        return result;
    }
}

