import altUserImg from "../../assets/icons/altUserImg.png";

export default function SimplePlayListCard() {
  return (
    <div className="flex mb-2">
      <img
        src={altUserImg}
        className="w-12 h-12 border-2 border-gray-400 rounded-xl mr-6"
      />
      <div className="flex-col">
        <h1>Black Tryangle</h1>
        <p>프론트 살려</p>
      </div>
    </div>
  );
}
