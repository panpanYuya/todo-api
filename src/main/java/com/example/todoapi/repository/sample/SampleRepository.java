package com.example.todoapi.repository.sample;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SampleRepository {

  @Select("SELCT content FROM samples ORDER BY id LIMIT 1")
  public SampleRecord select();
}