import { AxiosResponse } from "axios";
import { authAxios } from "./AuthCommon";

const END_POINT = "mypage";

export const mypageAPI = {
  getMypageInfo(memberId: number): Promise<AxiosResponse> {
    return authAxios({
      method: "GET",
      url: `${END_POINT}/${memberId}`,
    });
  },

  changeMyMainSong(nickname:string, selectedSongId: number): Promise<AxiosResponse> {
    return authAxios({
      method: "POST",
      url: `${END_POINT}/mainsong`,
      data: {
        nickname: nickname,
        songId: selectedSongId,
      },
    });
  },

  changeName(changedNickName: string): Promise<AxiosResponse> {
    return authAxios({
      method: "PUT",
      url: `${END_POINT}/rename`,
      data: {
        nickname: changedNickName,
      },
    });
  },
};
