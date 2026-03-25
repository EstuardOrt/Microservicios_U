package com.davidmejicano.facultades.repository

import com.davidmejicano.facultades.domain.Facultad
import io.micronaut.data.annotation.Join
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface FacultadRepository : CrudRepository<Facultad, Long> {

    @Join(value = "carreras", type = Join.Type.LEFT_FETCH)
    override fun findAll(): List<Facultad>
}