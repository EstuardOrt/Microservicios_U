package com.davidmejicano.facultades.controller

import com.davidmejicano.facultades.domain.Facultad
import com.davidmejicano.facultades.repository.FacultadRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

@Controller("/facultades")
@ExecuteOn(TaskExecutors.BLOCKING)
class FacultadController(private val repository: FacultadRepository) {

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

    @Delete("/{id}")
    fun eliminar(id: Long): HttpResponse<Unit> {
        repository.deleteById(id)
        return HttpResponse.noContent()
    }
}