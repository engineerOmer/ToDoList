package com.engineeromer.todo.dto.responseDto;

import com.engineeromer.todo.entity.ToDo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String name;
    private List<ToDoResponseDto> toDos;

}
