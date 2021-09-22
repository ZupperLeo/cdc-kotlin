package br.com.zup.cdc.dto

import br.com.zup.cdc.model.Autor
import br.com.zup.cdc.model.Endereco
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Introspected
data class NovoAutorForm(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotNull val livros: Set<LivroForm>,
    @field:NotBlank @field:Size(max = 400) val descricao: String,
    @field:NotBlank val cep: String,
    @field:NotBlank val numero: String
){
    fun toModel(enderecoDTO: EnderecoDTO): Autor {
        val endereco = Endereco(enderecoDTO, numero)

        return Autor(nome, email, descricao, endereco)
    }
}