import { AxiosResponse } from "axios";
import { authAxios, defaultAxios } from "./AuthCommon";

const END_POINT = "oauth";

export const loginAPI = {
  getKakaoRedirect(): Promise<AxiosResponse> {
    return defaultAxios({
      method: "GET",
      url: `${END_POINT}/kakao`,
    });
  },

  getKakaoLogin(code: string): Promise<AxiosResponse> {
    return defaultAxios({
      method: "GET",
      url: `${END_POINT}/login/kakao?code=${code}`,
    });
  },

  kakaoLogout(): Promise<AxiosResponse> {
    return defaultAxios({
      method: "POST",
      url: `${END_POINT}/logout/kakao`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
      },
    });
  },

  // 토큰을 재발급 받는다
  reissueToken(): Promise<AxiosResponse> {
    return authAxios({
      method: "POST",
      url: `members/reissue`,
    });
  },
};
