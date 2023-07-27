package com.example.test.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class AbstractEntity {
    protected Long creationTimestamp;
    protected Long updationTimestamp;

    @PrePersist
    protected void populateCreationTimestamp() {
        this.creationTimestamp = System.currentTimeMillis();
    }

    @PreUpdate
    protected void populateUpdatedTimestamp() {
        this.updationTimestamp = System.currentTimeMillis();
    }
}
