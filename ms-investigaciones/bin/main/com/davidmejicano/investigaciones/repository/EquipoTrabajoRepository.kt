package com.davidmejicano.investigaciones.repository

import com.davidmejicano.investigaciones.domain.EquipoTrabajo
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface EquipoTrabajoRepository : CrudRepository<EquipoTrabajo, Long>