package com.example.oprs.mappers.map.dto.dao;

import com.example.oprs.dao.Search;
import com.example.oprs.dto.SearchDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class SearchMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(SearchDto.class, Search.class).byDefault().register();
    }

}


