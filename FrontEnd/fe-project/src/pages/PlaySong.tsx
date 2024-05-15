import { useEffect, useState } from "react";
import { songsAPI } from "../api/songsAPI";
import altDiscImg from "../assets/altImages/altDiscImg.png";
import { useParams } from "react-router-dom";
import MusicPlayer from "../components/playsong/MusicPlayer";

export default function PlaySong() {
  const { songId } = useParams();

  const [songInfo, setSongInfo] = useState<SongWithCreator | null>(null);

  const getSongInfo = async (songId: number) => {
    try {
      const response = await songsAPI.getDetailSongInfo(songId);
      setSongInfo(response.data);

      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    const id = songId ? parseInt(songId) : null;

    if (id) {
      getSongInfo(id);
    }
  }, [songId]);

  return (
    songInfo && (
      <div>
        <div className="flex flex-col items-center">
          <img
            src={songInfo.song.imageUrl ? `https://${songInfo.song.imageUrl}` : altDiscImg}
            className="w-80 h-80 mb-4 border-2 border-gray-400 rounded-xl"
          />
        </div>
        <MusicPlayer
          songId={songInfo.song.songId}
          title={songInfo.song.title}
          songUrl={songInfo.song.songUrl}
        />
        <div className="mx-8 mt-5">
          <h1>{songInfo.song.title}</h1>
          <p>{songInfo.nickname}</p>
          <h1 className="mt-3">Lyrics</h1>
          <p>{songInfo.song.lyrics}</p>
        </div>
      </div>
    )
  );
}
