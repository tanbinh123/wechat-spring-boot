package com.rookiego.www.wechat.dao;

import com.rookiego.www.wechat.domain.internal.SystemConfig;
import org.apache.ibatis.annotations.*;

/**
 * <b>类名称:</b>SystemConfigDAO<br/>
 * <b>类注释:</b>系统配置DAO<br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>rookie<br/>
 * <b>修改人:</b>rookie<br/>
 * <b>修改时间:</b>2018年04月30日 16时06分47秒<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br/>
 */
@Mapper
public interface SystemConfigDAO {

    @Select("select * from system_config where config_scope=#{configScope} and config_key=#{configKey} limit 1")
    SystemConfig queryOne(@Param("configScope") String configScope, @Param("configKey") String configKey);

    @Select("select config_value from system_config where config_scope=#{configScope} and config_key=#{configKey} limit 1")
    String queryConfigValue(@Param("configScope") String configScope, @Param("configKey") String configKey);

    @Update("update system_config set config_value=#{configValue},update_time=now(),extend_info=#{extendInfo} where config_key=#{configKey} and config_scope=#{configScope}")
    int updateOneConfig(SystemConfig systemConfig);

    @Insert("insert into system_config values (#{configKey},#{configValue},#{configScope},#{createTime},#{updateTime},#{extendInfo})")
    int insertOne(SystemConfig systemConfig);
}
