package springrest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import domain.Role;
import dto.UserDto;
import form.RemoveRequest;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;
import util.ValidationUtils;
import validation.groups.AdminFormGroup;
import validation.groups.EditFormGroup;
import validation.groups.NotEditFormGroup;
import validation.groups.ValidationSequence;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserService userService;
    private ObjectMapper mapper;
    private Logger log = LogManager.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService,
        ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/getAllRoles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> getAllRoles() {
        return userService.getAllRoles();
    }

    @GetMapping(value = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto getUser(@RequestParam String username) {
        return userService.getUserByLogin(username).orElse(null);
    }

    @PostMapping(value = "/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(
        @Validated({ ValidationSequence.class, NotEditFormGroup.class, AdminFormGroup.class })
        @RequestBody
        UserDto userDto,
        BindingResult validationResult) {

        ObjectNode body = mapper.createObjectNode();

        if (validationResult.hasErrors()) {
            return getResponseEntityForErrors(validationResult);
        }

        log.info("No validation errors for user {}, trying to save...", userDto);

        body.put("success", userService.createUser(userDto));
        return new ResponseEntity<>(body.toPrettyString(), HttpStatus.OK);
    }

    @PostMapping(value = "/editUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> editUser(
        @Validated({ ValidationSequence.class, EditFormGroup.class, AdminFormGroup.class })
        @RequestBody
        UserDto userDto,
        BindingResult validationResult) {

        ObjectNode body = mapper.createObjectNode();

        if (validationResult.hasErrors()) {
            return getResponseEntityForErrors(validationResult);
        }

        log.info("No validation errors for user update request {}, updating", userDto);

        body.put("success", userService.updateUser(userDto));
        return new ResponseEntity<>(body.toPrettyString(), HttpStatus.OK);
    }

    @PostMapping(value = "/removeUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeUser(@RequestBody RemoveRequest request) {
        ObjectNode body = mapper.createObjectNode();
        log.info("Removing user {}", request.getUsername());

        body.put("success", userService.removeUserByLogin(request.getUsername()));
        return new ResponseEntity<>(body.toPrettyString(), HttpStatus.OK);
    }

    private ResponseEntity<String> getResponseEntityForErrors(BindingResult validationResult) {
        ObjectNode body = mapper.createObjectNode();

        Map<String, String> errors = ValidationUtils.getErrorsMapForUserForm(validationResult);
        body.put("success", false);
        body.set("errors", mapper.convertValue(errors, JsonNode.class));
        return new ResponseEntity<>(body.toPrettyString(), HttpStatus.OK);
    }
}
