package com.y4d3.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by semo on 02.01.17.
 */
@MappedSuperclass
public class ADomainObject implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Version
    private Integer version;

    private Date created;
    private Date lastUpdate;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreated() {
        return created;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        lastUpdate = new Date();
        if (created == null) {
            created = new Date();
        }
    }
}
