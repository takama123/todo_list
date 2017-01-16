package com.example.pc.todo_list.bean;

import java.util.Comparator;

/**
 * Created by PC on 1/15/2017.
 */

public class CompareByDate implements Comparator<Mission> {

    @Override
    public int compare(Mission o1, Mission o2) {
        return o1.convertToCalendar().getTime().compareTo(o2.convertToCalendar().getTime());
    }
}
