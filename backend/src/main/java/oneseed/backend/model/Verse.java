package oneseed.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "verses")
@Getter
@Setter
public class Verse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    private String text;
    private String book;
    private int chapter;
    private String verse;
    private int verseNumber;
    private String version;
    @Column(name = "is_favorite", nullable = true)
    private boolean isFavorite = false;
    @Column(name ="user_id", nullable = true)
    private Long userId;


}