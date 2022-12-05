package edu.esprit.kaddem.services.srategy;
import edu.esprit.kaddem.exception.ErrorCodes;
import edu.esprit.kaddem.exception.InvalidOperationException;
import edu.esprit.kaddem.model.user.Etudiant;
import edu.esprit.kaddem.services.EtudiantService;
import edu.esprit.kaddem.services.FlickrPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.InputStream;
@CrossOrigin(origins = "http://localhost:4200")

@Service("etudiantStrategy")
@Slf4j
public class SaveEtudiantPhoto implements Strategy<Etudiant> {

    private FlickrPhotoService flickrPhotoService;
    private EtudiantService etudiantService ;

    @Autowired
    public SaveEtudiantPhoto(FlickrPhotoService flickrPhotoService, EtudiantService etudiantService) {
        this.flickrPhotoService = flickrPhotoService;
        this.etudiantService = etudiantService;
    }

    @CrossOrigin(origins = "http://localhost:4200")

    @Override
    public Etudiant savePhoto(Integer id , InputStream photo, String titre) {
        Etudiant etudiantDto = etudiantService.findById(id);
        String urlPhoto = flickrPhotoService.savePhoto(photo,titre);
        System.out.println("lurl est "+urlPhoto);

        if(!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("Erreur de l'enregistrement de la photo de l'etudiant", ErrorCodes.UPDATE_PHOTO_EXCEPTION);

        }
        etudiantDto.setPhoto(urlPhoto);

        return etudiantService.create(etudiantDto);
    }
}
