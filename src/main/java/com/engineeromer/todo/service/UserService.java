package com.engineeromer.todo.service;

import com.engineeromer.todo.dto.converter.ConverterService;
import com.engineeromer.todo.dto.requestDto.userRequest.DeleteUserRequestDto;
import com.engineeromer.todo.dto.requestDto.userRequest.SaveUserRequestDto;
import com.engineeromer.todo.dto.requestDto.userRequest.UpdateUserRequestDto;
import com.engineeromer.todo.dto.responseDto.ToDoResponseDto;
import com.engineeromer.todo.dto.responseDto.UserResponseDto;
import com.engineeromer.todo.entity.ToDo;
import com.engineeromer.todo.entity.User;
import com.engineeromer.todo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ConverterService convertService;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserResponseDto saveUser(SaveUserRequestDto saveUserRequestDto){
        User user = convertService.convertToUserFromUserSaveRequestDto(saveUserRequestDto);
        userRepository.save(user);
        UserResponseDto userResponseDto = convertService.convertToUserResponseDtoFromUser(user);
        return userResponseDto;
    }

    public boolean deleteUser(DeleteUserRequestDto deleteUserRequestDto){
        int id = deleteUserRequestDto.getId();
        Optional<User> userRepositoryById = userRepository.findById(id);
        User user = userRepositoryById.get();
        userRepository.deleteById(id);
        return !userRepository.existsById(id);
    }

    public UserResponseDto updateUserName(UpdateUserRequestDto updateUserRequestDto){
        User user = userRepository.findById(updateUserRequestDto.getId()).orElseThrow();
        user.setName(updateUserRequestDto.getName());
        userRepository.save(user);
        return convertService.convertToUserResponseDtoFromUser(user);
    }

    public List<UserResponseDto> getAllUser(){
        return userRepository.findAll().stream().map(
                user ->  UserResponseDto.builder().name(user.getName()).toDos(
                            user.getToDos().stream()
                                    .map(convertService::convertToDoResponseDtoFromToDo).toList()
                    ).build())
                .toList();
    }


    public User getUserFromId(int id){
        return userRepository.findById(id).orElseThrow();
    }




}
