package com.example.userservice.dto.consumer;

import com.example.userservice.entity.enums.StatusUser;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class ConsumerDtoForAdd {
    private Long id;
    private MultipartFile image;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    private String name;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    private String surname;
    @NotNull(message = "{error.field.empty}")
    @Pattern(regexp = "^\\+380(31|32|33|34|35|36|37|38|39|41|42|43|44|45|46|47|48|49|50|59|61|63|66|67|68|73|89|91|92|93|94|95|96|97|98|99)\\d{7}$", message = "{error.field.telephone}")
    private String phone;
    @NotNull(message = "{error.field.empty}")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{error.field.email}")
    private String email;
    @Size(min = 8, max = 100, message = "{error.field.size.max}")
    private String password;
    @NotNull(message = "{error.field.empty}")
    private StatusUser status;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    private String agentName;
    @Size(max = 100, message = "{error.field.size.max}")
    @NotNull(message = "{error.field.empty}")
    private String agentSurname;
    @NotNull(message = "{error.field.empty}")
    @Pattern(regexp = "^\\+380(31|32|33|34|35|36|37|38|39|41|42|43|44|45|46|47|48|49|50|59|61|63|66|67|68|73|89|91|92|93|94|95|96|97|98|99)\\d{7}$", message = "{error.field.telephone}")
    private String agentPhone;
    @NotNull(message = "{error.field.empty}")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{error.field.email}")
    private String agentEmail;
    @NotNull(message = "{error.field.empty}")
    private LocalDate subscriptionValidUntil;
    @NotNull(message = "{error.field.empty}")
    private Boolean onlyMe;
    @NotNull(message = "{error.field.empty}")
    private Boolean meAndAgent;
    @NotNull(message = "{error.field.empty}")
    private Boolean onlyAgent;
    @NotNull(message = "{error.field.empty}")
    private Boolean exclude;
}