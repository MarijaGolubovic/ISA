package com.example.demo.model;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

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
    private LocalTime startTime;
    private LocalTime endTime;

    public WorkTime() {}

    public WorkTime(LocalTime start, LocalTime end) {
        this.startTime = start;
        this.endTime = end;
    }

    public WorkTime(Long id, LocalTime start, LocalTime end) {
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

    public LocalTime getStart() {
        return startTime;
    }

    public void setStart(LocalTime start) {
        this.startTime = start;
    }

    public LocalTime getEnd() {
        return endTime;
    }

    public void setEnd(LocalTime end) {
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
