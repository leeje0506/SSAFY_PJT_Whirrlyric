import { AxiosResponse } from "axios";
import { defaultAxios } from "./AuthoCommon";

const END_POINT = "songs";

export const songsAPI = {
  getDetailSongInfo(songId: number): Promise<AxiosResponse> {
    return defaultAxios({
      method: "GET",
      url: `${END_POINT}/${songId}`,
    });
  },

  getGenreSongList(genre: string): Promise<AxiosResponse> {
    return defaultAxios({
      method: "GET",
      url: `${END_POINT}/list?genre=${genre}`,
    });
  },

  downloadSong(downloadUrl: string): Promise<AxiosResponse> {
    return defaultAxios({
        method: "GET",
        url: `${END_POINT}/download/${downloadUrl}`
    })
  }
};