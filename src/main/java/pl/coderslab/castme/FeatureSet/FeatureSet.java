package pl.coderslab.castme.FeatureSet;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "feature_set")
@Getter
@Setter
public class FeatureSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gender;
    private int height;
    private int weight;
    private String hairColor;
    private String hairLength;
    private String eyeColor;
    private String figure;
    private LocalDate dateOfBirth;
}
