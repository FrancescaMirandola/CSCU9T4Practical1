package com.stir.cscu9t4practical1;

public class CycleEntry extends Entry {  //subclass of Entry
    private String terrain;
    private String tempo;

    //constructor
    public CycleEntry(String n, int d, int m, int y, int h, int min, int s, float dist, String terrain, String tempo) {
        super(n, d, m, y, h, min, s, dist);
        this.terrain = terrain;
        this.tempo = tempo;
    }

    /**
     *  getTempo()
     *  return:  String
     *  get the value of variable tempo
     */
    public String getTempo() {

        return tempo;
    }
    /**
     *  getTerrain()
     *  return:  String
     *  get the value of variable terrain
     */
    public String getTerrain() {

        return terrain;
    }

    /**
     *  getEntry() (overriding method from superclass)
     *  return:  String (result)
     *  get the whole record
     */
    public String getEntry() {
        String result = getName() + " cycled " + getDistance() + " km in "
                + getHour() + ":" + getMin() + ":" + getSec() + " on "
                + getDay() + "/" + getMonth() + "/" + getYear() + " on " + terrain + " at " + tempo + " tempo"+ "\n";
        return result;

    }
}

