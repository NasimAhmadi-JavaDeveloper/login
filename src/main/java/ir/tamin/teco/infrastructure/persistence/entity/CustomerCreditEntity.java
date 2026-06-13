package ir.tamin.teco.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBL_CUSTOMER_CREDIT")
public class CustomerCreditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerCreditSeq")
    @SequenceGenerator(name = "customerCreditSeq", sequenceName = "CUSTOMER_CREDIT_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Basic
    @Column(name = "PERSON_TYPE", nullable = false, length = 50)
    private String personType;//TODO Enum

    @Basic
    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String firstName;

    @Basic
    @Column(name = "LAST_NAME", nullable = false, length = 100)
    private String lastName;

    @Basic
    @Column(name = "NATIONAL_CODE", nullable = false)
    private String nationalCode;

    @Basic
    @Column(name = "BIRTH_DATE", nullable = false)
    private String birthDate;

    @Basic
    @Column(name = "FATHER_NAME", nullable = false)
    private String fatherName;

    @Basic
    @Column(name = "MANAGER_NATIONAL_CODE", length = 50)
    private String managerNationalCode;

    @Basic
    @Column(name = "PHONE", nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "UNIT_CODE", referencedColumnName = "BRHCODE")
    private BranchEntity unitCode;//TODO?? nullable

    @Basic
    @Column(name = "ACCOUNT_NO", length = 200)
    private String accountNo;

    @Basic
    @Column(name = "DEBT_REASON", nullable = false, length = 250)
    private String debtReason;

    @Basic
    @Column(name = "AMOUNT", nullable = false)
    private Long amount;

    @Basic
    @Column(name = "DISCOUNT_AMOUNT")
    private Long discountAmount;

    @Basic
    @Column(name = "TETA_TRACKING_CODE", length = 250)
    private String tetaTrackingCode;

    @Basic
    @Column(name = "TETA_USERID")
    private Long tetaUserId;

    @Basic
    @Column(name = "STATUS", length = 50)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Basic
    @Column(name = "CREATION_USER", length = 10)
    private String creationUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_DATE")
    private Date modifyDate;

    @Basic
    @Column(name = "MODIFY_USER", length = 10)
    private String modifyUser;

}
