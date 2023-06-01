package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table
public class ActivationCode {
    @Id
    @SequenceGenerator(
            name = "activation_code_sequence",
            sequenceName = "activation_code_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "activation_code_sequence"
    )
    private Long id;
    private String code;
    private String email;
    private boolean activated;

    public ActivationCode(){}
    public ActivationCode(String code, String email, boolean activated) {
        this.code = code;
        this.email = email;
        this.activated = activated;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}

