package sg.edu.nus.iss.ssf13_workshoprevision.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Contact {
    
    @NotBlank(message = "This field cannot be blank")
    @Size(min = 3, max = 64, message = "Name must be between 3 and 64 characters")
    private String name;

    @NotEmpty(message = "This field cannot be empty")
    @Email(message = "The entered email address is an invalid email address")
    private String email;

    @NotBlank(message = "This field cannot be blank")
    @Size(min = 7, message = "The entered phone number is invalid")
    private String phoneNumber;

    @NotNull(message = "This field cannot be null")
    @Past(message = "The entered date of birth is invalid")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Min(value = 10, message = "Cannot be younger than 10 years old")
    @Max(value = 100, message = "Cannot be older than 100 years old")
    private int age;

    String id;

    public Contact() {
        this.generateId();
    }

    public Contact(String name, String email, String phoneNumber, LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.setDateOfBirth(dateOfBirth);
        this.generateId();
    }  

    public void generateId() {

        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        
        while (sb.length() < Constants.HEX_STRING_SIZE) 
            sb.append(Integer.toHexString(r.nextInt(Constants.HEX_VALUE_MAX)));
        
        this.setId(sb.substring(0, Constants.HEX_STRING_SIZE));
    }

    @Override
    public String toString() {
        return "Contact [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", dateOfBirth="
                + dateOfBirth + ", age=" + age + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        int calculatedAge = 0;

        if (dateOfBirth != null)
            calculatedAge = Period.between(dateOfBirth, LocalDate.now()).getYears();

        this.dateOfBirth = dateOfBirth;
        setAge(calculatedAge);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
