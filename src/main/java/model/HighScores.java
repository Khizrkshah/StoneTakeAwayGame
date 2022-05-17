package model;

import lombok.Data;

/**
 * The Model that is used to populate the HighScores Table.
 */
@Data
public class HighScores {
    private String name;
    private Long numberOfWins;
}
