package com.example.userservice.dto.consumer;

import com.example.userservice.entity.enums.StatusUser;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@Schema(description = "DTO for adding consumer")
public class ConsumerDtoForAdd {
    private Long id;
    private MultipartFile image;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "Boris")
    private String name;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "Diablo")
    private String surname;
    @NotNull(message = "{error.field.empty}")
    @Pattern(regexp = "^\\+380(31|32|33|34|35|36|37|38|39|41|42|43|44|45|46|47|48|49|50|59|61|63|66|67|68|73|89|91|92|93|94|95|96|97|98|99)\\d{7}$", message = "{error.field.telephone}")
    @Schema(defaultValue = "+380939393939")
    private String phone;
    @NotNull(message = "{error.field.empty}")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{error.field.email}")
    @Schema(defaultValue = "qwerty@gmail.com")
    private String email;
    @Size(min = 8, max = 100, message = "{error.field.size.max}")
    @Schema(defaultValue = "qwertyqwert")
    private String password;
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "ACTIVE")
    private StatusUser status;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "Ivan")
    private String agentName;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "CS2")
    private String agentSurname;
    @NotNull(message = "{error.field.empty}")
    @Pattern(regexp = "^\\+380(31|32|33|34|35|36|37|38|39|41|42|43|44|45|46|47|48|49|50|59|61|63|66|67|68|73|89|91|92|93|94|95|96|97|98|99)\\d{7}$", message = "{error.field.telephone}")
    @Schema(defaultValue = "+380939393930")
    private String agentPhone;
    @NotNull(message = "{error.field.empty}")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{error.field.email}")
    @Schema(defaultValue = "qwertyqwer")
    private String agentEmail;
    @NotNull(message = "{error.field.empty}")
    private LocalDate subscriptionValidUntil;
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "true")
    private Boolean onlyMe;
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "true")
    private Boolean meAndAgent;
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "true")
    private Boolean onlyAgent;
    @NotNull(message = "{error.field.empty}")
    @Schema(defaultValue = "true")
    private Boolean exclude;
}