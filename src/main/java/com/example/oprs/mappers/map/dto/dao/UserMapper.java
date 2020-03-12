package com.example.oprs.mappers.map.dto.dao;

import com.example.oprs.dao.User;
import com.example.oprs.dto.UserDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingException;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        String fieldName;
        try {
            fieldName = UserDto.class.getDeclaredField("password").getName();
        } catch (NoSuchFieldException ignored) {
            throw new MappingException("Ignored field dose not exist.");
        }

        factory.classMap(UserDto.class, User.class).fieldAToB(fieldName, fieldName).byDefault().register();
    }

}