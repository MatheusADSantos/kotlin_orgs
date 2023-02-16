package MatheusADSantos.com.github.orgs.extensions

//Durante a nossa implementação de cadastro de usuário, salvamos o id, nome e senha do usuário. Considerando que estamos lidando com um App de estudos, essa abordagem não é problemática.
//Porém, em casos do mundo real, é extremamente importante notar que não se deve salvar informações sensíveis diretamente no banco de dados, pois há risco delas ficarem expostas - o que é muito grave.
//Em outras palavras, em casos que você precisa salvar informações sensíveis (como senhas ou algum dado sigiloso), o ideal é utilizar técnicas de hashing ou criptografia.
//No caso de senhas, é comum a utilização de hashing de única via, como é caso de MD5, SHA-2 ou até mesmo uma personalização de alguma das soluções (conhecido como salt, pois é uma técnica em que você gera o hash e não faz o processo contrário, ou seja, de hash para o valor original.
//Para saber mais detalhes da estratégia de hash, confira este artigo com exemplos em Java que utiliza a mesma API utilizada no Kotlin, a MessageDigest. Inclusive, neste Gist tem um exemplo de implementação no Kotlin com SHA-256.


import java.security.MessageDigest

fun String.toHash(
    algoritmo: String = "SHA-256"
): String {
    return MessageDigest
        .getInstance(algoritmo)
        .digest(this.toByteArray())
        .fold("") { str, byte ->
            str + "%02x".format(byte)
        }
}