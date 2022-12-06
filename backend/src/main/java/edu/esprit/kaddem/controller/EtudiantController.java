package edu.esprit.kaddem.controller;

import edu.esprit.kaddem.config.EmailSenderConfiguration;
import edu.esprit.kaddem.dto.EtudiantDto;
import edu.esprit.kaddem.exception.EntityNotFoundException;
import edu.esprit.kaddem.lib.AbstractCrudController;
import edu.esprit.kaddem.model.Classe;
import edu.esprit.kaddem.model.Ecole;
import edu.esprit.kaddem.model.user.Etudiant;
import edu.esprit.kaddem.services.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@Api
@RestController
@RequestMapping(value = "/etudiants", produces = "application/json")
public class EtudiantController extends AbstractCrudController<Etudiant, EtudiantDto> {

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private final PDFGeneratorService pdfGeneratorService;

    @Autowired
    private EcoleService ecoleService;
    @Autowired
    private ClasseService classeService;

    @Autowired
    private EmailSenderConfiguration emailSender;

    @Autowired
    private EmailServiceImpl emailService;

    public EtudiantController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @PostMapping("/affecttoclasse/{etudiantid}/{classeid}")
    public void assignEtudiantToClasse(@PathVariable("etudiantid") Integer etudiantid, @PathVariable("classeid") Integer classeid) {
        Etudiant e = etudiantService.findById(etudiantid);
        Classe c = classeService.findById(classeid);
        e.setClasse(c);
        etudiantService.create(e);
    }

    @PostMapping("/desafectfromclasse/{etudiantid}/{classeid}")
    public void removeEtudiantFromClasse(@PathVariable("etudiantid") Integer etudiantid, @PathVariable("classeid") Integer classeid) {
        Etudiant e = etudiantService.findById(etudiantid);
        e.setClasse(null);
        etudiantService.create(e);
    }

    @PostMapping("/desafectfromecole/{etudiantid}/{ecoleid}")
    public void removeEtudiantFromEcole(@PathVariable("etudiantid") Integer etudiantid, @PathVariable("ecoleid") Integer ecoleid) {
        Etudiant e = etudiantService.findById(etudiantid);
        e.setEcole(null);
        etudiantService.create(e);
    }

    @PostMapping("/affecttoecole/{etudiantid}/{ecoleid}")
    public void assignEtudiantToEcole(@PathVariable("etudiantid") Integer etudiantid, @PathVariable("ecoleid") Integer ecoleid) {
        Etudiant e = etudiantService.findById(etudiantid);
        Ecole c = ecoleService.findById(ecoleid);
        e.setEcole(c);
        etudiantService.create(e);
    }

    @GetMapping("/listByClass/{classeId}")
    public List<EtudiantDto> findEtudiantsByClasseId(@PathVariable("classeId") Integer classeId) {
        var etudiants = etudiantService.findAllEtudiantsByIdClasse(classeId);
        return etudiants.stream().map(this::toDto).toList();
    }

    @GetMapping("/countByClass/{classeId}")
    public int countEtudiantsByClasseId(@PathVariable("classeId") Integer classeId) {
        return etudiantService.countEtudiantsByIdClasse(classeId);
    }

    @GetMapping("/pdf/generate/{etudiantId}/{classeId}")
    public void generatePDF(HttpServletResponse response ,@PathVariable("classeId") Integer classeId,@PathVariable("etudiantId") Integer etudiantId) throws IOException {
        String nom = etudiantService.findById(etudiantId).getNom();
        String prenom = etudiantService.findById(etudiantId).getPrenom();
        String classe = etudiantService.findById(etudiantId).getClasse().getNomClasse();
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(response,nom,prenom,classe);
    }
    @PostMapping("/sendemail/{subject}/{etudiantId}")
    public void sendEmail(@PathVariable String subject,@PathVariable("etudiantId") Integer etudiantId) throws IOException {
        String msg = "";
        try {
            Etudiant e = etudiantService.findById(etudiantId);
        } catch (Exception e) {
            if (e instanceof EntityNotFoundException)
                throw new EntityNotFoundException("Etudiant with id " + etudiantId + " not found");

        }
        String To = etudiantService.findById(etudiantId).getEmail();
        if (subject.equals("payement") || subject.equals("absence")) {

            switch (subject) {
                case "payement":
                    msg = "Bonjour " + etudiantService.findById(etudiantId).getNom() + " " + etudiantService.findById(etudiantId).getPrenom() + " ,\n" +
                            "Nous vous informons que vous devez payez\n" +
                            "Merci pour votre comprhension.\n" +
                            "Cordialement,\n" +
                            "L'équipe Kaddem";
                    break;
                case "absence":
                    msg = "Bonjour " + etudiantService.findById(etudiantId).getNom() + " " + etudiantService.findById(etudiantId).getPrenom() + " ,\n" +
                            "Nous vous informons que vous avez beacoup d'absences \n" +
                            "Merci pour votre comprhension.\n" +
                            "Cordialement,\n" +
                            "L'équipe Kaddem";

                    break;


            }


            this.emailService.sendSimpleMessage(To, subject, msg);
        } else {
            throw new EntityNotFoundException("subject not found");
        }

    }


}



