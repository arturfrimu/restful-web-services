package com.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean() {
        SomeBean bean = new SomeBean("value1", "value2", "value3");
        FilterProvider filters = getFilterProvider("SomeBeanFilter", new HashSet<>(Arrays.asList("field1", "field2", "field1")));
        MappingJacksonValue mapping = new MappingJacksonValue(bean);
        mapping.setFilters(filters);
        return mapping;
    }

    private FilterProvider getFilterProvider(String filterId, Set<String> filteringFields) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(filteringFields);
        return new SimpleFilterProvider().addFilter(filterId, filter);
    }
}
