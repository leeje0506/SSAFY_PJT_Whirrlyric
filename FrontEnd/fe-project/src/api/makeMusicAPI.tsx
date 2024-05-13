import { AxiosResponse } from "axios";
import { defaultAxios } from "./AuthoCommon";

const END_POINT = "songs";

export const makeMusicAPI = {
  // 음악 만들기
  createMusic(data: createMusicForm): Promise<AxiosResponse> {
    console.log("creating music");
    console.log(data);
    return defaultAxios({
      method: "POST",
      url: `${END_POINT}`,
      data: data,
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,

      },
    });
  },

  // 장르 조회
  getGenreList(): Promise<AxiosResponse> {
    return defaultAxios({
      method: "GET",
      url: `${END_POINT}/genres/data`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    });
  },
  // 가사 타입 조회
  getLyricsList(): Promise<AxiosResponse> {
    return defaultAxios({
      method: "GET",
      url: `${END_POINT}/lyrics/parts`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    });
  },
  // 가이드 조회
  getGuide(): Promise<AxiosResponse> {
    {
      return defaultAxios({
        method: "GET",
        url: `${END_POINT}/lyrics/guide`,
        headers: {
          Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
        },
      });
    }
  },
};
