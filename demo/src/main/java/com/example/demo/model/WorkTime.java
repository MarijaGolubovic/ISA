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
    private Time start;
    private Time end;

    public WorkTime() {}

    public WorkTime(Time start, Time end) {
        this.start = start;
        this.end = end;
    }

    public WorkTime(Long id, Time start, Time end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "WorkTime{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
