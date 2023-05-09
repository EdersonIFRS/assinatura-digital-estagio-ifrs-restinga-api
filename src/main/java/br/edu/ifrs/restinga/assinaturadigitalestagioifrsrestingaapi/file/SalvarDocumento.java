package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SalvarDocumento {

    public String salvar(MultipartFile documento, int chamadoId) throws IOException {
        String nomeArquivo = documento.getOriginalFilename();
        //Difinindo diretorio de destino
        Path diretorioDestino = Paths.get("./documentos/chamados/" + chamadoId);


            Files.createDirectories(diretorioDestino);
            // Salva o arquivo no diretório de destino
            byte[] bytesArquivo = documento.getBytes();
            //caminho final do arquivo
            Path arquivoDestino = diretorioDestino.resolve(nomeArquivo);
            Files.write(arquivoDestino, bytesArquivo);

            // Msg de sucesso
            return "Arquivo " + nomeArquivo + " foi salvo com sucesso no diretório " + diretorioDestino.toString();

    }

    public String deletar(int chamadoId){
        Path diretorioDestino = Paths.get("./documentos/chamados/" + chamadoId);
        try{
            List<Path> lista = Files.list(diretorioDestino).toList();
            for(Path item : lista){
                Files.delete(item);
            }
            Files.delete(diretorioDestino);
            return "Diretorio apagado.";
        }catch (IOException e){
            return "Erro ao deletar diretorio: " + e.getMessage();
        }
    }



    //Retorna a lista com o nome dos documentos.
    public List<String> listarDocumentos(String chamadoId){
        Path diretorioDestino = Paths.get("./documentos/chamados/" + chamadoId);
        List<String> nomesDocumentos = new ArrayList<>();
        if(Files.isDirectory(diretorioDestino)){
            try {
                List<Path> list = Files.list(diretorioDestino).toList();
                for(Path item: list){
                    nomesDocumentos.add(item.getFileName().toString());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return nomesDocumentos;
    }

}
