package in.gov.wildlifemisportal.credential.userDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.forest.wildlife.credential.role.Role_m;
import com.forest.wildlife.exceptionHandling.atLeastOneFieldNotNullValidator.AtLeastOneFieldNotNull;
import com.forest.wildlife.lgdEntities.entities.District;
import com.forest.wildlife.lgdEntities.entities.Division;
import com.forest.wildlife.lgdEntities.entities.Range;
import com.forest.wildlife.lgdEntities.entities.State;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AtLeastOneFieldNotNull(fieldNames = {"state", "division", "district", "range","roleId"})
@Table(name = "user_detail")
@Builder
public class UserDetail_t {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", allocationSize = 1)
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;

    @Column(name = "password", nullable = false)
    private String password;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "role_m_role_id", nullable = false)
//    @JsonIgnoreProperties(value={"user_detail", "hibernateLazyInitializer"})
//    private Role_m roleM;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

//    @Column(name = "account_non_locked", nullable = false)
//    private Boolean accountNonLocked;
//
//    @Column(name = "failed_attempt")
//    private Integer failedAttempt;
//
//    @Column(name = "lock_time")
//    private Date lockTime;

    @ManyToMany()
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonManagedReference
    @ToString.Exclude
//    @JsonIgnore
    private Set<Role_m> roleId=new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_code", nullable=true)
    @ToString.Exclude
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "division_id", nullable=true)
    @ToString.Exclude
    private Division division;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_code", nullable=true)
    @ToString.Exclude
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "range_id", nullable=true)
    @ToString.Exclude
    private Range range;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserDetail_t that = (UserDetail_t) o;
        return getUserId() != null && Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}