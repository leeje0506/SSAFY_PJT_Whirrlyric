import { AxiosResponse } from "axios";
import { defaultAxios } from "./AuthoCommon";

const END_POINT = "songs";

export const makeMusicAPI ={
    // 음악 만들기
    createMusic(data:createMusicForm): Promise<AxiosResponse>{
        return defaultAxios({
            method:"POST",
            url:`${END_POINT}`,
            data: JSON.stringify(data)
            // data: {
            //     title : "",
            //     genre : "",
            //     intro : "",
            //     verse1: "",
            //     verse2: "",
            //     chorus: "",
            //     outro : "",
            //     bridge: ""
            // }
        });
    },

    // 장르 조회
    getGenreList(): Promise<AxiosResponse>{
        return defaultAxios({
            method:"GET",
            url:`${END_POINT}/genres/data`
        });
    },
    // 가사 타입 조회
    getLyricsList(): Promise<AxiosResponse>{
        return defaultAxios({
            method:"GET",
            url:`${END_POINT}/lyrics/parts`
        });
    }
}