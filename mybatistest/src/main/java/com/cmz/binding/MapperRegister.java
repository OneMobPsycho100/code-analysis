package com.cmz.binding;

import com.cmz.configuration.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chenmingzhe
 * @Date: 2020/2/14 11:37
 */
@Getter
@Setter
public class MapperRegister {

    private Configuration config;

    private Map<String,MapperMethod> knownMappers = new HashMap<String,MapperMethod>();

}
