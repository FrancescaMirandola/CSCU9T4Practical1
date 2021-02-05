package com.stir.cscu9t4practical1;

public class SwimEntry extends Entry {  //subclass of Entry
    private String where;

    //constructor
    public SwimEntry(String n, int d, int m, int y, int h, int min, int s, float dist, String where) {
        super(n, d, m, y, h, min, s, dist);  //from superclass
        this.where = where;                  //set to the value given when created
    }

    /**
     *  getWhere()
     *  return:  String
     *  get the value of variable where
     */
    public String getWhere() {
        return where;
    }

    /**
     *  getEntry() (overriding method from superclass)
     *  return:  String (result)
     *  get the whole record
     */
    public String getEntry() {
        String result = getName() + " swam " + getDistance() + " km " + getWhere() + " in "
                + getHour() + ":" + getMin() + ":" + getSec() + " on "
                + getDay() + "/" + getMonth() + "/" + getYear() + "\n";
        return result;
    }
}