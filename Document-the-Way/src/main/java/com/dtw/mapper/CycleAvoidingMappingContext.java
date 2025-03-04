package com.dtw.mapper;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

import java.util.IdentityHashMap;
import java.util.Map;

@Component
public class CycleAvoidingMappingContext {
    private final Map<Object, Object> knownInstances = new IdentityHashMap<>();

    @BeforeMapping
    public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType) {
        if (source == null) {
            return null;
        }
        return (T) knownInstances.get(source);
    }

    @BeforeMapping
    public void storeMappedInstance(Object source, @MappingTarget Object target) {
        if (source != null) {
            knownInstances.put(source, target);
        }
    }

    public void clear() {
        knownInstances.clear();
    }
}