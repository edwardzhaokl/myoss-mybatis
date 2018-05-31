/*
 * Copyright 2018-2018 https://github.com/myoss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.myoss.phoenix.mybatis.mapper.template.select;

import java.io.Serializable;

import org.apache.ibatis.annotations.SelectProvider;

import com.github.myoss.phoenix.mybatis.mapper.annotation.RegisterMapper;
import com.github.myoss.phoenix.mybatis.mapper.template.select.impl.SelectMapperTemplate;

/**
 * 查询记录通用 Mapper 接口
 *
 * @author Jerry.Chen
 * @since 2018年4月27日 下午5:23:03
 */
@RegisterMapper
public interface SelectByPrimaryKeyMapper<T> {
    /**
     * 根据主键id查询实体对象
     *
     * @param id 主键id
     * @return 对应的实体对象
     * @see SelectMapperTemplate#selectByPrimaryKey
     */
    @SelectProvider(type = SelectMapperTemplate.class, method = "dynamicSql")
    T selectByPrimaryKey(Serializable id);

    /**
     * 根据主键字段查询实体对象，可以支持多主键字段的表
     *
     * @param condition 匹配的条件，主键有值的实体对象
     * @return 对应的实体对象
     * @see SelectMapperTemplate#selectWithPrimaryKey
     */
    @SelectProvider(type = SelectMapperTemplate.class, method = "dynamicSql")
    T selectWithPrimaryKey(T condition);
}
