package br.com.zup.dto

import br.com.zup.model.Aluno
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Size

@Introspected
data class AlunoForm(
    @field:Size(max = 60) val nome: String,
    val email: String,
    val idade: Int
) {
    fun toModel(): Aluno {
        return Aluno(nome, email, idade)
    }
}

