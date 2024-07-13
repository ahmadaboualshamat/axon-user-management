package com.axon.usermanagement.domain.mapper;

import com.axon.usermanagement.domain.entity.UserAction;
import com.axon.usermanagement.service.dto.UserActionDTO;
import com.commonlib.domain.mapper.EntityMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class UserActionMapper extends ModelMapper implements EntityMapper<UserActionDTO, UserAction> {

    @Override
    public UserAction toEntity(UserActionDTO UserActionDTO) {
        return map(UserActionDTO, UserAction.class);
    }

    @Override
    public UserActionDTO toDto(UserAction UserAction) {
        return map(UserAction, UserActionDTO.class);
    }
}
