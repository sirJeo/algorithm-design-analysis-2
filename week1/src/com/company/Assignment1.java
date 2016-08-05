/**
 * Programming Assignment #1
 *
 * PA1.1:
 *
 * This file describes a set of jobs with positive and integral weights
 * and lengths. It has the format
 * [number_of_jobs]
 * [job_1_weight] [job_1_length]
 * [job_2_weight] [job_2_length]
 *
 * Your task in this problem is to run the greedy algorithm that schedules
 * jobs in decreasing order of the difference (weight - length). Recall from
 * lecture that this algorithm is not always optimal. IMPORTANT: if two jobs
 * have equal difference (weight - length), you should schedule the job with
 * higher weight first. Beware: if you break ties in a different way, you are
 * likely to get the wrong answer. You should report the sum of weighted
 * completion times of the resulting schedule --- a positive integer --- in
 * the box below.
 */

package com.company;

import java.io.*;
import java.util.*;

public class Assignment1 {
    private Integer amount = null;
    private TreeMap<Integer, ArrayList<TaskItem>> tasksPA1 = new TreeMap<>();

    public Assignment1(String filename) {
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

        ArrayList<TaskItem> taskList = tasksPA1.get(item.getDiff());
        if (taskList == null) {
            taskList = new ArrayList<>();
            tasksPA1.put(item.getDiff(), taskList);
        }
        taskList.add(item);
    }


    public long calculate() {
        long length = 0, time = 0;

        for (Map.Entry<Integer, ArrayList<TaskItem>> entry : tasksPA1.descendingMap().entrySet()) {
            ArrayList<TaskItem> value = entry.getValue();

            Collections.sort(value, new TaskComparator());

            for (TaskItem taskItem : value) {
                time += taskItem.getWeight() * (length + taskItem.getLength());
                length += taskItem.getLength();
            }
        }
        return time;
    }
}