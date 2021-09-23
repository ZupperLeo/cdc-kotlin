package br.com.zup.cdc.controller

import br.com.zup.cdc.client.EnderecoClient
import br.com.zup.cdc.dto.AutorDTO
import br.com.zup.cdc.dto.NovoAutorForm
import br.com.zup.cdc.model.Autor
import br.com.zup.cdc.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*

import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autores")
class AutorController(
    val autorRepository: AutorRepository,
    val enderecoClient: EnderecoClient
) {

    @Post
    @Transactional
    fun cadastra(@Body @Valid form: NovoAutorForm): HttpResponse<Any> {

        val enderecoDTO = enderecoClient.consultarCEP(form.cep)

        if(enderecoDTO.body() == null){
            return HttpResponse.badRequest()
        }

        val autor: Autor = form.toModel(enderecoDTO.body()!!)

        autorRepository.save(autor)

        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autor.id)))

        return HttpResponse.created(uri)
//        return HttpResponse.ok(uri)
    }

    @Get("/listar")
    @Transactional
    fun lista(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {

        if (email.isBlank()) {
            val autores = autorRepository.findAll()

            val autorDTO = autores.map { autor -> AutorDTO(autor) }

            return HttpResponse.ok(autorDTO)
        }

        val possivelAutor = autorRepository.buscaPorEmail(email)

        if (possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }

        val autor = possivelAutor.get()
        return HttpResponse.ok(autor)
    }

    @Get("/buscar")
    @Transactional
    fun buscar(
        @QueryValue(defaultValue = "") nome: String, @QueryValue(defaultValue = "") email: String,
        @QueryValue(defaultValue = "") id: Long
    ): HttpResponse<Any> {
//        val possivelAutor = autorRepository.findByNomeAndEmailAndIdOrderByIdDesc(nome, email, id)

        val possivelAutor = autorRepository.findById(id)

        if (possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }

        val autor = possivelAutor.get()

        return HttpResponse.ok(autor)
    }

    @Put("/atualizar/{id}")
    @Transactional
    fun atualiza(@QueryValue id: Long, @Body @Valid descricao: String): HttpResponse<Any> {
        val possivelAutor = autorRepository.findById(id)

        if (possivelAutor.isEmpty) return HttpResponse.notFound()

        val autor = possivelAutor.get()
        autor.descricao = descricao
        autorRepository.update(autor)

        return HttpResponse.created(autor.toString())

    }

    @Delete("/{id}")
    @Transactional
    fun deleta(@PathVariable id: Long): HttpResponse<Any> {
        val possivelAutor = autorRepository.findById(id)

        if (possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }

        autorRepository.deleteById(id)

        return HttpResponse.ok()
    }
}