import { useNavigate } from "react-router-dom";
import altDiscImg from "../../assets/icons/altDiscImg.png";

interface SimplePlayListCardProps {
  songWithCreator: SongWithCreator;
}

export default function SimplePlayListCard({songWithCreator}: SimplePlayListCardProps) {
  const navigate = useNavigate();

  return (
    <div
      className="flex mb-2 cursor-pointer"
      onClick={() => {
        navigate(`/play-song/${songWithCreator.song.songId}`);
      }}
    >
      <img
        src={altDiscImg}
        className="w-12 h-12 border-2 border-gray-400 rounded-xl mr-6"
      />
      <div className="flex-col">
        <h1>{songWithCreator.song.title}</h1>
        <p>{songWithCreator.nickname}</p>
      </div>
    </div>
  );
}
