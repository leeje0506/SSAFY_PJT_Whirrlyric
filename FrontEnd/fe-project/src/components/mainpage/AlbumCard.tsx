import { useNavigate } from "react-router-dom";
import altDiscImg from "../../assets/altImages/altDiscImg.png";

interface AlbumCardProps{
  songWithCreator: SongWithCreator;
};

export default function AlbumCard({songWithCreator}:AlbumCardProps) {
  const navigate = useNavigate();
  const handleOnClick = () => {
    navigate(`/play-song/${songWithCreator.song.songId}`);
  };

  return (
    <div className="flex flex-col w-40 h-60 mr-1 border-2 border-gray-400" onClick={handleOnClick}>
      <img src={songWithCreator.song.imageUrl ? `https://${songWithCreator.song.imageUrl}` : altDiscImg} className="w-40 h-40"></img>
      <div className="flex flex-col justify-center w-40 h-20 border-2 border-black">
        <h1 className="mb-2 ml-2 overflow-hidden overflow-ellipsis whitespace-nowrap max-w-full">{songWithCreator.song.title ? songWithCreator.song.title : "제목 없음"}</h1>
        <p className="ml-2 overflow-hidden overflow-ellipsis whitespace-nowrap max-w-full">{songWithCreator.nickname}</p>
      </div>
    </div>
  );
};
