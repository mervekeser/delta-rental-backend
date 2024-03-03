package com.delta.rental.deltarental.entities.concretes;

import com.delta.rental.deltarental.entities.abstracts.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "colors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Color extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "color")
    @JsonIgnore
    private List<Car> cars;
}
