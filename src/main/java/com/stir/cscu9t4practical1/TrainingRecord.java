// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;

import java.security.InvalidParameterException;
import java.util.*;

public class TrainingRecord {
    private List<Entry> tr;

    public TrainingRecord() {
        tr = new ArrayList<Entry>();
    } //constructor

    // adds a record to the list
    public void addEntry(Entry e) {
        if (e.getName().isBlank()||e.getName().matches("-?\\d+(\\.\\d+)?")){           //if name is not given or it is a number

           throw new InvalidParameterException();                                            //throws an exception
        }
        if (tr.size() == 0) {           //if it's the first record, adds without checks
            tr.add(e);
        } else if (tr.size() != 0) {             //if it's not the first record
            ListIterator<Entry> iter = tr.listIterator();   //go through the records
            while (iter.hasNext()) {
                Entry current = iter.next();
                if (current.getName() == (e.getName()) && current.getDay() == e.getDay() && current.getMonth() == e.getMonth() && current.getYear() == e.getYear()) {
                    //if there's a record with the same name and date
                    System.out.println("Record already present.");
                    //don't add, exit the loop
                    break;
                } else {
                    tr.add(e); //if there's no such record, add the record and exit the loop
                    break;
                }
            }
        }
    }

    // looks up the entry of a given day and month
    public String lookupEntry (int d, int m, int y) {
        ListIterator<Entry> iter = tr.listIterator();
        String result = "No entries found";
        while (iter.hasNext()) {
            Entry current = iter.next();
            if (current.getDay()==d && current.getMonth()==m && current.getYear()==y)
                result = current.getEntry();
        }
        return result;
    } // lookupEntry

    /**
     *  findAllByDate (int d, int m, int y)
     *  return:  message
     *  looks up all entries of a given day, month and year
     */
    public String findAllByDate(int d, int m, int y) {
        ListIterator<Entry> iter = tr.listIterator();           //creation of the iterator
        String result = "";                                     //variable which will show the results in the text area
        while (iter.hasNext()) {                                //while the training list has entries
            Entry current = iter.next();                        //variable for the entry pointed by the iterator
            if (current.getDay() == d && current.getMonth() == m && current.getYear() == y) {      //if it has the same day, month and year given in the input area
                result = result.concat(current.getEntry());    //add (or concatenate) the record to variable result
            }
        }
        return result; //display result string
    }

    /**
     *  deleteEntry(String n,int d, int m, int y )
     *  return:  message
     *  deletes a record given a name, a day, a month and year
     */

    public void deleteEntry(String n,int d, int m, int y ) {
        ListIterator<Entry> iter = tr.listIterator();           //creation of the iterator
        while (iter.hasNext()) {                                //while the training list has entries
            Entry current = iter.next();                        //variable for the entry pointed by the iterator
            if (current.getName().equalsIgnoreCase(n) && current.getDay() == d && current.getMonth() == m && current.getYear() == y) {
                //if it has the same name, day, month and year given in the input area
                iter.remove();      //remove current entry
                break;              //exit the loop
            }
        }
    }

    /**
     *  getNumberOfEntries(
     *  return:  int
     *  Counts the number of entries
     */
    public int getNumberOfEntries(){
        return tr.size();
    }

    /**
     *  clearAllEntries()
     *  return /
     *  Clears all entries
     */
    public void clearAllEntries(){
        tr.clear();
    }

}