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
            <script>
              SELECT id, summary, description, status
              FROM tasks
              <where>
                <if test='condition.summary != null and !condition.summary.isBlank()'>
                  summary LIKE CONCAT('%', #{condition.summary}, '%')
                </if>
                <if test='condition.status != null and !condition.status.isEmpty()'>
                  AND status IN (
                    <foreach item='item' index='index' collection='condition.status' separator=','>
                      #{item}
                    </foreach>
                  )
                </if>
              </where>
            </script>
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
