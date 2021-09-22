package br.com.zup.dto

import br.com.zup.model.Autor

class AutorDTO(autor: Autor) {

    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao
    val id = autor.id

}
