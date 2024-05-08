import altDiscImg from "../../assets/icons/altDiscImg.png";

interface AlbumCardProps{
  songWithCreator: SongWithCreator;
};

export default function AlbumCard({songWithCreator}:AlbumCardProps) {

  return (
    <div className="flex flex-col w-40 h-60 mr-1 border-2 border-gray-400">
      <img src={songWithCreator.song.imageUrl || altDiscImg} className="w-40 h-40"></img>
      <div className="flex flex-col justify-center w-40 h-20 border-2 border-black">
        <h1 className="mb-2 ml-2">{songWithCreator.song.title}</h1>
        <p className="ml-2">{songWithCreator.nickname}</p>
      </div>
    </div>
  );
}
