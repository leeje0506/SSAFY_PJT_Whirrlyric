import { useLocation, useNavigate } from "react-router-dom";
import leftArrowIcon from "../../assets/icons/rootlayout/leftArrowIcon";
import batteryIcon from "../../assets/icons/rootlayout/batteryIcon";
import chartBarIcon from "../../assets/icons/rootlayout/chartBarIcon";
import speakerIcon from "../../assets/icons/rootlayout/speakerIcon";
import wifiIcon from "../../assets/icons/rootlayout/wifiIcon";

export default function Header() {
  const location = useLocation();
  const navigate = useNavigate();

  const getHeaderName = (pathname: string) => {
    switch (pathname) {
      case "/write-song":
        return "Write a Song";
      case "/play-song":
        return "Play Song";
      case "/mypage":
        return "My Page";
      case "/change-name":
        return "닉네임 수정";
    }
  };

  return (
      <div className="flex items-center justify-center w-full">
        <div className="flex items-start fixed w-[430px] h-12 bg-gray-700">
          <span className="pt-6 text-white ml-3">12 : 14</span>
          <div className="flex-grow"></div>
          <span className="flex pt-6 text-white mr-2 space-x-2">
          {speakerIcon} {wifiIcon} {chartBarIcon} {batteryIcon}
        </span>
        </div>
        <div className="flex items-center justify-center fixed top-6 w-[430px] h-[80px] mx-auto px-4 bg-white border-b border-gray-200 font-['Pretendard'] font-extrabold text-2xl">
          <button className="absolute left-8" onClick={() => navigate(-1)}>
            {leftArrowIcon}
          </button>
          <h1 className="text-center">{getHeaderName(location.pathname)}</h1>
        </div>
      </div>
  );
}
