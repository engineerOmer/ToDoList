package com.engineeromer.todo.controller;

import com.engineeromer.todo.dto.requestDto.toDoRequest.DeleteToDoRequestDto;
import com.engineeromer.todo.dto.requestDto.toDoRequest.SaveToDoRequestDto;
import com.engineeromer.todo.dto.requestDto.toDoRequest.UpdateToDoRequestDto;
import com.engineeromer.todo.dto.responseDto.ToDoResponseDto;
import com.engineeromer.todo.entity.ToDo;
import com.engineeromer.todo.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/toDo")
public class ToDosController {
    private final ToDoService toDoService;

    @PostMapping("/saveToDo")
    public ResponseEntity<ToDoResponseDto> saveToDo(@RequestBody SaveToDoRequestDto saveToDoRequestDto){
        return new ResponseEntity<>(toDoService.saveToDo(saveToDoRequestDto), HttpStatus.OK);
    }
    @DeleteMapping("/deleteToDo")
    public ResponseEntity<Boolean> deleteToDo(@RequestBody DeleteToDoRequestDto deleteToDoRequestDto){
        return new ResponseEntity<>(toDoService.deleteToDo(deleteToDoRequestDto),HttpStatus.OK);
    }
    @PutMapping("/updateToDo")
    public ResponseEntity<String> isDone(@RequestBody UpdateToDoRequestDto updateToDoRequestDto){
        String toDoReturnMessage = toDoService.updateMission(updateToDoRequestDto);
        return new ResponseEntity<>(toDoReturnMessage,HttpStatus.OK);
    }
    @GetMapping("toDoList")
    public ResponseEntity<List<ToDoResponseDto>> getToDoList(){
        return new ResponseEntity<>(toDoService.getAllToDo(),HttpStatus.OK);
    }
    @GetMapping("/listOfWeekToDo")
    public ResponseEntity<List<ToDo>> getAllByWeek(@RequestParam int page,@RequestParam int pageSize){
        return new ResponseEntity<List<ToDo>>(this.toDoService.getAll(page,pageSize),HttpStatus.OK);
    }
}
