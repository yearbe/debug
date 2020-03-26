package com.debug.sqlite.controller;

import com.debug.sqlite.mapper.DictMapper;
import com.debug.sqlite.model.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lyb
 * @since 2019-03-19
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictMapper dictMapper;

    @RequestMapping("/add")
    public Dict add(String key, String value) {
        Dict dict = new Dict();
        dict.setKey(key);
        dict.setValue(value);
        dictMapper.insert(dict);
        return dict;
    }

    @RequestMapping("/select")
    public Dict add(Long id) {
        Dict dict = dictMapper.select(id);
        return dict;
    }

    @RequestMapping("/selectAll")
    public List<Dict> selectAll() {
        List<Dict> dicts = dictMapper.selectAll();
        return dicts;
    }

    @RequestMapping("/delete")
    public Dict delete(Long id) {
        Dict dict = dictMapper.select(id);
        dictMapper.delete(id);
        return dict;
    }
}
