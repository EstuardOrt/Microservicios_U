package com.davidmejicano.facultades.domain

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*

@Serdeable
@Entity
@Table(name = "FACULTAD")
class Facultad(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 150)
    var nombre: String,

    @Column(length = 15)
    var siglas: String? = null,

    @Column(name = "nombre_decano", length = 150)
    var nombreDecano: String? = null,

    @Column(length = 20)
    var telefono: String? = null,

    @Column(length = 100)
    var correo: String? = null,

    @OneToMany(mappedBy = "facultad", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var carreras: MutableList<Carrera> = mutableListOf()
)