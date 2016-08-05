/**
 * Programming Assignment #1
 *
 * PA1.2:
 *
 * This file describes a set of jobs with positive and integral weights
 * and lengths. It has the format
 * [number_of_jobs]
 * [job_1_weight] [job_1_length]
 * [job_2_weight] [job_2_length]
 *
 * Your task now is to run the greedy algorithm that schedules jobs (optimally)
 * in decreasing order of the ratio (weight/length). In this algorithm, it does
 * not matter how you break ties. You should report the sum of weighted completion
 * times of the resulting schedule --- a positive integer --- in the box below.
 */

package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Assignment2 {

    private Integer amount = null;
    private TreeMap<Double, ArrayList<TaskItem>> tasksPA2 = new TreeMap<>();

    public Assignment2(String filename) {
        try {
            Scanner scan = new Scanner(new File(filename));
            while (scan.hasNextLine()) {
                if (amount == null) {
                    amount = Integer.parseInt(scan.nextLine());
                } else {
                    prepareLine(scan.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void prepareLine(String line) {
        String[] job = line.split(" ");
        int weight = Integer.parseInt(job[0]);
        int length = Integer.parseInt(job[1]);

        TaskItem item = new TaskItem(weight, length);

        ArrayList<TaskItem> taskList = tasksPA2.get(item.getRatio());
        if (taskList == null) {
            taskList = new ArrayList<>();
            tasksPA2.put(item.getRatio(), taskList);
        }
        taskList.add(item);
    }

    public long calculate() {
        long length = 0, time = 0;

        for (Map.Entry<Double, ArrayList<TaskItem>> entry : tasksPA2.descendingMap().entrySet()) {
            ArrayList<TaskItem> value = entry.getValue();
//            System.out.println(entry.getKey() + " => " + value.size());

            Collections.sort(value, new TaskComparator());

            for (TaskItem taskItem : value) {
                time += taskItem.getWeight() * (length + taskItem.getLength());
                length += taskItem.getLength();
            }
        }
        return time;
    }
}
