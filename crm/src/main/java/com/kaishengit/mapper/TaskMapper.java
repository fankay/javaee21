package com.kaishengit.mapper;

import com.kaishengit.pojo.Task;

import java.util.List;

public interface TaskMapper {
    void save(Task task);

    List<Task> findByUserId(Integer userId);
}
