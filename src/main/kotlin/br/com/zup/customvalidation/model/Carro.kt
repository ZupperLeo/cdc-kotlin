package br.com.zup.customvalidation.model

import br.com.zup.customvalidation.validator.Placa
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class Carro(
    @field:NotBlank val modelo: String?,
    @field:NotBlank @field:Placa val placa: String?
) {
}