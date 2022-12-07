package edu.esprit.kaddem.services;

import edu.esprit.kaddem.exception.EntityNotFoundException;
import edu.esprit.kaddem.model.user.Etudiant;
import edu.esprit.kaddem.model.user.Utilisateur;
import edu.esprit.kaddem.repository.UtilisateurRepository;
import edu.esprit.kaddem.utils.PatchUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.json.JsonMergePatch;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class UserService {

    @Value("${app.name}")
    private String appName;
    public static String QR_PREFIX = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";

    @Autowired
    private UtilisateurRepository userRepository;

    @Autowired private PatchUtil patchUtil;


    public List<Utilisateur> getAllUsers() {
        return userRepository.findAll();
    }

    public Utilisateur getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public void update2FAStatus(boolean status, Integer userId) {
        var user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        user.setIsUsing2FA(status);
        userRepository.save(user);
    }

    @SneakyThrows
    public String generateQRUrl(Utilisateur user) {
        return QR_PREFIX + URLEncoder.encode(String.format(
                        "otpauth://totp/%s:%s?secret=%s&issuer=%s",
                        appName, user.getEmail(), user.getSecret(), appName),
                StandardCharsets.UTF_8);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public void add(Utilisateur user) {
        userRepository.save(user);
    }

    @SneakyThrows
    public Utilisateur patch(Integer id, JsonMergePatch patch) {
        var oldUser = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        var patched = patchUtil.mergePatch(patch, (Etudiant)oldUser, Etudiant.class);
        userRepository.save(patched);
        return patched;
    }
}
