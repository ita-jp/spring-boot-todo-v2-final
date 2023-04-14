package com.example.todo.repository;

import com.example.todo.service.tasks.TaskEntity;
import com.example.todo.service.tasks.TaskSearchEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TaskRepository {

    @Select("""
            SELECT id, summary, description, status
            FROM tasks
            WHERE summary LIKE CONCAT('%', #{condition.summary}, '%')
            """)
    List<TaskEntity> select(@Param("condition") TaskSearchEntity condition);

    @Select("SELECT id, summary, description, status FROM tasks WHERE id = #{id}")
    Optional<TaskEntity> selectById(@Param("id") long id);

    @Insert("INSERT INTO tasks (summary, description, status) VALUES (#{task.summary}, #{task.description}, #{task.status})")
    void insert(@Param("task") TaskEntity task);

    @Update("""
            UPDATE tasks
            SET
                summary     = #{entity.summary},
                description = #{entity.description},
                status      = #{entity.status}
            WHERE id = #{entity.id}
            """)
    void update(@Param("entity") TaskEntity entity);

    @Delete("""
            DELETE FROM tasks
            WHERE id = #{id}
            """)
    boolean deleteById(@Param("id") long id);
}
