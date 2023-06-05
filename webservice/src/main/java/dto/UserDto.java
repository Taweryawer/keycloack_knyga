package dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Length;
import validation.groups.AdminFormGroup;
import validation.groups.EditFormGroup;
import validation.groups.Extended;
import validation.groups.NotEditFormGroup;
import validation.validators.MinimalLengthIfNotEmpty;
import validation.validators.PasswordMatches;
import validation.validators.UniqueEmail;
import validation.validators.UniqueLogin;

@PasswordMatches
@UniqueEmail(editMode = true, groups = EditFormGroup.class)
@UniqueEmail(groups = NotEditFormGroup.class)
@UniqueLogin(groups = NotEditFormGroup.class)
@XmlRootElement
public class UserDto {

    @Pattern(regexp = "^[a-zA-Z0-9_-]*$", message = "Only characters a-z A-Z 0-9 _ and - are allowed in a username")
    @Length(min = 4, max = 20, message = "Username should be 4 to 20 characters long", groups = NotEditFormGroup.class)
    @NotEmpty(message = "Login can't be empty")
    private String login;

    @MinimalLengthIfNotEmpty(min = 8, message = "Minimal password length is 8", groups = EditFormGroup.class)
    @Length(min = 8, message = "Minimal password length is 8", groups = NotEditFormGroup.class)
    private String password;

    @NotEmpty(message = "Email can't be empty")
    @Email(message = "Email should be in valid format")
    private String email;

    @Length(min = 2, max = 20, message = "Minimal first name length is 2 and maximum is 20")
    @Pattern(regexp = "^[a-zA-ZА-Яа-яЁё]*$", message = "Please, type your real name!")
    @NotEmpty(message = "First name can't be empty")
    private String firstName;

    @Length(min = 2, max = 20, message = "Minimal last name length is 2 and maximum is 20")
    @Pattern(regexp = "^[a-zA-ZА-Яа-яЁё]*$", message = "Please, type your real name!")
    @NotEmpty(message = "Last name can't be empty")
    private String lastName;

    @Pattern(regexp = "^(\\d{4})-([0][0-9]|[1][0-2])-([0-2][0-9]|[3][0-1])$", message = "Date should be valid", groups = Extended.class)
    @NotEmpty(message = "Birthday can't be empty")
    private String birthday;

    @NotEmpty(message = "Role can't be empty", groups = AdminFormGroup.class)
    private String roleId;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}
