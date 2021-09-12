package com.mkraguje.redditclone.model;

import lombok.*;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Getter @Setter
@ToString
@NoArgsConstructor
public class Vote extends Auditable{

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private short direction;

    @NonNull
    @ManyToOne
    private Link link;
}
