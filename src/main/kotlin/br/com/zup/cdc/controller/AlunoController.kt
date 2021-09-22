package br.com.zup.cdc.controller

import br.com.zup.cdc.dto.AlunoForm
import br.com.zup.cdc.model.Aluno
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/alunos")
class AlunoController {

    @Post
    fun cadastraNovoAluno(form: AlunoForm?): MutableHttpResponse<Any> {
        if (form != null) {
            if (!verificaFormNulo(form) || form.nome.length <= 60 || form.nome.isNotEmpty()) {
                if (verificaEmail(form.email)) {
                    val aluno: Aluno = form.toModel()
                    return HttpResponse.created(aluno)
                }
            } else {
                return HttpResponse.badRequest()

            }
        }
        return HttpResponse.badRequest()
    }


}

private fun verificaFormNulo(form: AlunoForm?): Boolean {
    if (form != null) {
        if (form.nome.isEmpty() || form.email.isEmpty() || form.idade < 18) return true
    }
    return false
}

private fun verificaEmail(email: String): Boolean {
    if (email.contains("@email.com.br")) {
        return true
    }
    return false
}
