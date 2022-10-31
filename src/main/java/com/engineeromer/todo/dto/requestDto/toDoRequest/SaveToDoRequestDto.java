package com.engineeromer.todo.dto.requestDto.toDoRequest;

import com.engineeromer.todo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveToDoRequestDto {
    private String mission;
    private String day;
    private boolean done;
    private int userId;
}
