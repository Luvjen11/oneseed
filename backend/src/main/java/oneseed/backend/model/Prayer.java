package oneseed.backend.model;

import jakarta.persistence.Column;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prayers")
@Getter
@Setter
public class Prayer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime dateCreated;
    private LocalDateTime dateAnswered;
    @Column(name = "is_answered", nullable = true)
    private boolean isAnswered = false;
    @Column(name ="user_id", nullable = true)
    private Long userId;
}
