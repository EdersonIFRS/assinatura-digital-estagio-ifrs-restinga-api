package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.controller;


import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.domain.repository.DocumentoRepository;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.dto.DocumentoDto;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Documento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DocumentoController {
    @Autowired
    DocumentoRepository documentoRepository;
    @PostMapping("/salvarDocumento")
    @ResponseBody
    public ResponseEntity salvarDocumentos(@RequestBody List<MultipartFile> documentos) {
        Documento doc = new Documento();
        for (MultipartFile documento : documentos) {
            try {
                byte[] bytesDocumento = documento.getBytes();
                Blob blobDoc = new SerialBlob(bytesDocumento);
                doc.setNome(documento.getOriginalFilename());
                doc.setDocumento(blobDoc);
                documentoRepository.save(doc);
            } catch (IOException | SQLException e) {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.ok().build();
    }
    @GetMapping("/downloadDocumento")
    public ResponseEntity<Resource> downloadArquivo(@RequestParam long chamadoId) {
        Optional<Documento> doc;
        try {
            doc  = documentoRepository.findById(chamadoId);
            if(!doc.isPresent()){
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            byte[] documentoBytes = doc.get().getDocumento().getBytes(1, (int) doc.get().getDocumento().length());
            InputStream inputStream = new ByteArrayInputStream(documentoBytes);
            Resource resource = new InputStreamResource(inputStream);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (SQLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/deletarDocumento")
    public ResponseEntity<String> deletarDocumento(@RequestParam Long chamadoId){
        Optional<Documento> doc = documentoRepository.findById(chamadoId);
        if(doc.isPresent()){
            documentoRepository.deleteById(chamadoId);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/listarDocumento")
    @ResponseBody
    public ResponseEntity<List<DocumentoDto>> listarDocumentos() {
        List<Documento> documentos = documentoRepository.findAll();
        List<DocumentoDto> documentosDto = new ArrayList<>();

        for (Documento documento : documentos) {
            DocumentoDto documentoDto = new DocumentoDto();
            documentoDto.setId(documento.getId());
            documentoDto.setNome(documento.getNome());
            documentosDto.add(documentoDto);
        }

        if (!documentosDto.isEmpty()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(documentosDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listarDocumento/{solicitarEstagioId}")
    @ResponseBody
    public ResponseEntity<List<DocumentoDto>> listarDocumentosPorSolicitarEstagioId(@PathVariable("solicitarEstagioId") Long solicitarEstagioId) {
        List<Documento> documentos = documentoRepository.findBySolicitarEstagioId(solicitarEstagioId);
        List<DocumentoDto> documentosDto = new ArrayList<>();

        for (Documento documento : documentos) {
            DocumentoDto documentoDto = new DocumentoDto();
            documentoDto.setId(documento.getId());
            documentoDto.setNome(documento.getNome());
            documentosDto.add(documentoDto);
        }

        if (!documentosDto.isEmpty()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(documentosDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
