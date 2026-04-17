package com.davidmejicano.investigaciones.domain

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import java.time.LocalDate

@Serdeable
@Entity
@Table(name = "INVESTIGACION")
data class Investigacion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var titulo: String?,

    @Column(name = "fecha_realizacion")
    var fechaRealizacion: LocalDate? = null,

    var duracion: String? = null,

    @Column(name = "facultad_id")
    var facultadId: Int? = null,

    @Column(name = "investigador_responsable_id")
    var investigadorResponsableId: Int? = null,

    @OneToMany(mappedBy = "investigacion", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    var equipoTrabajo: MutableList<EquipoTrabajo> = mutableListOf()
)