package entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Builder
public class Movie {
    private final long id;
    private final String title;
    private final String description;
    private final int releaseYear;
    private final String ageCertification;
    private final int runtime;
    private final List<String> genres;
    private final String productionCountry;
    private final int seasons;
}
