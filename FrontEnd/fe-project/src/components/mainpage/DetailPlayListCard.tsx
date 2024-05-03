import { useNavigate } from "react-router-dom";
import altDiscImg from "../../assets/icons/altDiscImg.png";

export default function DetailPlayListCard() {
  const navigate = useNavigate();

  const dummySong = {
    songId: 1,
    nickname: "닉네임",
    title: "노래 제목",
    lyrics: {
      intro: "인트로",
      verse: "벌스",
      chorus: "코러스",
      bridge: "브릿지",
      outro: "아웃트로",
    },
    image: "",
    song: "",
    playCount: 0,
  };

  return (
    <div
      className="flex h-12 mb-2 cursor-pointer"
      onClick={() => {
        navigate("/play-song");
      }}
    >
      <h1 className="flex flex-col justify-center mx-2">1</h1>
      <img
        src={altDiscImg}
        className="w-12 h-12 border-2 border-gray-400 rounded-xl mx-2"
      />
      <div className="flex-col w-40 mx-2">
        <h1>Black Tryangle</h1>
        <p>{dummySong.nickname}</p>
      </div>

      <h1 className="flex flex-col justify-center mx-2">{dummySong.playCount}</h1>
    </div>
  );
}
