package com.axon.usermanagement.action;

import com.axon.usermanagement.domain.entity.UserAction;
import com.axon.usermanagement.domain.repository.UserActionRepository;
import com.commonlib.domain.enumration.ActionType;
import com.commonlib.service.dto.user.UserDTO;
import com.commonlib.util.action.AuditingEntityListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

@Slf4j
@Component
public class AuditingUserListener implements AuditingEntityListener<UserDTO> {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserActionRepository userActionRepository;

    public void log(UserDTO userDTO, ActionType actionType) {
        log(userDTO, actionType, null);
    }

    public void log(UserDTO userDTO, ActionType actionType, Exception e) {

        try {
            UserAction userAction =
                    UserAction.builder()
                            .userId(userDTO.getId())
                            .userActionType(actionType)
                            .userInfo(objectMapper.writeValueAsString(userDTO))
                            .createdBy(userDTO.getCreatedBy())
                            .createdDate(userDTO.getCreatedDate())
                            .technicalRemarks(Objects.nonNull(e) ? getStackTraceAsString(e) : null)
                            .build();
            userActionRepository.save(userAction);
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
        }

    }
    public static String getStackTraceAsString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
