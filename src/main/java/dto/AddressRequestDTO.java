package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO implements Serializable {
    private static final long serialVersionUID = 3821826596176100840L;

    @NotNull
    private String address;
}
