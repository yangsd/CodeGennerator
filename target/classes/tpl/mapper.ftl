<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${namespace}">

 	<#assign var1 = "#" />
 	<#assign var2 = "$" />
 	
	<!-- resultMap -->
	<resultMap id="resultList" type="${table.aliasName}">		
		<id column="${table.primary_key}" property="${table.primary_key}" />
		<!-- 请删除重复的字段  -->
	<#list fields as field> 
		<result column="${field.column}" property="${field.column}" />
	</#list> 
	</resultMap>

	<!-- 分页查询操作 -->
	<select id="getListByPage" parameterType="String" resultMap="resultList">
		select * from ${table.name} where 1=1
		<if test="sort!=null and order!=null">
			order by ${var2}{sort} ${var2}{order}
		</if>
		<if test="currentpage!=0 and pagesize!=0">
			limit ${var2}{currentpage},${var2}{pagesize}
		</if>
	</select>
	
	<!-- 查询所有记录数 -->
	<select id="getCount" resultType="int">
		select count(*) from ${table.name}
	</select>

	<!-- 根据主键查询信息 -->
	<select id="get${table.aliasName}ById" parameterType="Integer" resultMap="resultList">
		select * from ${table.name} where ${table.primary_key} = ${var1}{${table.primary_key}}
	</select>

	<!-- 新增操作 -->
	<insert id="add${table.aliasName}" parameterType="${table.aliasName}"
		useGeneratedKeys="true" keyProperty="${table.primary_key}">
		insert into
		${table.name}(
		<#list fields as field> 
			<#if field_index == 0>
		    	${field.column}
		    <#else>
		    	,${field.column}
		    </#if>			
		</#list>
		)values(
		<#list fields as field> 
			<#if field_index == 0>
		    	${var1}{${field.column}}
		    <#else>
		    	,${var1}{${field.column}}
		    </#if>			
		</#list>
		)
	</insert>

	<!-- 更新操作 -->
	<update id="update${table.aliasName}" parameterType="${table.aliasName}">
		update ${table.name} set
		<!-- 请删除更新的主键字段  -->
		<#list fields as field> 
			<#if field_index == 0>
		    	${field.column} = ${var1}{${field.column}}
		    <#else>
		    	,${field.column} = ${var1}{${field.column}}
		    </#if>
		</#list>
		where
		${table.primary_key}=${var1}{${table.primary_key}}
	</update>

	<!-- 删除操作 -->
	<delete id="delete${table.aliasName}" parameterType="Integer">
		delete from ${table.name} where ${table.primary_key} = ${var1}{${table.primary_key}}
	</delete>
</mapper>