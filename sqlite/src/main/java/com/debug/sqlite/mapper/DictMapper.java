package com.debug.sqlite.mapper;

import com.debug.sqlite.model.Dict;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lyb
 * @since 2019-03-19
 */
@Mapper
@Repository
public interface DictMapper {
    /**
     * 插入 并查询id 赋给传入的对象
     * @param dict
     * @return
     */
    @Insert("INSERT INTO dict(key, value) VALUES(#{key}, #{value})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'dict')", before = false, keyProperty = "id", keyColumn = "id", resultType = long.class)
    long insert(Dict dict);

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @Select("SELECT * FROM dict WHERE id=#{id}")
    Dict select(Long id);

    /**
     * 查询全部
     * @return
     */
    @Select("SELECT * FROM dict")
    List<Dict> selectAll();

    /**
     * 更新 value
     * @param dict
     * @return
     */
    @Update("UPDATE dict SET value=#{value} WHERE id=#{id}")
    int updateValue(Dict dict);

    /**
     * 根据 ID 删除
     * @param id
     * @return
     */
    @Delete("DELETE FROM dict WHERE id=#{id}")
    int delete(Long id);
}
