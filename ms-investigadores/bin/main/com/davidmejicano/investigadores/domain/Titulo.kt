package com.davidmejicano.investigadores.domain

import io.micronaut.serde.annotation.Serdeable
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Serdeable
@Entity
@Table(name = "TITULO")
data class Titulo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "nivel_academico")
    var nivelAcademico: String? = null,

    @Column(name = "nombre_titulo")
    var nombreTitulo: String? = null,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "investigador_id")
    var investigador: Investigador? = null 
)