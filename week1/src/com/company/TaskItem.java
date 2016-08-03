package com.company;

/**
 * Created by user on 03.08.16.
 */
public class TaskItem {
    private Integer weight;
    private Integer length;
    private Integer diff;
    private Double ratio;

    public Integer getWeight() {
        return weight;
    }

    public Integer getLength() {
        return length;
    }

    public Integer getDiff() {
        return diff;
    }

    public double getRatio() {
        return ratio;
    }

    public TaskItem(Integer w, Integer l) {
        weight = w;
        length = l;
        diff = w - l;
        ratio = (double)w / (double)l;
    }
}