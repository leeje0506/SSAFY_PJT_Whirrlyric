package com.example.member.domain.authCode;

import com.example.member.domain.type.OauthServerType;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Description;

@Tag(
    name = "AuthCode를 발급할 URL 제공하는 기능을 제공"
)
public interface AuthCodeRequestUrlProvider {

    @Description("자신이 어떤 OauthServerType을 지원할 수 있는 지")
    OauthServerType supportServer();

    @Description("provide()를 통해 URL을 생성하여 반환")
    String provide();
}
