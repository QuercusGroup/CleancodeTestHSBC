package com.test.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/*
Book entity to simulates DB entity.
 */
@Data
@Entity
public class BookEntity {
    @Id
    private UUID uuid;
    private String name;
    private String title;
    private Boolean available;
}
