package br.com.zup.repository

import br.com.zup.model.Autor
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface AutorRepository : JpaRepository<Autor, Long> {

    fun findByEmail(email: String): Optional<Autor>

    @Query("SELECT a FROM Autor a WHERE a.email = :value")
    fun buscaPorEmail(value: String): Optional<Autor>

    fun findByNomeAndEmailAndIdOrderByIdDesc(nome: String, email: String, id: Long): Optional<Autor>

    @Query(value = "SELECT * FROM autor  ORDER BY id DESC", nativeQuery = true)
    fun findAllOrderByIdDesc(): List<Autor>

    @Query(value = "select * from cartao inner join carteira on cartao.id = carteira.cartao_id", nativeQuery = true)
    fun findAllOrderByIdDesc(ast: String): List<Autor>
}