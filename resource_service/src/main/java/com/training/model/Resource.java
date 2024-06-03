package com.training.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "RESOURCE")
@NoArgsConstructor
public class Resource {

    @Id
    @GeneratedValue(generator = "uuid")
    private Integer id;

    private String fileName;

    private String type;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] data;

    public Resource(String name, String type, byte[] data) {
        this.fileName = name;
        this.type = type;
        this.data = data;
    }

}
