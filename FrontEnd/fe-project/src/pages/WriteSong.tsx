import {makeMusicAPI} from "../api/makeMusicAPI.tsx";
import LyricsItem from "../components/makesongpage/LyricsItem.tsx";
import { useEffect, useState } from "react";

export default function WriteSong() {
  const [lyricsList, setLyricsList] = useState<lyrics[] | null>(null);

  const [genreList, setGenreList] = useState<genre[] | null>(null);

  // 음악 목록
  const getLyricsList = async () => {
    try {
      const response = await makeMusicAPI.getLyricsList();

      // const response = [{"id" : 1, "lyricsname" : "title"},{"id" : 2, "lyricsname" : "intro"},{"id" : 3, "lyricsname" : "verse1"},{"id" : 4, "lyricsname" : "verse2"},{"id" : 5, "lyricsname" : "chorus"},{"id" : 6, "lyricsname" : "bridge"},{"id" : 7, "lyricsname" : "outro"}];
      // 여기서 response.data를 직접 사용하기 전에 데이터 구조를 로그로 확인
      console.log(response);
      setLyricsList(response.data); // JSON 배열을 상태로 저장
    } catch (error) {
      console.error(error);
    }
  };

  // 장르 목록 - option으로 넣을 것!
  const getGenreList = async()=>{

    try{
      await makeMusicAPI.getGenreList()
          .then((response)=> {
            // console.log("GENRE");
            // console.log("data : " + response.data);
            // console.log("********");
            setGenreList(response.data)
          }).catch(error => console.log(error));
    }catch(error){
      console.log(error);
    }
  };

const handleSubmit= async (data : createMusicForm) =>{
        await makeMusicAPI.createMusic(data).then((response)=>
            // navigate 갈 것.
            console.log(response.data))
            .catch((error)=>console.log(error));
}


  useEffect(() => {
    getLyricsList();
  }, []);

  useEffect(() => {
    getGenreList();
  }, []);


  return (
      <div className="ml-8">
          <h1 className="text-3xl text-center mb-5">Make Song</h1>
          <form onSubmit={handleSubmit} className="w-full">
              {lyricsList ? (
                  lyricsList.map((lyric) => (
                      <LyricsItem key={lyric.id} lyric={lyric}/>
                  ))
              ) : (
                  <div className="text-lg">No created song</div>

              )}

              <h1 className="text-2xl">Genre</h1>
              <div className="flex-col">
                  <div>
                      <select className="w-full">
                          {genreList && genreList.length > 0 ? (
                              genreList.map((genre) => (
                                  <option key={genre.id} value={genre.genrename} className="w-full">
                                      {genre.genrename}
                                  </option>
                              ))
                          ) : (
                              <option className="w-full"> No genres available</option>
                          )}
                      </select>
                  </div>
                  <div>
                      <input type="submit" value="노래 만들기" className="mx-auto text-center w-full"/>
                  </div>
              </div>
          </form>
      </div>

  );
}