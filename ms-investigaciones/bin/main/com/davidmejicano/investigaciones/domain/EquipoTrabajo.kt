package com.davidmejicano.investigaciones.domain

import io.micronaut.serde.annotation.Serdeable
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Serdeable
@Entity
@Table(name = "EQUIPO_TRABAJO")
data class EquipoTrabajo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "investigador_asociado_id", nullable = false)
    var investigadorAsociadoId: Int,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "investigacion_id")
    var investigacion: Investigacion? = null
)