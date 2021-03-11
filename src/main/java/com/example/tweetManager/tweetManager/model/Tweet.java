package com.example.tweetManager.tweetManager.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table
public @Data
class Tweet {
    @Id
    // TODO - Posible be a generate value
    private Long id;

    @Column
    private String userName;

    @Lob
    @Column( length = 281  )
    private String text;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private Boolean validation;



}
