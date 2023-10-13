package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "project")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project extends BaseModel{
    @Column(name = "name")
    private String name;

    @Column(name = "linkGit")
    private String linkGit;

    @Column(name = "linkVideo")
    private String linkVideo;

    @Column(name = "start")
    private LocalDate start;

    @Column(name = "end")
    private LocalDate end;



    @ElementCollection
    private List<String> linkImages = new ArrayList<String>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )


    @JsonIgnoreProperties("projects")
    @EqualsAndHashCode.Exclude
    private Set<Member> members = new HashSet<>();


    public void addMember(Member member) {
        if(member != null) {
            this.members.add(member);
        }
    }

}
