import altDiscImg from "../assets/icons/altDiscImg.png";

export default function PlaySong() {
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
    <div>
      <div className="flex flex-col items-center">
        <img
          src={dummySong.image || altDiscImg}
          className="w-80 h-80 mb-4 border-2 border-gray-400 rounded-xl"
        />
      </div>
      <div className="mx-8">
        <h1>{dummySong.title}</h1>
        <p>{dummySong.nickname}</p>
        <h1>Lyrics</h1>
        <p>{dummySong.lyrics.intro}</p>
        <p>{dummySong.lyrics.verse}</p>
        <p>{dummySong.lyrics.chorus}</p>
      </div>
    </div>
  );
}
