package com.davidborza.billing.controller;

import com.davidborza.billing.entity.User;
import com.davidborza.billing.model.dto.request.UserRequestDto;
import com.davidborza.billing.model.dto.response.UserResponseDto;
import com.davidborza.billing.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2023. 01. 28.
 *
 * @author David
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserService userService;
    private final ConversionService conversionService;

    /**
     * Get all users.
     *
     * @return List of users.
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream().map(user -> conversionService.convert(user, UserResponseDto.class)).toList();
    }

    /**
     * Save the given user.
     *
     * @param userRequestDto The given user.
     * @return Saved user.
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public UserResponseDto save(@RequestBody final UserRequestDto userRequestDto) {
        return  conversionService.convert(userService.save(conversionService.convert(userRequestDto, User.class)), UserResponseDto.class);
    }

    /**
     * Update the given User by ID.
     *
     * @param id The given ID.
     * @param userRequestDto The given userRequestDto object.
     * @return The Updated UserResponseDto object.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public UserResponseDto update(@PathVariable("id") final Long id, @RequestBody final UserRequestDto userRequestDto) {
        return conversionService.convert(userService.update(id, conversionService.convert(userRequestDto, User.class)), UserResponseDto.class);
    }

    /**
     * Delete User by ID.
     *
     * @param id The given ID.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public void delete(@PathVariable final Long id) {
        userService.deleteById(id);
    }
}
