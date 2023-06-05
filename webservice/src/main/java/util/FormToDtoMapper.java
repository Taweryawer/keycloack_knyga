package util;

import dto.UserDto;
import form.RegisterForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormToDtoMapper {

    UserDto formToDto(RegisterForm form);

    RegisterForm dtoToForm(UserDto dto);
}
