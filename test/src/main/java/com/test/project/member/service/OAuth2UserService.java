/*
package com.test.project.member.service;

import com.test.project.member.dto.MemberDto;
import com.test.project.member.entity.Member;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(readOnly = true)
public class OAuth2UserService extends DefaultOAuth2UserService {
    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String oauthId = oAuth2User.getName();
        MemberDto memberDto = null;
        String oauthType = userRequest.getClientRegistration().getRegistrationId().toUpperCase();
        if(!"KAKAO".equals(oauthType)){
            throw new OAuthTypeMatchNotFoundException();
        }
        if(isNew(oauthType, oauthEmail)){
            switch (oauthType){
                case "KAKAO" -> {
                    Map attributesProperties = (Map) attributes.get("properties");
                    Map attributesKaKaoAcount = (Map) attributes.get("kakao_account");
                    String email = "%s@kakao.com".formatted(oauthId);
                    String username = "KAKAO_%s".formatted(oauthId);
                    if((boolean) attributesKaKaoAcount.get("has_email")){
                        email=(String) attributesKaKaoAcount.get("email");
                    }
                }
                MemberDto = Member.builder().email(email).username(username)
                        .password(passwordEncdoer.e)
            }
        }

    }
}
*/
