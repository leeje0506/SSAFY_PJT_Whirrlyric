import { AxiosResponse } from "axios";
import { authAxios } from "./AuthCommon";

const END_POINT = "songs";

export const makeMusicAPI = {
  // 음악 만들기
  createMusic(data: createMusicForm): Promise<AxiosResponse> {
    return authAxios({
      method: "POST",
      url: `${END_POINT}`,
      data: data,
    });
  },
  // 장르 조회
  getGenreList(): Promise<AxiosResponse> {
    return authAxios({
      method: "GET",
      url: `${END_POINT}/genres/data`,
    });
  },
  // 가사 타입 조회
  getLyricsList(): Promise<AxiosResponse> {
    return authAxios({
      method: "GET",
      url: `${END_POINT}/lyrics/parts`,
    });
  },
  // 가이드 조회
  getGuide(): Promise<AxiosResponse> {
    {
      return authAxios({
        method: "GET",
        url: `${END_POINT}/lyrics/guide`,
      });
    }
  },
};
