package br.com.zup.cdc.dto

import br.com.zup.cdc.model.Autor

class AutorDTO(autor: Autor) {

    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao
    val id = autor.id

}
