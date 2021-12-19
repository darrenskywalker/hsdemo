package com.example.hsdemo.entities;

import com.example.hsdemo.views.person.ClubView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "club")
@NoArgsConstructor
public class ClubEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "clubs")
    private Set<PersonEntity> members;

    public ClubEntity(final ClubView clubView) {
        this.name = clubView.getName();
    }
}
