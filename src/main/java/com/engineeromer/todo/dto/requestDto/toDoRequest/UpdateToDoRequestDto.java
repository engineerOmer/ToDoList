package com.engineeromer.todo.dto.requestDto.toDoRequest;

import lombok.Data;

@Data
public class UpdateToDoRequestDto {
    private int id;
    private boolean done;
}
