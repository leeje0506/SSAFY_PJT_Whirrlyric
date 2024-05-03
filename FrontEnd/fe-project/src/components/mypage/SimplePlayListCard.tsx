import { useNavigate } from "react-router-dom";
import altDiscImg from "../../assets/icons/altDiscImg.png"

export default function SimplePlayListCard() {
  const navigate = useNavigate();

  return (
    <div className="flex mb-2 cursor-pointer" onClick={() => {navigate("/play-song")}}>
      <img
        src={altDiscImg}
        className="w-12 h-12 border-2 border-gray-400 rounded-xl mr-6"
      />
      <div className="flex-col">
        <h1>Black Tryangle</h1>
        <p>프론트 살려</p>
      </div>
    </div>
  );
}
