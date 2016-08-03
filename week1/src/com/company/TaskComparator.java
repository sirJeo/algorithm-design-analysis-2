package com.company;

import java.util.Comparator;

public class TaskComparator implements Comparator<TaskItem> {
    @Override
    public int compare(TaskItem o1, TaskItem o2) {
        return o2.getWeight().compareTo(o1.getWeight());
    }
}