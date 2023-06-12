package br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.ImplClasses;

import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.controller.BaseController;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.Documento;
import br.edu.ifrs.restinga.assinaturadigitalestagioifrsrestingaapi.model.SolicitarEstagio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import java.util.List;

@Service
public class FileImp extends BaseController{

    @Transactional
    public boolean SaveDocBlob(List<MultipartFile> docs, SolicitarEstagio solicitacaoId) {
        try {
            for (MultipartFile doc : docs) {
                Documento documento = new Documento();
                byte[] bytesDocumento = doc.getBytes();
                Blob blobDoc = new SerialBlob(bytesDocumento);
                documento.setNome(doc.getOriginalFilename());
                documento.setDocumento(blobDoc);
                documento.setSolicitarEstagio(solicitacaoId);
                documentoRepository.save(documento);
            }
            return true;
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
