import altDiscImg from "../../assets/icons/altDiscImg.png";

export default function AlbumCard() {
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
    <div className="flex flex-col w-40 h-60 mr-1 border-2 border-gray-400">
      <img src={dummySong.image || altDiscImg} className="w-40 h-40"></img>
      <div className="flex flex-col justify-center w-40 h-20 border-2 border-black">
        <h1 className="mb-2 ml-2">{dummySong.title}</h1>
        <p className="ml-2">{dummySong.nickname}</p>
      </div>
    </div>
  );
}
