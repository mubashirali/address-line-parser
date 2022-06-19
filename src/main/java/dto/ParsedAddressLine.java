package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParsedAddressLine {

    private String street;

    @JsonProperty("housenumber")
    private String houseNumber;
}
