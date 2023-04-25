package pl.coderslab.castme.Photo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/photo")
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @DeleteMapping("/{photoId}/delete")
    public void deletePhoto(@PathVariable Long photoId) {
        Photo photo = photoService.getPhotoById(photoId);
        photoService.deletePhoto(photo);
    }
}
