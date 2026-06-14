package virtusa.project.exceptions;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiErrorResponse {

    private String issue;
    private int status;
    private LocalDateTime timestamp;
}