package com.example.my_store_spring.utilities;

import com.google.common.collect.Streams;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Service
public final class Mapper {

    private final ModelMapper modelMapper;

    public <E, D> D toDto(@NonNull final E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <E, D> Page<D> mapPageEntityToDto(@NonNull final Page<E> entityPage, Class<D> dtoClass) {
        return entityPage.map(e -> toDto(e, dtoClass));
    }

    public <E, D> List<D> collectToDto(@NonNull final Collection<E> entityCollection, Class<D> dtoClass) {
        return entityCollection.stream()
                .map(e -> modelMapper.map(e, dtoClass))
                .collect(Collectors.toList());
    }

    public <E, D> List<D> iterableToDto(@NonNull final Iterable<E> entityCollection, Class<D> dtoClass) {
        return Streams.stream(entityCollection)
                .map(e -> modelMapper.map(e, dtoClass))
                .collect(Collectors.toList());
    }

    public <E, D> E toEntity(@NonNull final D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    public <E, D> List<E> collectToEntity(@NonNull final Collection<D> dtoCollection, Class<E> entityClass) {
        return dtoCollection.stream()
                .map(d -> modelMapper.map(d, entityClass))
                .collect(Collectors.toList());
    }
}
