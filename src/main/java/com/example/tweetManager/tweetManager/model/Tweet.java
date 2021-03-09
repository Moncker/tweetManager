package com.example.tweetManager.tweetManager.model;

import lombok.Data;

import javax.persistence.*;


@Entity
public @Data
class Tweet {
    @Id
    private Integer id;

    @Column
    private String userName;

    @Column
    private String text;

    @Column
    private String localisation;

    @Column
    private Boolean validation;



}
