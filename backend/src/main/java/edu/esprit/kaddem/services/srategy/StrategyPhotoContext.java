package edu.esprit.kaddem.services.srategy;

import com.flickr4java.flickr.FlickrException;

import edu.esprit.kaddem.exception.ErrorCodes;
import edu.esprit.kaddem.exception.InvalidOperationException;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.InputStream;

@Service
@CrossOrigin(origins = "http://localhost:4200")

public class StrategyPhotoContext {

    private BeanFactory beanFactory; //pour differencier si il s'agit d'un etudiant ...
    private Strategy strategy;
    @Setter
    private String context;

    @Autowired
    public StrategyPhotoContext(BeanFactory beanFactory) {

        this.beanFactory = beanFactory;
    }

    //Object car on ne peut pas determiner des le debut le type d'object de retour
    public Object savePhoto(String context, Integer id , InputStream photo , String title) throws FlickrException {
        determinContext(context);
        return strategy.savePhoto(id,photo,title);
    }

    private void determinContext(String context){
        final String beanName = context + "Strategy"; // puisque les noms des Service lkol ..Strategy
        switch (context){
            case"etudiant":
                strategy=beanFactory.getBean(beanName, SaveEtudiantPhoto.class);
                break;

            default:throw new InvalidOperationException("context inconnue pour l'enregistrement de cette photo", ErrorCodes.UNKNOWN_CONTEXT);
        }
    }


}
