package com.davidmejicano.investigadores.domain

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import java.time.LocalDate

@Serdeable
@Entity
@Table(name = "INVESTIGADOR")
data class Investigador(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 100)
    var nombres: String?,

    @Column(nullable = false, length = 100)
    var apellidos: String?,

    var telefono: String? = null,
    var celular: String? = null,
    var correo: String? = null,
    
    @Column(columnDefinition = "TEXT")
    var direccion: String? = null,

    @Column(name = "municipio_nacimiento")
    var municipioNacimiento: String? = null,

    @Column(name = "departamento_nacimiento")
    var departamentoNacimiento: String? = null,

    @Column(name = "fecha_nacimiento")
    var fechaNacimiento: LocalDate? = null,

    @Column(unique = true, length = 13)
    var cui: String? = null,

    var pasaporte: String? = null,

    @Column(name = "area_cientifica")
    var areaCientifica: String? = null,

    @Column(name = "facultad_id")
    var facultadId: Int? = null,

    @OneToMany(mappedBy = "investigador", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    var titulos: MutableList<Titulo> = mutableListOf()
)