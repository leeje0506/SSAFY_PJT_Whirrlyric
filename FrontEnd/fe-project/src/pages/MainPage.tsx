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

  const popularListSize = 5;
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
      <Carousel
        className="rounded-xl"
        placeholder={undefined}
        onPointerEnterCapture={undefined}
        onPointerLeaveCapture={undefined}
      >
        {popularSongList ? (
          popularSongList.map((songs, index) => (
            <div
              key={index}
              className="flex flex-col items-center relative h-full w-full bg-gray-200"
            >
              <h1>{`인기곡 ${index + 1}`}</h1>
              <div>
                {songs.map((song, i) => (
                  <DetailPlayListCard
                    key={song.song.songId}
                    rank={index * 5 + i + 1}
                    songWithCreator={song}
                  />
                ))}
              </div>
            </div>
          ))
        ) : (
          <div>
            <h1>인기곡이 존재하지 않습니다.</h1>
          </div>
        )}
      </Carousel>

      <DragScrollContainer addClass="mt-8 w-[400px]">
        <h1>Latest</h1>
        <div className="flex">
          {latestSongList?.map((song) => (
            <AlbumCard key={song.song.songId} songWithCreator={song} />
          ))}
        </div>
      </DragScrollContainer>

      {genreSongList?.map((songListWithGenre) => (
        <DragScrollContainer addClass="mt-8 w-[400px]">
          <h1>{songListWithGenre.genre}</h1>
          <div className="flex">
            {songListWithGenre.songList.map((song) => (
              <AlbumCard key={song.song.songId} songWithCreator={song} />
            ))}
          </div>
        </DragScrollContainer>
      ))}
    </>
  );
}
