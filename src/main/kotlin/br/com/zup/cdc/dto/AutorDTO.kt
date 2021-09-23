package br.com.zup.cdc.dto

import br.com.zup.cdc.model.Autor
import com.fasterxml.jackson.annotation.JsonProperty

data class AutorDTO(@JsonProperty("Autor")val autor: Autor) {

    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao
    @JsonProperty("Endereco")
    val endereco = autor.endereco
    val id = autor.id

}
