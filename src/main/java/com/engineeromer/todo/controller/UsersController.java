package com.engineeromer.todo.controller;

import com.engineeromer.todo.dto.requestDto.userRequest.DeleteUserRequestDto;
import com.engineeromer.todo.dto.requestDto.userRequest.SaveUserRequestDto;
import com.engineeromer.todo.dto.requestDto.userRequest.UpdateUserRequestDto;
import com.engineeromer.todo.dto.responseDto.UserResponseDto;
import com.engineeromer.todo.entity.User;
import com.engineeromer.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody SaveUserRequestDto saveUserRequestDto){
        return new ResponseEntity<>(userService.saveUser(saveUserRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Boolean> deleteUser(@RequestBody DeleteUserRequestDto deleteUserRequestDto){
        return new ResponseEntity<>(userService.deleteUser(deleteUserRequestDto),HttpStatus.OK);
    }
    @PutMapping("/updateUser")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UpdateUserRequestDto updateUserRequestDto){
        return new ResponseEntity<>(userService.updateUserName(updateUserRequestDto),HttpStatus.OK);
    }

    @GetMapping("/userList")
    public ResponseEntity<List<UserResponseDto>> getUsersList(){
        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
    }


}
