package br.com.zup.model

import br.com.zup.dto.EnderecoDTO
import javax.persistence.Embeddable

@Embeddable
class Endereco(
    enderecoDTO: EnderecoDTO,
    numero: String
) {
//    val rua = enderecoDTO.rua
//    val cidade = enderecoDTO.cidade
//    val estado = enderecoDTO.estado

    val cep = enderecoDTO.cep
    val logradouro = enderecoDTO.logradouro
    val complemento = enderecoDTO.complemento
    val bairro = enderecoDTO.bairro
    val localidade = enderecoDTO.localidade
    val uf = enderecoDTO.uf
    val ibge = enderecoDTO.ibge
    val gia = enderecoDTO.gia
    val ddd = enderecoDTO.ddd
    val siafi  = enderecoDTO.siafi
}
