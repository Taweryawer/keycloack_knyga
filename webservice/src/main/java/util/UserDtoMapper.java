package util;

import domain.Role;
import domain.User;
import dto.UserDto;
import exception.MappingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Mapping(source = "role", target = "roleId", qualifiedByName = "roleId")
    @Mapping(source = "birthday", target = "birthday", qualifiedByName = "stringFromBirthday")
    UserDto userToDto(User user);

    @Mapping(source = "birthday", target = "birthday", qualifiedByName = "birthdayFromString")
    User dtoToUser(UserDto userDto);

    @Named("birthdayFromString")
    static LocalDateTime stringToLocalDateTime(String birthday) {
        if (birthday == null) {
            throw new MappingException("Date is empty");
        }
        try {
            return LocalDate.parse(birthday, formatter).atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new MappingException("Wrong date format. Correct format: yyyy-MM-dd");
        }
    }

    @Named("stringFromBirthday")
    static String timeToString(LocalDateTime birthday) {
        return birthday.format(formatter);
    }

    @Named("roleId")
    static String roleToRoleId(Role role) {
        return role.getId().toString();
    }
}
