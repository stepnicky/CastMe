package pl.coderslab.castme.Photo;

import org.springframework.stereotype.Service;
import pl.coderslab.castme.Actor.Actor;

import java.util.List;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public void savePhoto(Photo photo) {
        photoRepository.save(photo);
    }
    public List<Photo> getPhotosByActor(Actor actor) {
        return photoRepository.getByActor(actor);
    }
    public Photo getPhotoById(Long photoId) {
        return photoRepository.findById(photoId).orElseThrow(RuntimeException::new);
    }
    public void deletePhoto(Photo photo) {
        photoRepository.delete(photo);
    }
}
