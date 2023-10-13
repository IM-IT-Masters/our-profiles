package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member extends BaseModel{
    @Column(name = "member")
    private String name;



    @ElementCollection
    private List<String> linkImages = new ArrayList<String>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "project_member",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )


    @JsonIgnoreProperties("members")
    @EqualsAndHashCode.Exclude
    private Set<Project> projects = new HashSet<>();

}
