package ir.tamin.teco.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBL_BRANCH")
public class BranchEntity {

    @Id
    @Column(name = "BRHCODE", nullable = false, length = 2)
    private String brhCode;

    @Basic
    @Column(name = "BRHNAME", nullable = false, length = 100)
    private String brhName;

    @Basic
    @Column(name = "BRHKIND", length = 1)
    private String brhKind;

}
