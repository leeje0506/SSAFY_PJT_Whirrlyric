import { AxiosResponse } from "axios";
import { defaultAxios } from "./AuthoCommon";

const END_POINT = "oauth";
// const END_POINT_MEMBER = "members"

export const loginAPI = {
    getKakaoRedirect(): Promise<AxiosResponse>{
        return defaultAxios({
            method :"GET",
            url: `${END_POINT}/kakao`,
        });
    },

    getKakaoLogin(code : string): Promise<AxiosResponse>{
        return defaultAxios({
            method: "GET",
            url: `${END_POINT}/login/kakao?code=${code}`
        })
    },

    kakaoLogout(): Promise<AxiosResponse>{
        return defaultAxios({
            method: "POST",
            url: `${END_POINT}/logout/kakao`
        })
    },

    // 토큰을 재발급 받는다
    reissueToken(): Promise<AxiosResponse>{
        return defaultAxios({
            method: "POST",
            url: `${END_POINT}/member/reissue`
        })
    }


}