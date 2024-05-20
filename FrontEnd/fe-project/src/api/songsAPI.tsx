import { AxiosResponse } from "axios";
import { authAxios, defaultAxios } from "./AuthCommon";

const END_POINT = "songs";

export const songsAPI = {
  getDetailSongInfo(songId: number): Promise<AxiosResponse> {
    return authAxios({
      method: "GET",
      url: `${END_POINT}/${songId}`,
    });
  },

  getSongList(): Promise<AxiosResponse> {
    return authAxios({
      method: "GET",
      url: `${END_POINT}/list`,
    });
  },

  downloadSong(downloadUrl: string): Promise<AxiosResponse> {
    return defaultAxios({
      method: "GET",
      url: downloadUrl,
      responseType: "blob",
    });
  },

  countSongPlay(songId: number): Promise<AxiosResponse> {
    return authAxios({
      method: "PATCH",
      url: `${END_POINT}/${songId}/play`,
    });
  },
};
