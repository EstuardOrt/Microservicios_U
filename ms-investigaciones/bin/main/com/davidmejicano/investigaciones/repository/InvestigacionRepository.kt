package com.davidmejicano.investigaciones.repository

import com.davidmejicano.investigaciones.domain.Investigacion
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface InvestigacionRepository : CrudRepository<Investigacion, Long> {
    
    // Búsqueda por coincidencia parcial en el título (sin importar mayúsculas)
    fun findByTituloContainsIgnoreCase(titulo: String): List<Investigacion>

    // Búsqueda por ID del investigador responsable
    fun findByInvestigadorResponsableId(id: Int): List<Investigacion>

    // Búsqueda por ID del investigador si está en el equipo de trabajo
    fun findByEquipoTrabajoInvestigadorAsociadoId(id: Int): List<Investigacion>
}