package br.com.zup.cdc.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Autor(
    val nome: String?,
    val email: String?,
    var descricao: String?,
    val endereco: Endereco
) {

    @Id
    @GeneratedValue
    var id: Long? = null
    val criadoEm: LocalDateTime = LocalDateTime.now()


    override fun toString(): String {
        return "Autor(nome=$nome, email=$email, descricao=$descricao, id=$id, criadoEm=$criadoEm)"
    }


}
