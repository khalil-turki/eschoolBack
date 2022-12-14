package edu.esprit.kaddem.services;

import edu.esprit.kaddem.exception.EntityNotFoundException;
import edu.esprit.kaddem.model.user.Etudiant;
import edu.esprit.kaddem.model.user.Role;
import edu.esprit.kaddem.model.user.Utilisateur;
import edu.esprit.kaddem.repository.UtilisateurRepository;
import edu.esprit.kaddem.utils.PatchUtil;
import edu.esprit.kaddem.utils.PolymorphicUtils;
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

    @Autowired
    private PatchUtil patchUtil;

    @Autowired
    private FlickrPhotoService flickrPhotoService;

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
        var clazz = patch.toJsonValue().asJsonObject().get("role").toString().replace("\"", "");
        var newConcreteClass = PolymorphicUtils.getUserClass(Role.fromValue(clazz));
        var patched = (Utilisateur) patchUtil.unsafeMergePatch(patch, oldUser, newConcreteClass);

        patched.setRole(Role.fromValue(clazz));
        if(oldUser.getRole() != patched.getRole()){
            userRepository.delete(oldUser);
        }
        userRepository.save(patched);
        return patched;
    }

    public Utilisateur updateAvatar(Utilisateur user, byte[] avatar) {
        java.io.InputStream inputStream = new java.io.ByteArrayInputStream(avatar);
        String titre = String.format("%d_%d", System.currentTimeMillis(), user.getId());
        var url = flickrPhotoService.savePhoto(inputStream, titre);
        user.setAvatar(url);
        userRepository.save(user);
        return user;
    }

    public void deleteAvatar(Utilisateur user) {
        user.setAvatar(null);
        userRepository.save(user);
    }
}
