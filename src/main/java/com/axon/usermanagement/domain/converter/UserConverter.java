package com.axon.usermanagement.domain.converter;

import com.commonlib.service.dto.user.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserConverter extends ObjectMapper implements AttributeConverter<UserDTO, String> {


    @Override
    public String convertToDatabaseColumn(UserDTO attribute) {
        try {
            return writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            // Handle serialization error
            throw new IllegalArgumentException("Error converting user object to JSON", e);
        }
    }

    @Override
    public UserDTO convertToEntityAttribute(String dbData) {
        try {
            return readValue(dbData, UserDTO.class);
        } catch (JsonProcessingException e) {
            // Handle deserialization error
            throw new IllegalArgumentException("Error converting JSON to user", e);
        }
    }
}