package com.davidmejicano.facultades.controller

import com.davidmejicano.facultades.domain.Facultad
import com.davidmejicano.facultades.domain.Carrera
import com.davidmejicano.facultades.repository.FacultadRepository
import com.davidmejicano.facultades.repository.CarreraRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

@Controller("/facultades")
@ExecuteOn(TaskExecutors.BLOCKING)
class FacultadController(private val repository: FacultadRepository, private val carreraRepository: CarreraRepository) {

    @Get("/")
    fun listar(): List<Facultad> = repository.findAll()

    @Post("/")
    fun crear(@Body facultad: Facultad): HttpResponse<Facultad> {
        return HttpResponse.created(repository.save(facultad))
    }

    @Get("/{id}")
    fun obtener(id: Long): HttpResponse<Facultad> {
        return repository.findById(id)
            .map { HttpResponse.ok(it) }
            .orElse(HttpResponse.notFound())
    }

    @Get("/{id}/carreras")
        fun getCarrerasByFacultad(id: Long): HttpResponse<List<Carrera>> {
            // Verificamos la existencia del recurso padre en el SSD
            return if (repository.existsById(id)) {
                val carreras = carreraRepository.findByFacultadId(id)
                HttpResponse.ok(carreras)
            } else {
                // Si la facultad no existe, devolvemos el estado 404
                HttpResponse.notFound()
            }
    }


    @Delete("/{id}")
    fun eliminar(id: Long): HttpResponse<Unit> {
        repository.deleteById(id)
        return HttpResponse.noContent()
    }
}