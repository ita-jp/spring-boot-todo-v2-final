package com.example.todo.repository;

import com.example.todo.service.tasks.TaskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TaskRepository {

    @Select("SELECT id, summary, description, status FROM tasks")
    List<TaskEntity> select();

    @Select("SELECT id, summary, description, status FROM tasks WHERE id = #{id}")
    Optional<TaskEntity> selectById(@Param("id") long id);
}
