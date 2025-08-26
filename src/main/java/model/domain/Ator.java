package model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Ator implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAtor;
    @Column(nullable = false, length = 100)
    private String nomeAtor;

    public Ator(String nomeAtor) {
        this.nomeAtor = nomeAtor;
    }
}
