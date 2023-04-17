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
    private int height;
    private int weight;
    private String hairColor;
    private String hairLength;
    private String eyeColor;
    private String figure;
    private int age;

    public FeatureSet(String gender,
                      int height,
                      int weight,
                      String hairColor,
                      String hairLength,
                      String eyeColor,
                      String figure,
                      int age) {
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.hairColor = hairColor;
        this.hairLength = hairLength;
        this.eyeColor = eyeColor;
        this.figure = figure;
        this.age = age;
    }
}
