import altUserImg from "../../assets/icons/altUserImg.png";

export default function Profile() {
  const dummyUser = {
    nickname: "nick",
    imageUrl: "",
    mainSong: "songmain",
    songList: "list",
  };

  return (
    <div className="flex-col">
      <div className="flex-row">
        <img src={dummyUser.imageUrl || altUserImg} className="w-28 h-28 round-full" />
      </div>
    </div>
  );
}
