package com.axon.usermanagement.service.dto;

import com.commonlib.service.dto.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserActionDTO extends AbstractDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("userid")
    private Long userId;

    private String userInfo;

    private String technicalRemarks;
}
