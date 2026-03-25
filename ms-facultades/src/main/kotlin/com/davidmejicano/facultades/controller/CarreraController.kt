package com.davidmejicano.facultades.controller

import com.davidmejicano.facultades.domain.Carrera
import com.davidmejicano.facultades.repository.CarreraRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

@Controller("/carreras")
@ExecuteOn(TaskExecutors.BLOCKING)
class CarreraController(private val carreraRepository: CarreraRepository) {

    @Get("/")
    fun listAll(): Iterable<Carrera> = carreraRepository.findAll()

    @Post("/")
    fun save(@Body carrera: Carrera): HttpResponse<Carrera> {
        val nuevaCarrera = carreraRepository.save(carrera)
        return HttpResponse.created(nuevaCarrera)
    }

    @Get("/{id}")
    fun findById(id: Long): HttpResponse<Carrera> {
        return carreraRepository.findById(id)
            .map { HttpResponse.ok(it) }
            .orElse(HttpResponse.notFound())
    }
}