import { Carousel } from "@material-tailwind/react";
import AlbumCard from "../components/mainpage/AlbumCard";
import DetailPlayListCard from "../components/mainpage/DetailPlayListCard";
import DragScrollContainer from "../utils/DragScrollContainer";
import { songsAPI } from "../api/songsAPI";
import { useEffect, useState } from "react";

export default function MainPage() {
  const [songList, setSongList] = useState<SongListContainer | null>(null);

  const getSongList = async () => {
    try {
      const response = await songsAPI.getSongList();
      setSongList(response.data);

      console.log(response);
    } catch (error) {
      console.error(error);
    }
  };

  const popularListSize = 4;
  const popularSongList = songList
    ? (Array.from({ length: 3 }, (_, index) =>
        songList.popularSongList.slice(
          index * popularListSize,
          (index + 1) * popularListSize
        )
      ) as [SongWithCreator[]])
    : null;

  const latestSongList = songList?.latestSongList;
  const genreSongList = songList?.genreSongList;

  useEffect(() => {
    getSongList();
  }, []);

  return (
    <>
      {popularSongList && (
        <Carousel
          className=""
          placeholder={undefined}
          onPointerEnterCapture={undefined}
          onPointerLeaveCapture={undefined}
        >
          {popularSongList ? (
            popularSongList.map((songs, index) => (
              <div
                key={index}
                className="flex flex-col rounded-xl items-center relative h-full w-96 bg-gray-400 mx-auto"
              >
                <h1 className="mt-3 mb-5">{`인기곡 ${index + 1}`}</h1>
                <div>
                  {songs.map((song, i) => (
                    <DetailPlayListCard
                      key={song.song.songId}
                      rank={index * 4 + i + 1}
                      songWithCreator={song}
                    />
                  ))}
                </div>
                <div className="mt-10"></div>
              </div>
            ))
          ) : (
            <div>
              <h1>인기곡이 존재하지 않습니다.</h1>
            </div>
          )}
        </Carousel>
      )}

      {latestSongList && (
        <>
          <h1 className="mt-4 mb-2 ml-4">Latest</h1>
          <DragScrollContainer addClass="w-[400px] mx-auto">
            <div className="flex">
              {latestSongList?.map((song) => (
                <AlbumCard key={song.song.songId} songWithCreator={song} />
              ))}
            </div>
          </DragScrollContainer>
        </>
      )}

      {genreSongList?.map((songListWithGenre) => (
        <div key={songListWithGenre.genre}>
          {songListWithGenre.songList && (
            <>
              <h1 className="mt-4 mb-2 ml-4">{songListWithGenre.genre}</h1>
              <DragScrollContainer addClass="w-[400px] mx-auto">
                <div className="flex">
                  {songListWithGenre.songList.map((song) => (
                    <AlbumCard key={song.song.songId} songWithCreator={song} />
                  ))}
                </div>
              </DragScrollContainer>
            </>
          )}
        </div>
      ))}
    </>
  );
}
