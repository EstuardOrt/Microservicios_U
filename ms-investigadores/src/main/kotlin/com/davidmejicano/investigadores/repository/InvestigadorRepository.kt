package com.davidmejicano.investigadores.repository

import com.davidmejicano.investigadores.domain.Investigador
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface InvestigadorRepository : CrudRepository<Investigador, Long> {
    fun findByCui(cui: String): Investigador?
}