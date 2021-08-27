package com.mkraguje.redditclone.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class Comment extends Auditable{

    @Id
    @GeneratedValue
    private Long id;
    private String body;

    @ManyToOne
    private Link link;
}
