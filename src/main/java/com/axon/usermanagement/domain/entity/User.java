package com.axon.usermanagement.domain.entity;

import com.axon.usermanagement.converter.LocalDateTimeConverter;
import com.axon.usermanagement.domain.converter.StringSetConverter;
import com.commonlib.domain.enumration.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;


@Data
@Entity
@Table(name = "sys_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@RequiredArgsConstructor
//@JsonInclude(value = NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User /*extends AbstractEntity*/ {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeqGen")
    @SequenceGenerator(name = "userSeqGen", sequenceName = "user_seq", allocationSize = 1)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, name = "username")
    private String username;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "roles", nullable = false)
    @Convert(converter = StringSetConverter.class)
    private Set<String> roles;

//    @ManyToOne
//    @JoinColumn(name="user_type_id", nullable=false)
//    private UserType userType;

    @Column(name = "created_date", nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_date")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime modifiedDate;

    @Column(name = "modified_by")
    private String modifiedBy;

}
