package org.jeansamuel.swagg.model;

import lombok.Data;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;




@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

   /*// @CreatedDate
    @Column(name = "creationDate", nullable = false)
    private Instant creationDate;

   //@LastModifiedDate
    @Column(name = "lastModifiedDate", nullable = false)
    private Instant lastModifiedDate;

    @PrePersist
    void presist(){
        creationDate = Instant.now();
    }
   @PreUpdate
    void predate(){
        lastModifiedDate = Instant.now();
    }*/
}
