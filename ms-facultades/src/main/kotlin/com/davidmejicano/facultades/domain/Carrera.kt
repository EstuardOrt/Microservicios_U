package com.davidmejicano.facultades.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*

@Serdeable
@Entity
@Table(name = "CARRERA")
class Carrera(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 150)
    var nombre: String,

    @Column(name = "duracion_anios", nullable = false)
    var duracionAnios: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facultad_id", nullable = false)
    @JsonIgnoreProperties("nombre", "siglas", "nombreDecano", "telefono", "correo", "carreras")
    var facultad: Facultad? = null
)