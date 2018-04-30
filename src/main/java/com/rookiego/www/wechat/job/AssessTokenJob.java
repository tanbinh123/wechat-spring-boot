package com.rookiego.www.wechat.job;

import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.rookiego.www.wechat.consts.SystemConfigConst;
import com.rookiego.www.wechat.dao.SystemConfigDAO;
import com.rookiego.www.wechat.domain.internal.SystemConfig;
import com.rookiego.www.wechat.utils.WeChatServiceUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * <b>类名称:</b>AssessTokenJob<br/>
 * <b>类注释:</b>微信AccessToken自动更新Job<br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>rookie<br/>
 * <b>修改人:</b>rookie<br/>
 * <b>修改时间:</b>2018年04月30日 15时35分43秒<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br/>
 */
public class AssessTokenJob implements SimpleJob {
    @Resource
    private SystemConfigDAO systemConfigDAO;

    public static final long invalidTime = 2 * 55 * 60 * 1000;

    @Override
    public void execute(ShardingContext shardingContext) {
        doBusiness();
    }

    private void doBusiness() {
        boolean needRefresh = judgeRefreshAccessToken();
        if (needRefresh) {
            refreshAccessToken();
        }
    }

    private boolean judgeRefreshAccessToken() {
        SystemConfig systemConfig = systemConfigDAO.queryOne(SystemConfigConst.SCORE_WEIXIN, SystemConfigConst.WX_ACCESSTOKEN);
        if (systemConfig == null) {
            return true;
        }

        String configValue = systemConfig.getConfigValue();
        if (StringUtils.isEmpty(configValue)) {
            return true;
        }

        Date now = new Date();
        Date updateTime = systemConfig.getUpdateTime();
        if (updateTime == null || now.getTime() - updateTime.getTime() >= invalidTime) {
            return true;
        }

        return false;
    }

    private void refreshAccessToken() {
        String appId = systemConfigDAO.queryConfigValue(SystemConfigConst.SCORE_WEIXIN, SystemConfigConst.WX_APPID);
        String secret = systemConfigDAO.queryConfigValue(SystemConfigConst.SCORE_WEIXIN, SystemConfigConst.WX_APPSECRET);

        String result = WeChatServiceUtils.getAccessToken(appId, secret);
        if (StringUtils.isEmpty(result)) {
            return;
        }

        Map<String, Object> map = JSONObject.parseObject(result, Map.class);

        SystemConfig systemConfig = systemConfigDAO.queryOne(SystemConfigConst.SCORE_WEIXIN, SystemConfigConst.WX_ACCESSTOKEN);
        if (systemConfig != null) {
            systemConfig.setConfigValue((String) map.get("access_token"));
            systemConfig.setExtendInfo(result);

            systemConfigDAO.updateOneConfig(systemConfig);
            return;
        }

        systemConfig = new SystemConfig();
        systemConfig.setConfigKey(SystemConfigConst.WX_ACCESSTOKEN);
        systemConfig.setConfigValue((String) map.get("access_token"));
        systemConfig.setConfigScope(SystemConfigConst.SCORE_WEIXIN);
        systemConfig.setExtendInfo(result);
        systemConfig.setCreateTime(new Date());
        systemConfig.setUpdateTime(new Date());
        systemConfigDAO.insertOne(systemConfig);
    }

    public static void main(String[] args) {
        Date now = new Date();
        System.out.println(now.getTime());
        long twoHoursTime = now.getTime() - 2 * 60 * 60 * 1000;
        Date date = new Date(twoHoursTime);
        System.out.println(date);
    }


}
