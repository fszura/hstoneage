package com.szura.htoneage.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface EntryToDtoMapper<E, D> {

    E mapToEntry(D dto);

    D mapToDTO(E entry);

    default List<E> mapToEntries(List<D> dtos) {
        if (dtos == null) {
            return new ArrayList<>();
        } else {
            return dtos.stream().map(this::mapToEntry).collect(Collectors.toList());
        }
    }

    default List<D> mapToDtos(List<E> entries) {
        if (entries == null) {
            return new ArrayList<>();
        } else {
            return entries.stream().map(this::mapToDTO).collect(Collectors.toList());
        }
    }
}
