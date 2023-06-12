package entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class Credit {
    private final long id;
    private final long titleId;
    private final String realName;
    private final String characterName;
    private final String role;
}
