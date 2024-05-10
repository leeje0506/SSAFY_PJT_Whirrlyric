import { useNavigate } from "react-router-dom";
import plusIcon from "../../assets/icons/rootlayout/plusIcon";
import homeIcon from "../../assets/icons/rootlayout/homeIcon";
import userIcon from "../../assets/icons/rootlayout/userIcon";

export default function Footer() {
  const navigate = useNavigate();

  const footerButtonStyle = "flex-col mx-auto hover:border-b-2";

  return (
    <div className="footer-container fixed bottom-0 left-0 w-full">
      <div className="flex items-center w-[430px] h-[80px] mx-auto bg-white border-t border-gray-200">
        <button className={footerButtonStyle} onClick={() => navigate("/write-song")}>
          {plusIcon}
        </button>
        <button className={footerButtonStyle} onClick={() => navigate("/main")}>
          {homeIcon}
        </button>
        <button className={footerButtonStyle} onClick={() => navigate("/mypage")}>
          {userIcon}
        </button>
      </div>
    </div>
  );
}