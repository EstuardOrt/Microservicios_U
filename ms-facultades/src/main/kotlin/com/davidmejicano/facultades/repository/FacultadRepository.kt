package com.davidmejicano.facultades.repository

import com.davidmejicano.facultades.domain.Facultad
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface FacultadRepository : CrudRepository<Facultad, Long> {

}