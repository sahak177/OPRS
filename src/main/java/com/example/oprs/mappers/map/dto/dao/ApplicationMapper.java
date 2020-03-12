package com.example.oprs.mappers.map.dto.dao;

import com.example.oprs.dao.ApplicationInfo;
import com.example.oprs.dto.ApplicationInfoDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component

public class ApplicationMapper extends ConfigurableMapper {


    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(ApplicationInfo.class, ApplicationInfoDto.class).byDefault().register();
    }

}


