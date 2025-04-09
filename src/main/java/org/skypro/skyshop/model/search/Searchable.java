package org.skypro.skyshop.model.search;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;


public interface Searchable {


    String searchTerm();

    String searchTipContent();

    UUID getId();

    @JsonIgnore
    default String getStringRepresentation() {
        return "Имя объекта: " + searchTerm() + " ;  " + " тип объекта: " + searchTipContent();
    }

}