package br.com.zup.dto

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class LivroForm(
    @field:NotBlank val titulo: String,
    @field:NotBlank val isbn: String,
    val dataPublicacao: String
)