package pl.coderslab.castme.FeatureSet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "feature_set")
@Getter
@Setter
@NoArgsConstructor
public class FeatureSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gender;
    private String height;
    private String hairColor;
    private String hairLength;
    private String eyeColor;
    private String figure;
    private int ageFrom;
    private int ageTo;

    public FeatureSet(String gender,
                      String height,
                      String hairColor,
                      String hairLength,
                      String eyeColor,
                      String figure,
                      int ageFrom,
                      int ageTo) {
        this.gender = gender;
        this.height = height;
        this.hairColor = hairColor;
        this.hairLength = hairLength;
        this.eyeColor = eyeColor;
        this.figure = figure;
        this.ageFrom = ageFrom;
        this.ageTo = ageTo;
    }
}
