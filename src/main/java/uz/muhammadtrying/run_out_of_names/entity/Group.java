package uz.muhammadtrying.run_out_of_names.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Group extends BaseEntity {
    private String name;

    @Builder
    public Group(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
