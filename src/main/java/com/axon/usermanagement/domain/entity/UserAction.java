package com.axon.usermanagement.domain.entity;

import com.axon.usermanagement.converter.LocalDateTimeConverter;
import com.commonlib.domain.enumration.ActionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;


@Data
@Entity
@Table(name = "act_sys_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(value = NON_EMPTY)
public class UserAction /*extends AbstractEntity*/ {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actUserSeqGen")
    @SequenceGenerator(name = "actUserSeqGen", sequenceName = "actusr_seq", allocationSize = 1)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ActionType userActionType;


    @Column(name = "user_info")
    private String userInfo;

    @Column(name = "technical_remarks")
    private String technicalRemarks;

//    @CreatedDate
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdDate;

    @Column(name = "created_by")
    private String createdBy;

}
