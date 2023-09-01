package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table
public class RefreshConfig {

    @Id
    @SequenceGenerator(
            name = "refresh_config_sequence",
            sequenceName = "refresh_config_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "refresh_config_sequence"
    )
    private Long id;
    private Long userId;
    private int refreshPeriod;

    public Long getUserId() {
        return userId;
    }

    public int getRefreshPeriod() {
        return refreshPeriod;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRefreshPeriod(int refreshPeriod) {
        this.refreshPeriod = refreshPeriod;
    }

    public RefreshConfig(Long userId, int refreshPeriod) {
        this.userId = userId;
        this.refreshPeriod = refreshPeriod;
    }

    public RefreshConfig() {

    }
}
