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

package com.github.myoss.phoenix.mybatis.table.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.ibatis.type.TypeHandler;

import com.github.myoss.phoenix.mybatis.type.UnsupportedTypeHandler;

/**
 * Is used to specify a mapped column for a persistent property or field. If no
 * Column annotation is specified, the default values are applied.
 * <p>
 * Examples: <blockquote>
 *
 * <pre>
 * Example 1:
 * &#064;Column(name="DESC", nullable=false)
 * private String description;
 *
 * Example 2:
 * &#064;Column(name="id", primaryKey=true)
 * public String getId() { return id; }
 *
 * Example 3:
 * &#064;Column(name="ORDER_COST", insertable=false, updatable=false)
 * private String cost;
 *
 * </pre>
 *
 * </blockquote>
 *
 * @author Jerry.Chen
 * @since 2018年4月26日 上午11:57:24
 */
@Target({ METHOD, FIELD })
@Retention(RUNTIME)
@Documented
public @interface Column {

    /**
     * (Optional) The name of the column. Defaults to the property or field
     * name.
     *
     * @return 字段名
     */
    String name() default "";

    /**
     * (Optional) 编码之后的字段名，比如：字段名是关键字、有空格
     *
     * @return 编码之后的字段名
     */
    String escapedName() default "";

    /**
     * (Optional) Whether the database column is nullable.
     *
     * @return 字段是否允许为 {@code null}
     */
    boolean nullable() default true;

    /**
     * 字段是否包含在 SQL INSERT statements，某些字段不想被保存
     * <p>
     * (Optional) Whether the column is included in SQL INSERT statements
     * generated by the persistence provider.
     *
     * @return 字段是否在 INSERT 中使用
     */
    boolean insertable() default true;

    /**
     * 字段是否包含在 SQL UPDATE statements，某些字段不想被更新
     * <p>
     * (Optional) Whether the column is included in SQL UPDATE statements
     * generated by the persistence provider.
     *
     * @return 字段是否在 UPDATE 中使用
     */
    boolean updatable() default true;

    /**
     * 字段是否包含在 SQL SELECT statements，某些字段不想被查询出来
     * <p>
     * (Optional) Whether the column is included in SQL SELECT statements
     * generated by the persistence provider.
     *
     * @return 字段是否在 SELECT 中使用
     */
    boolean selectable() default true;

    /**
     * 用于 SQL 语句在 INSERT/UPDATE 的时候，字段是否需要进行填充，参考 {@link FillRule} 的详细说明
     *
     * @return 字段填充规则，支持设置多种规则
     */
    FillRule[] fillRule() default FillRule.NONE;

    /**
     * 是否为主键字段
     *
     * @return 字段是否为主键
     */
    boolean primaryKey() default false;

    /**
     * 逻辑删除数据，软删除，用字段标记数据被删除了，不做物理删除
     *
     * @return 字段是否支持逻辑删除
     */
    boolean logicDelete() default false;

    /**
     * 数据标记为"逻辑删除"的值
     *
     * @return 逻辑删除的值
     */
    String logicDeleteValue() default "";

    /**
     * 数据标记为"逻辑未删除"的值
     *
     * @return 逻辑未删除的值
     */
    String logicUnDeleteValue() default "";

    /**
     * (Optional) jdbcType name, like: VARCHAR, INTEGER, BIGINT, DECIMAL
     *
     * @return jdbcType name
     */
    String jdbcTypeName() default "";

    /**
     * 数据库字段类型转换为Java类型处理器
     *
     * @return Java类型处理器
     */
    Class<? extends TypeHandler<?>> typeHandler() default UnsupportedTypeHandler.class;
}
