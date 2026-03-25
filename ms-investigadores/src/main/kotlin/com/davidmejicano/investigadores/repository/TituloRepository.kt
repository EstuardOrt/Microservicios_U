package com.davidmejicano.investigadores.repository

import com.davidmejicano.investigadores.domain.Titulo
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface TituloRepository : CrudRepository<Titulo, Long>