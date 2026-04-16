package com.davidmejicano.facultades.repository

import com.davidmejicano.facultades.domain.Carrera
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface CarreraRepository : CrudRepository<Carrera, Long>{
    fun findByFacultadId(facultadId: Long): List<Carrera>
}