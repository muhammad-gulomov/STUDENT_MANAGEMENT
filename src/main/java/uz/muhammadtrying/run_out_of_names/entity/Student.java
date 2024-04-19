package uz.muhammadtrying.run_out_of_names.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class Student extends BaseEntity {
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String userName;
    private String password;

    @ManyToOne
    private Group group;

    @Builder
    public Student(Integer id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
