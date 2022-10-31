package com.engineeromer.todo.dto.converter;

import com.engineeromer.todo.dto.requestDto.toDoRequest.SaveToDoRequestDto;
import com.engineeromer.todo.dto.requestDto.userRequest.SaveUserRequestDto;
import com.engineeromer.todo.dto.responseDto.ToDoResponseDto;
import com.engineeromer.todo.dto.responseDto.UserResponseDto;
import com.engineeromer.todo.entity.ToDo;
import com.engineeromer.todo.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ConverterService {
    private final ModelMapper modelMapper;

    public User convertToUserFromUserSaveRequestDto(SaveUserRequestDto saveUserRequestDto){
        User user= modelMapper.map(saveUserRequestDto,User.class);
        return user;
    }
    public UserResponseDto convertToUserResponseDtoFromUser(User user){
        UserResponseDto userResponseDto = modelMapper.map(user,UserResponseDto.class);
        return userResponseDto;
    }
    public ToDo convertToDoFromToDoSaveToDoRequestDto(SaveToDoRequestDto saveToDoRequestDto){
        ToDo toDo = modelMapper.map(saveToDoRequestDto,ToDo.class);
        return toDo;
    }
    public ToDoResponseDto convertToDoResponseDtoFromToDo(ToDo toDo){
        ToDoResponseDto toDoResponseDto = modelMapper.map(toDo,ToDoResponseDto.class);
        return toDoResponseDto;
    }

}
