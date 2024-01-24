package com.delta.rental.deltarental.services.dtos.requests.city;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCityRequest {
    @NotNull(message = "Id boş geçilemez")
    @Positive(message = "Id 0'dan küçük olamaz")
    private int id;

    @NotBlank(message = "Şehir adı boş olamaz!")
    @Length(min = 2,message = "Girilen Şehir adı en az 2 harfli olmalıdır.")
    private String name;
}
