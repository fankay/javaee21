package com.kaishengit.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_task")
public class Task implements Serializable {

    @Id
    @GenericGenerator(name = "myuuid",strategy = "uuid")
    @GeneratedValue(generator = "myuuid")
    private String id;
    private String title;
    /*@Version
    private Integer version;*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /*public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }*/
}
