import { useNavigate } from "react-router-dom";
import altDiscImg from "../../assets/altImages/altDiscImg.png";

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
        src={songWithCreator.song.imageUrl ? `https://${songWithCreator.song.imageUrl}` : altDiscImg}
        className="w-12 h-12 border-2 border-gray-400 rounded-xl mr-6"
      />
      <div className="flex-col">
        <h1 className="overflow-hidden overflow-ellipsis whitespace-nowrap max-w-full">{songWithCreator.song.title ? songWithCreator.song.title : "제목 없음"}</h1>
        <p className="overflow-hidden overflow-ellipsis whitespace-nowrap max-w-full">{songWithCreator.nickname}</p>
      </div>
    </div>
  );
}
