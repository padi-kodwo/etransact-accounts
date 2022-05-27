package com.etransact.accounts.controller;


import com.etransact.accounts.dto.CreateUserDto;
import com.etransact.accounts.dto.UserDto;
import com.etransact.accounts.dto.response.ApiResponse;
import com.etransact.accounts.dto.response.PagedContent;
import com.etransact.accounts.entity.User;
import com.etransact.accounts.service.UserService;
import com.etransact.accounts.spec.ListUserSpec;
import com.etransact.accounts.spec.SearchUserSpec;
import com.etransact.accounts.util.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/search")
    public ApiResponse<PagedContent<UserDto>> searchUsers(SearchUserSpec searchUserSpec,
                                                          Pageable pageable,
                                                          HttpServletRequest httpServletRequest){
        String sessionId = httpServletRequest.getSession().getId();

        logger.info("["+ sessionId +"] http request: searchUsers");

        Page<User> allUsers = userService.getAll(searchUserSpec, pageable);
        List<UserDto> userDtos = allUsers
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        ApiResponse<PagedContent<UserDto>> apiResponse = Utils.wrapInPagedApiResponse(allUsers, userDtos, sessionId);

        logger.info("["+ sessionId +"] http response: searchUsers : {}", apiResponse);

        return apiResponse;
    }

    @GetMapping("/all")
    public ApiResponse<PagedContent<UserDto>> getAllUsers(ListUserSpec listUserSpec,
                                                          Pageable pageable,
                                                          HttpServletRequest httpServletRequest){

        String sessionId = httpServletRequest.getSession().getId();

        logger.info("["+ sessionId +"] http request: getAllUsers");

        Page<User> allUsers = userService.getAll(listUserSpec, pageable);
        List<UserDto> userDtos = allUsers
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        ApiResponse<PagedContent<UserDto>> apiResponse= Utils.wrapInPagedApiResponse(allUsers, userDtos, sessionId);

        logger.info("["+ sessionId +"] http response: getAllUsers: {}", apiResponse);

        return apiResponse;
    }


    @GetMapping(value = "/{id}")
    public ApiResponse<UserDto> getUserById(@PathVariable String id,
                                            HttpServletRequest httpServletRequest) {

        String sessionId = httpServletRequest.getSession().getId();

        logger.info("["+ sessionId +"] http request: getUserById", id);

        User user = userService.get(id);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        ApiResponse<UserDto> apiResponse= Utils.wrapInApiResponse(userDto, sessionId);

        logger.info("["+ sessionId +"] http response: getUserById: {}", apiResponse);

        return apiResponse;
    }

    @GetMapping(value = "/username/{username}")
    public ApiResponse<UserDto> getUserByUsername(@PathVariable String username,
                                                  HttpServletRequest httpServletRequest) {

        String sessionId = httpServletRequest.getSession().getId();

        logger.info("["+ sessionId +"] http request: getUserByUsername: ", username);

        User user = userService.getByUsername(username);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        ApiResponse<UserDto> apiResponse= Utils.wrapInApiResponse(userDto, sessionId);

        logger.info("["+ sessionId +"] http response: getUserByUsername: {}", apiResponse);

        return apiResponse;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserDto> signUpUser(@RequestBody @Valid CreateUserDto createUserDto,
                                           HttpServletRequest httpServletRequest) {

        String sessionId = httpServletRequest.getSession().getId();

        logger.info("["+ sessionId +"] http request: signUpUser");

        User user = userService.create(createUserDto);
        UserDto userDto = modelMapper.map(user, UserDto.class);

        ApiResponse<UserDto> apiResponse= Utils.wrapInApiResponse(userDto, sessionId);

        logger.info("["+ sessionId +"] http response: signUpUser: {}", apiResponse);

        return apiResponse;
    }


}
