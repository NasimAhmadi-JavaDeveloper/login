package ir.tamin.teco.model.entity;

import ir.tamin.teco.model.enums.ConfigKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "CONFIG")
public class ConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "config_seq")
    @SequenceGenerator(name = "config_seq", sequenceName = "SEQ_CONFIG", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "KEY", nullable = false, unique = true)
    private ConfigKey key;

    @Lob
    @Column(name = "VALUE")
    private String value;

}