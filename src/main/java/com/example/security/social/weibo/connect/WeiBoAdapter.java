package com.example.security.social.weibo.connect;

import com.example.security.domain.dto.social.WeiBoUserTo;
import com.example.security.social.weibo.api.WeiBo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 三方用户信息到本地数据库字段适配器
 */
public class WeiBoAdapter implements ApiAdapter<WeiBo> {

    @Override
    public boolean test(WeiBo weiBo) {
        return true;
    }

    @Override
    public void setConnectionValues(WeiBo weiBo, ConnectionValues connectionValues) {
        WeiBoUserTo user= weiBo.getUserInfo();
        connectionValues.setDisplayName(user.getName());
        connectionValues.setProviderUserId(user.getIdstr());
        connectionValues.setImageUrl("");
    }

    @Override
    public UserProfile fetchUserProfile(WeiBo weiBo) {
        return null;
    }

    @Override
    public void updateStatus(WeiBo weiBo, String message) {

    }
}
