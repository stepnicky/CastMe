package pl.coderslab.castme.casting;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CastingScheduler {
    private final CastingService castingService;

    public CastingScheduler(CastingService castingService) {
        this.castingService = castingService;
    }

    @Scheduled(fixedRate = 60000)
    public void checkCastingDeadlines() {
        LocalDateTime now = LocalDateTime.now();
        List<Casting> castings = castingService.getAllActiveCastings();
        for (Casting casting : castings) {
            if (casting.getDeadline().isBefore(now.toLocalDate())) {
                casting.setActive(false);
                castingService.updateCasting(casting);
            }
        }
    }
}