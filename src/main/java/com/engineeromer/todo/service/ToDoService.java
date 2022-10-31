package com.engineeromer.todo.service;

import com.engineeromer.todo.dto.converter.ConverterService;
import com.engineeromer.todo.dto.requestDto.toDoRequest.DeleteToDoRequestDto;
import com.engineeromer.todo.dto.requestDto.toDoRequest.SaveToDoRequestDto;
import com.engineeromer.todo.dto.requestDto.toDoRequest.UpdateToDoRequestDto;
import com.engineeromer.todo.dto.responseDto.ToDoResponseDto;
import com.engineeromer.todo.dto.responseDto.UserResponseDto;
import com.engineeromer.todo.entity.ToDo;
import com.engineeromer.todo.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ToDoService {
    private final ConverterService converterService;
    private final ModelMapper modelMapper;
    private final ToDoRepository toDoRepository;
    private final UserService userService;


    public ToDoResponseDto saveToDo(SaveToDoRequestDto saveToDoRequestDto){
        ToDo toDo = converterService.convertToDoFromToDoSaveToDoRequestDto(saveToDoRequestDto);
        toDo.setUser(userService.getUserFromId(saveToDoRequestDto.getUserId()));

        ToDoResponseDto toDoResponseDto = converterService.convertToDoResponseDtoFromToDo(toDoRepository.save(toDo));
        return toDoResponseDto;
    }
    public boolean deleteToDo(DeleteToDoRequestDto deleteToDoRequestDto){
        int id = deleteToDoRequestDto.getId();
        Optional<ToDo> toDoRepositoryById = toDoRepository.findById(id);
        ToDo toDo = toDoRepositoryById.get();
        toDoRepository.deleteById(id);
        return !toDoRepository.existsById(id);
    }

    public String updateMission(UpdateToDoRequestDto updateToDoRequestDto){
        int id = updateToDoRequestDto.getId();
        boolean updateToDoRequestDtoDone = updateToDoRequestDto.isDone();
        try{
            Optional<ToDo> toDoRepositoryById = toDoRepository.findById(id);
            if (toDoRepositoryById.isPresent()){
                ToDo toDo = toDoRepositoryById.get();
                toDo.setDone(updateToDoRequestDtoDone);
                toDoRepository.save(toDo);
                return "Gorev durumu basarili bir sekilde guncellendi";
            }
            return "kayitli gorev bulunamadi";
        }catch (Exception e){
            e.printStackTrace();
            return "bir hata ile karsilasildi";
        }
    }

    public List<ToDoResponseDto> getAllToDo(){
        return toDoRepository.findAll().stream().map(converterService::convertToDoResponseDtoFromToDo).toList();
    }
    public List<ToDo> getAll(int pageNo, int pageSize) {
        Pageable pageable =  PageRequest.of(pageNo-1, pageSize);
        return  this.toDoRepository.findAll(pageable).getContent();
    }



}
