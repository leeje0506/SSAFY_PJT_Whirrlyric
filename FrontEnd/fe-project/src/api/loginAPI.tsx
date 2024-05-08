import { AxiosResponse } from "axios";
import { defaultAxios } from "./AuthoCommon";

const END_POINT = "oauth";
const END_POINT_MEMBER = "members"

export const loginAPI = {
    kakaoSingIn(): Promise<AxiosResponse>{
        return defaultAxios({
            method :"POST",
            url: `${END_POINT_MEMBER}/signup`
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
    }


}