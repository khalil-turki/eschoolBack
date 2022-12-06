package edu.esprit.kaddem.services;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class PDFGeneratorService {
    public void export(HttpServletResponse response, String nom, String prenom , String classe) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        Paragraph paragraph = new Paragraph("ATTESTATION DE PRESENCE", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
          fontParagraph.setSize(12);
        Paragraph paragraph2 = new Paragraph("\n \n \n Je soussigné(e) mr KADDEM  directeur general,\n \n" +

                " certifie que  Madame/Monsieur" + " " + nom + " " + prenom + " " +
                " fait partie actuellement au sein de notre ecole esprit \n" +
                " et qu'il est inscri  à la classe  " + classe + "\n" +
                "\n \n \n \n Signature"


                , fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(paragraph);
        document.add(paragraph2);
        document.close();
    }
}
