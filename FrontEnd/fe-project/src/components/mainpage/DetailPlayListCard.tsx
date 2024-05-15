import { useNavigate } from "react-router-dom";
import altDiscImg from "../../assets/altImages/altDiscImg.png";

interface DetailPlayListCardProps{
  songWithCreator: SongWithCreator;
  rank: number
};

export default function DetailPlayListCard({songWithCreator, rank}:DetailPlayListCardProps) {
  const navigate = useNavigate();

  const displayRank = rank < 10 ? `0${rank}` : rank;

  return (
    <div
      className="flex h-12 mb-2 cursor-pointer"
      onClick={() => {
        navigate(`/play-song/${songWithCreator.song.songId}`);
      }}
    >
      <h1 className="flex flex-col justify-center mx-2">{displayRank}</h1>
      <img
        src={songWithCreator.song.imageUrl ? `https://${songWithCreator.song.imageUrl}` : altDiscImg}
        className="w-12 h-12 border-2 border-gray-200 rounded-xl mx-2"
      />
      <div className="flex-col w-40 mx-2">
        <h1>{songWithCreator.song.title}</h1>
        <p>{songWithCreator.nickname}</p>
      </div>

      <h1 className="flex flex-col justify-center mx-2">{songWithCreator.song.playCount}</h1>
    </div>
  );
}
