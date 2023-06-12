import com.github.javafaker.Faker;
import com.opencsv.CSVWriter;
import entity.Credit;
import entity.Movie;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

    private final static String MOVIES_PATH = "build/resources/main/result_data/movies.csv";
    private final static String CREDIT_PATH = "build/resources/main/result_data/credits.csv";

    private final static int YEAR_FROM = 1900;
    private final static int YEAR_TO = 2023;

    private final static int NUMBER_FROM = 60;
    private final static int NUMBER_TO = 240;
    private final static int MOVIES_CONT = 100;
    private final static int CREDIT_CONT = 5;

    private static final Faker faker = new Faker();
    private static final String[] ageCertifications = {"G", "PG", "PG-13", "R", "NC-17", "U", "U/A", "A", "S", "AL", "6", "9", "12", "12A", "15", "18", "18R", "R18", "R21", "M", "MA15+", "R16", "R18+", "X18", "T", "E", "E10+", "EC", "C", "CA", "GP", "M/PG", "TV-Y", "TV-Y7", "TV-G", "TV-PG", "TV-14", "TV-MA"};
    private static final String[] roles = {"Director", "Producer", "Screenwriter", "Actor", "Actress", "Cinematographer", "Film Editor", "Production Designer", "Costume Designer", "Music Composer"};
    private static final String[] genres = {"Action", "Comedy", "Drama", "Fantasy", "Horror", "Mystery", "Romance", "Thriller", "Western"};

    public static void main(String[] args) throws IOException {
        List<Movie> movies = new ArrayList<>();
        List<Credit> credits = new ArrayList<>();

        for (long i = 1; i <= MOVIES_CONT; i++) {
            Movie movie = generateMovie(i);
            movies.add(movie);

            for (int j = 0; j < CREDIT_CONT; j++) {
                Credit credit = generateCredit((i - 1) * CREDIT_CONT + j + 1, i);
                credits.add(credit);
            }
        }

        writeMoviesToCSV(movies);
        writeCreditsToCSV(credits);
    }


    private static Movie generateMovie(long id) {
        return Movie.builder()
                .id(id)
                .title(faker.book().title())
                .description(faker.lorem().sentence())
                .releaseYear(faker.number().numberBetween(YEAR_FROM, YEAR_TO))
                .ageCertification(ageCertifications[faker.random().nextInt(ageCertifications.length)])
                .runtime(faker.number().numberBetween(NUMBER_FROM, NUMBER_TO))
                .genres(Collections.singletonList(genres[faker.random().nextInt(genres.length)]))
                .productionCountry(faker.address().countryCode())
                .seasons(faker.bool().bool() ? faker.number().numberBetween(1, 10) : 0)
                .build();
    }

    private static Credit generateCredit(long id, long titleId) {
        return Credit.builder()
                .id(id)
                .titleId(titleId)
                .realName(faker.name().fullName())
                .characterName(faker.superhero().name())
                .role(roles[faker.random().nextInt(roles.length)])
                .build();
    }

    private static void writeMoviesToCSV(List<Movie> movies) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(MOVIES_PATH));
        String[] header = {"id", "title", "description", "release_year", "age_certification", "runtime", "genres", "production_country", "seasons"};
        writer.writeNext(header);
        for (Movie movie : movies) {
            writer.writeNext(new String[]{String.valueOf(movie.getId()), movie.getTitle(),
                    movie.getDescription(), String.valueOf(movie.getReleaseYear()), movie.getAgeCertification(),
                    String.valueOf(movie.getRuntime()), String.join(",", movie.getGenres()),
                    movie.getProductionCountry(), String.valueOf(movie.getSeasons())});
        }
        writer.close();
    }

    private static void writeCreditsToCSV(List<Credit> credits) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(CREDIT_PATH));
        String[] header = {"id", "title_id", "real_name", "character_name", "role"};
        writer.writeNext(header);
        for (Credit credit : credits) {
            writer.writeNext(new String[]{String.valueOf(credit.getId()), String.valueOf(credit.getTitleId()),
                    credit.getRealName(), credit.getCharacterName(), credit.getRole()});
        }
        writer.close();
    }
}
