package com.davidmejicano.investigadores.controller

import com.davidmejicano.investigadores.domain.Investigador
import com.davidmejicano.investigadores.domain.Titulo
import com.davidmejicano.investigadores.repository.InvestigadorRepository
import com.davidmejicano.investigadores.repository.TituloRepository
import io.micronaut.http.annotation.*
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException

@Controller("/investigadores")
class InvestigadorController(private val repository: InvestigadorRepository, private val tituloRepository: TituloRepository) {

    @Get("/")
    fun listAll(): List<Investigador> = repository.findAll().toList()

    @Get("/cui/{cui}")
    fun findByCui(cui: String): Investigador? {
        return repository.findByCui(cui)
    }

    @Put("/{id}")
    fun update(id: Long, @Body actualizado: Investigador): Investigador {
        val existente = repository.findById(id)
            .orElseThrow { HttpStatusException(HttpStatus.NOT_FOUND, "Investigador no encontrado") }

        val modificado = existente.copy(
            nombres = actualizado.nombres ?: existente.nombres,
            apellidos = actualizado.apellidos ?: existente.apellidos,
            correo = actualizado.correo ?: existente.correo,
            facultadId = actualizado.facultadId ?: existente.facultadId,
            areaCientifica = actualizado.areaCientifica ?: existente.areaCientifica,
            telefono = actualizado.telefono ?: existente.telefono,
            celular = actualizado.celular ?: existente.celular,
            direccion = actualizado.direccion ?: existente.direccion

        )

        return repository.update(modificado)
    }

    @Get("/{id}")
    fun findById(id: Long): Investigador? = repository.findById(id).orElse(null)

    @Post("/")
    @Status(HttpStatus.CREATED)
    fun create(@Body investigador: Investigador): Investigador {
        
        investigador.titulos.forEach { it.investigador = investigador }
        return repository.save(investigador)
    }

    @Post("/cui/{cui}/titulos")
    @Status(HttpStatus.CREATED)
    fun addTitulo(cui: String, @Body nuevoTitulo: Titulo): Titulo {

        val investigador = repository.findByCui(cui) 
            ?: throw HttpStatusException(HttpStatus.NOT_FOUND, "Investigador no encontrado")

        nuevoTitulo.investigador = investigador

        return tituloRepository.save(nuevoTitulo)
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    fun delete(id: Long) {
        repository.deleteById(id)
    }
}