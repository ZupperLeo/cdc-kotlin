package br.com.zup.cdc.controller

import br.com.zup.cdc.dto.AutorDTO
import br.com.zup.cdc.dto.EnderecoDTO
import br.com.zup.cdc.model.Autor
import br.com.zup.cdc.model.Endereco
import br.com.zup.cdc.repository.AutorRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class AutorControllerTest {

    @field: Inject
    lateinit var autorRepository: AutorRepository

    @field: Inject
    @field: Client("/")
    lateinit var client: HttpClient

    lateinit var autor: Autor

    @BeforeEach
    internal fun setup() {
        val enderecoDTO = EnderecoDTO(
            "01001-000", "Praça da Sé", "lado ímpar",
            "Sé", "São Paulo", "SP", "3550308", "1004", "11", "7107"
        )
        val endereco = Endereco(enderecoDTO, "10")
        autor = Autor("Leonardo", "leonardo@teste.com", "descricao", endereco)

        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown() {
        autorRepository.deleteAll()
    }

    @Test
    internal fun `deve retornar os detalhes de um autor`() {

        val response = client.toBlocking()
            .exchange("/autores/buscar?email=${autor.email}&id=${autor.id}", AutorDTO::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(autor.nome, response.body()!!.nome)
        assertEquals(autor.email, response.body()!!.email)
        assertEquals(autor.descricao, response.body()!!.descricao)
    }
}
