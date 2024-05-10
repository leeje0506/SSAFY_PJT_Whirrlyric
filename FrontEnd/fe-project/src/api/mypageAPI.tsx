import { AxiosResponse } from "axios";
import { defaultAxios } from "./AuthoCommon";

const END_POINT = "mypage";

export const mypageAPI = {
  getMypageInfo(memberId: number): Promise<AxiosResponse> {
    return defaultAxios({
      method: "GET",
      url: `${END_POINT}/${memberId}`,
    });
  },

  changeMyMainSong(selectedSongId: number): Promise<AxiosResponse> {
    return defaultAxios({
      method: "POST",
      url: `${END_POINT}/mainsong`,
      data: {
        nickname: "member1",
        songId: selectedSongId,
      },
    });
  },
};
