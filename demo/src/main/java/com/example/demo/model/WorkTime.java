package com.example.demo.model;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table
public class WorkTime {
    @Id
    @SequenceGenerator(
            name = "worktime_sequence",
            sequenceName = "worktime_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "worktime_sequence"
    )
    private Long id;
    private Time startTime;
    private Time endTime;

    public WorkTime() {}

    public WorkTime(Time start, Time end) {
        this.startTime = start;
        this.endTime = end;
    }

    public WorkTime(Long id, Time start, Time end) {
        this.id = id;
        this.startTime = start;
        this.endTime = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getStart() {
        return startTime;
    }

    public void setStart(Time start) {
        this.startTime = start;
    }

    public Time getEnd() {
        return endTime;
    }

    public void setEnd(Time end) {
        this.endTime = end;
    }

    @Override
    public String toString() {
        return "WorkTime{" +
                "id=" + id +
                ", start=" + startTime +
                ", end=" + endTime +
                '}';
    }
}
