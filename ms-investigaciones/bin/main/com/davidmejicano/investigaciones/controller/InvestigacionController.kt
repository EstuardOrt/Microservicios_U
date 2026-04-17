package com.davidmejicano.investigaciones.controller

import com.davidmejicano.investigaciones.domain.Investigacion
import com.davidmejicano.investigaciones.domain.EquipoTrabajo
import com.davidmejicano.investigaciones.repository.InvestigacionRepository
import com.davidmejicano.investigaciones.repository.EquipoTrabajoRepository
import io.micronaut.http.annotation.*
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException

@Controller("/investigaciones")
class InvestigacionController(
    private val repository: InvestigacionRepository,
    private val equipoRepository: EquipoTrabajoRepository
) {

   
    @Get("/")
    fun search(
        @QueryValue titulo: String?,
        @QueryValue investigadorId: Int?
    ): List<Investigacion> {
        return when {
            !titulo.isNullOrBlank() -> 
                repository.findByTituloContainsIgnoreCase(titulo)
            
            investigadorId != null -> {
                // Buscamos donde sea responsable O donde sea miembro del equipo
                val comoResponsable = repository.findByInvestigadorResponsableId(investigadorId)
                val comoAsociado = repository.findByEquipoTrabajoInvestigadorAsociadoId(investigadorId)
                
                // Unimos ambas listas y quitamos duplicados
                (comoResponsable + comoAsociado).distinctBy { it.id }
            }
            
            else -> repository.findAll().toList()
        }
    }

    @Get("/{id}")
    fun findById(id: Long): Investigacion? = repository.findById(id).orElse(null)

    @Put("/{id}")
    fun update(id: Long, @Body actualizado: Investigacion): Investigacion {
        
        val existente = repository.findById(id)
            .orElseThrow { HttpStatusException(HttpStatus.NOT_FOUND, "Investigación no encontrada") }

        val modificado = existente.copy(
            titulo = actualizado.titulo ?: existente.titulo,
            fechaRealizacion = actualizado.fechaRealizacion ?: existente.fechaRealizacion,
            duracion = actualizado.duracion ?: existente.duracion,
            facultadId = actualizado.facultadId ?: existente.facultadId,
            investigadorResponsableId = actualizado.investigadorResponsableId ?: existente.investigadorResponsableId
        )
        return repository.update(modificado)
    }

    @Post("/")
    @Status(HttpStatus.CREATED)
    fun create(@Body investigacion: Investigacion): Investigacion {
        // Aseguramos la relación bidireccional antes de guardar
        investigacion.equipoTrabajo.forEach { it.investigacion = investigacion }
        return repository.save(investigacion)
    }

    @Post("/{id}/equipo")
    @Status(HttpStatus.CREATED)
    fun addMiembroEquipo(id: Long, @Body nuevoMiembro: EquipoTrabajo): EquipoTrabajo {
        val investigacion = repository.findById(id)
            .orElseThrow { HttpStatusException(HttpStatus.NOT_FOUND, "Investigación no encontrada") }

        nuevoMiembro.investigacion = investigacion
        return equipoRepository.save(nuevoMiembro)
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    fun delete(id: Long) {
        if (!repository.existsById(id)) {
            throw HttpStatusException(HttpStatus.NOT_FOUND, "Investigación no encontrada")
        }
        repository.deleteById(id)
    }
}