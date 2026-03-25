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
        // 1. Buscamos al investigador (solo para validar que existe)
        val investigador = repository.findByCui(cui) 
            ?: throw HttpStatusException(HttpStatus.NOT_FOUND, "Investigador no encontrado")

        // 2. Vinculamos el investigador al título
        nuevoTitulo.investigador = investigador

        // 3. Guardamos EL TÍTULO directamente
        // Esto evita el error de "detached entity" porque no tocamos al objeto investigador
        return tituloRepository.save(nuevoTitulo)
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    fun delete(id: Long) {
        repository.deleteById(id)
    }
}